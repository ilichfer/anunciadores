package com.anunciadores.service;

import com.anunciadores.client.TelegramClient;
import com.anunciadores.dto.*;
import com.anunciadores.model.Coordinador;
import com.anunciadores.model.Persona;
import com.anunciadores.repository.IPersonaRepo;
import com.anunciadores.service.interfaces.IServicioService;
import com.anunciadores.service.interfaces.ITelegramService;
import com.anunciadores.util.UtilDate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TelegramServiceImpl implements ITelegramService {

	@Autowired
	private IPersonaRepo personaRepo;

	@Autowired
	TelegramClient telegramClient;

	@Autowired
	private IServicioService servicioService;

	@Autowired
	private UtilDate utilDate;

	@Value("${propiedad.telegram.token}")
	private String telegramToken;


	private final Logger log = LoggerFactory.getLogger(TelegramServiceImpl.class);

	@Override
	public List<Persona> getUpdatesWithContact() throws JsonMappingException, JsonProcessingException {
		List<Persona> peopleWithIdTelegram = new ArrayList<>();
		ResponseTelegram response = telegramClient.getUpdates(telegramToken);
		Set<Long> contactosUnicos = new HashSet<>();

		List<ResultDTO> actualizacionesConContacto = response.getResult().stream()
				.filter(result -> {
					MessageDTO message = result.getMessage();
					return message != null && message.getContact()!= null;
				})
				.filter(result -> contactosUnicos.add(result.getMessage().getChat().getId()))
				.collect(Collectors.toList());

		for (ResultDTO update : actualizacionesConContacto) {
			ContactDTO contact = update.getMessage().getContact();
			String telefonoOriginal = contact.getPhone_number();
			String telefonoRecortado = telefonoOriginal.length() > 2
					? telefonoOriginal.substring(2)
					: telefonoOriginal; // Evita error si el número es muy corto

			Long chatId = update.getMessage().getChat().getId();
			String nombre = update.getMessage().getFrom().getFirst_name();

			System.out.println("📞 Teléfono limpio: " + telefonoRecortado);
			System.out.println("👤 Nombre: " + nombre);
			System.out.println("💬 Chat ID: " + chatId);
			System.out.println("────────────────────────────");

			Optional<List<Persona>> personaTelegram = personaRepo.findByCelular(telefonoRecortado);

			if (personaTelegram.isPresent() && personaTelegram.get().size()>1) {
					for ( Persona p: personaTelegram.get()){
						System.out.println("   - Persona encontrada: ID=" + p.getId() + ", Nombre=" + p.getNombre() + ", Celular=" + p.getCelular() + ", TelegramID=" + p.getIdTelegram());
						p.setIdTelegram(chatId.toString());
						peopleWithIdTelegram.add(personaRepo.save(p));
					}
			}else if(personaTelegram.isPresent() && personaTelegram.get().size()==1){
				personaTelegram.get().get(0).setIdTelegram(chatId.toString());

				peopleWithIdTelegram.add(personaRepo.save(personaTelegram.get().get(0)));
			}else {
				System.out.println("❌ No existe");
			}
		}

		return peopleWithIdTelegram;
	}

	@Override
	public List<Persona> getContactTelegram() throws JsonMappingException, JsonProcessingException {
		return personaRepo.findAll().stream().filter( p -> p.getEstado() == true )
				.collect(Collectors.toList());
	}

	@Override
	public int getUpdatesWithOutContact() throws JsonMappingException, JsonProcessingException {
		ResponseTelegram response = telegramClient.getUpdates(telegramToken);
		Set<Long> contactosUnicos = new HashSet<>();

		List<ResultDTO> actualizacionesSinDuplicados = response.getResult().stream()
				.filter(result -> {
					MessageDTO message = result.getMessage();
					return message != null && message.getContact() == null;
				})
				.filter(result -> contactosUnicos.add(result.getMessage().getChat().getId()))
				.collect(Collectors.toList());
int contador=0;


		for (ResultDTO update : actualizacionesSinDuplicados) {

			Long idChatTelegram = update.getMessage().getChat().getId();

			Optional<List<Persona>> list=	personaRepo.findByIdTelegram(idChatTelegram.toString());

			if (list.get().size()>0) {
				System.out.println("❌ Ya tiene telegram asociado");
			}
			else{
				getContact(idChatTelegram.toString());
				System.out.println("✅ No tiene telegram asociado");
				contador++;
			}
	}
		return contador;
	}

	@Override
	public List<ServicioResponseDto> sendNotification() throws JsonMappingException, JsonProcessingException, ParseException {
		List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDateGroup(utilDate.cargarfechaActualBogotaDate());
		List<ServicioResponseDto> personasProgramandas = new ArrayList<>();
		if(listProgramacionMinisterio.size()>0) {
			for (ServicioListResponseDto serv: listProgramacionMinisterio){
				for (ServicioResponseDto s: serv.getServicioDTO()){
					if(s.getIdNotificacionTelegram()!=null){
						personasProgramandas.add(s);

						Map<String, Object> body = new HashMap<>();
						body.put("chat_id", Long.parseLong(s.getIdNotificacionTelegram())); // ID del chat al que quieres enviar
						body.put("text", "Hola <b>"+s.getEncargado()+"</b>,\neste es un mensaje automático para recordarte que tienes servicio el día <b>"+serv.getFechaServcio()+"</b> en el ministerio de <b>"+serv.getNombreMinisterio()+"</b> en la posición <b>"+s.getPosicion()+"</b>.\n¡Gracias por tu servicio!");
						body.put("disable_notification", false);

						String respuesta = telegramClient.enviarMensajeTelegram(telegramToken, body,"HTML");
						System.out.println("📨 Telegram respondió: " + respuesta);
					}else{
						personasProgramandas.add(s);
						System.out.println(s.getEncargado()+"   ❌ No tiene telegram asociado");
					}
				}
			}
			Coordinador cor = servicioService.findCoordinador(listProgramacionMinisterio);
			if(cor!=null && cor.getPersona()!=null && cor.getPersona().getIdTelegram()!=null){
				Map<String, Object> body = new HashMap<>();
				body.put("chat_id", Long.parseLong(cor.getPersona().getIdTelegram())); // ID del chat al que quieres enviar
				body.put("text", "Hola <b>"+cor.getPersona().getNombre()+"</b>,\neste es un mensaje automático para recordarte que tienes servicio el día <b>"+utilDate.convertDateToString(cor.getFechaServicio())+"</b> en la posición de <b> Coordinador </b>.\n¡Gracias por tu servicio!");
				body.put("disable_notification", false);

				String respuesta = telegramClient.enviarMensajeTelegram(telegramToken, body,"HTML");
				System.out.println("📨 Telegram respondió: " + respuesta);
			}
		}
        return personasProgramandas;
    }


	@Override
	public ResponseTelegram getContact(String idTelegram) throws JsonMappingException, JsonProcessingException {

		if (!personaRepo.findFirstByIdTelegram(idTelegram).isPresent() && !idTelegram.equals("7726706876") ) {
		Map<String, Object> body = new HashMap<>();
		body.put("chat_id",idTelegram); // ID del chat al que quieres enviar
		body.put("text", "Hola, Dios te bendiga.\n" +
				"nos encantaría poder estar en contacto contigo para compartir información y apoyarte en lo que necesites.\n" +
				"\uD83D\uDC47 ¿Podrías compartirnos tu contacto telefonico dando click en el boton de abajo? \uD83D\uDC47 ");
		body.put("disable_notification", false);

		// Botón que solicita el contacto
		Map<String, Object> botonContacto = new HashMap<>();
		botonContacto.put("text", "Click aqui para compartir tu telefono");
		botonContacto.put("request_contact", true);

		// Fila de botones
		List<Map<String, Object>> filaBotones = new ArrayList<>();
		filaBotones.add(botonContacto);

		// Teclado completo
		List<List<Map<String, Object>>> teclado = new ArrayList<>();
		teclado.add(filaBotones);

		// reply_markup con configuración
		Map<String, Object> replyMarkup = new HashMap<>();
		replyMarkup.put("keyboard", teclado);
		replyMarkup.put("resize_keyboard", true);
		replyMarkup.put("one_time_keyboard", true);

		body.put("reply_markup", replyMarkup);

		String respuesta = telegramClient.enviarMensajeTelegram(telegramToken, body,"HTML");
		System.out.println("📨 Telegram respondió: " + respuesta);
		}
		return null;
	}


	@Scheduled(cron = "0 0 18 * * *", zone = "America/Bogota")
	//@Scheduled(fixedDelay = 10000) // Cada 10 segundos para pruebas
	public ResponseTelegram sendMessage() throws JsonMappingException, JsonProcessingException, ParseException {

		LocalDate fechaActual = LocalDate.now();

		// Obtener el día de la semana como un objeto DayOfWeek
		DayOfWeek diaDeLaSemana = fechaActual.getDayOfWeek();

		// Imprimir el día de la semana (en inglés por defecto)
		System.out.println("El día actual es: " + diaDeLaSemana);

		// Validar si es jueves
		if (diaDeLaSemana == DayOfWeek.THURSDAY || diaDeLaSemana == DayOfWeek.SATURDAY) {
			fechaActual = fechaActual.plusDays(1);
			Date fechaAumentada = java.sql.Date.valueOf(fechaActual);
			List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDateGroup(fechaAumentada);

			if(listProgramacionMinisterio.size()>0) {
				for (ServicioListResponseDto serv: listProgramacionMinisterio){
					for (ServicioResponseDto s: serv.getServicioDTO()){
						if(s.getIdNotificacionTelegram()!=null){
							Map<String, Object> body = new HashMap<>();
							body.put("chat_id", Long.parseLong(s.getIdNotificacionTelegram())); // ID del chat al que quieres enviar
							body.put("text", "Hola <b>"+s.getEncargado()+"</b>,\n este es un mensaje automático para recordarte que tienes servicio el día <b>"+serv.getFechaServcio()+"</b> en el ministerio de <b>"+serv.getNombreMinisterio()+"</b> en la posición <b>"+s.getPosicion()+"</b>. \n ¡Gracias por tu servicio!");
							body.put("disable_notification", false);

							String respuesta = telegramClient.enviarMensajeTelegram(telegramToken, body,"HTML");
							System.out.println("📨 Telegram respondió: " + respuesta);
						}else{
							System.out.println(s.getEncargado()+"   ❌ No tiene telegram asociado");
						}
					}
				}
				Coordinador cor = servicioService.findCoordinador(listProgramacionMinisterio);
				if(cor!=null && cor.getPersona()!=null && cor.getPersona().getIdTelegram()!=null){
					Map<String, Object> body = new HashMap<>();
					body.put("chat_id", Long.parseLong(cor.getPersona().getIdTelegram())); // ID del chat al que quieres enviar
					body.put("text", "Hola <b>"+cor.getPersona().getNombre()+"</b>,\n este es un mensaje automático para recordarte que tienes servicio el día <b>"+utilDate.convertDateToString(cor.getFechaServicio())+"</b> en la posición de <b> Coordinador </b>. \n¡Gracias por tu servicio!");
					body.put("disable_notification", false);

					String respuesta = telegramClient.enviarMensajeTelegram(telegramToken, body,"HTML");
					System.out.println("📨 Telegram respondió: " + respuesta);
				}
			}
		} else {
			System.out.println("Hoy no ejecutar ña tarea.");
		}



		return null;
	}

}
