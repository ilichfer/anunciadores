package com.anunciadores.controller;


import com.anunciadores.dto.*;
import com.anunciadores.model.Persona;
import com.anunciadores.service.interfaces.IBibliaService;
import com.anunciadores.service.interfaces.ITelegramService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping
public class TelegramController {

	@Autowired
	private ITelegramService telegramService;

	@GetMapping("/actualizarIdNotificacionesTelegram")
	public String actualizarIdNotificacionesTelegram(Model model) throws JsonMappingException, JsonProcessingException {
		List<Persona> personasActualizadas = telegramService.getUpdatesWithContact();
		model.addAttribute("personas", personasActualizadas.size()>0?personasActualizadas:null);
		model.addAttribute("notificaciones", null);
		model.addAttribute("msj", personasActualizadas.size()>0?null:"no se encontraron datos para actualizar en Telegram");
		return "personasTelegram";
	}

    @GetMapping("/enviarMensajeTelegram")
    public ResponseEntity<ResponseTelegram> enviarMensajeTelegram(@RequestParam String token,@RequestParam String mensaje) throws JsonMappingException, JsonProcessingException {
		ResponseTelegram responsedata = telegramService.getContact(mensaje);
        return null;
    }

	@GetMapping("/solicitarContactoTelegram")
	public String buscardatosContactoServidoresTelegram(Model model) throws JsonMappingException, JsonProcessingException {
		int notifiaciones = telegramService.getUpdatesWithOutContact();
		model.addAttribute("personas", null);
		model.addAttribute("notificaciones", null);
		model.addAttribute("msj", "se han solicitado el contacto a "+notifiaciones+" servidores en Telegram");

		return "personasTelegram";
	}

	@GetMapping("/notificacionProgramacion")
	public String notificacionProgramacion(Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		List<ServicioResponseDto> personasNotificadas = telegramService.sendNotification();
		model.addAttribute("personas", null);
		model.addAttribute("notificaciones", personasNotificadas);
		model.addAttribute("msj", null);

		return "personasTelegram";
	}

	@GetMapping("/getContactBdTelegram")
	public String getContactBdTelegram(Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		List<Persona> personasTelegram = telegramService.getContactTelegram();
		model.addAttribute("personas", personasTelegram.size()>0?personasTelegram:null);
		model.addAttribute("notificaciones", null);
		model.addAttribute("msj", null);

		return "personasTelegram";
	}

}
