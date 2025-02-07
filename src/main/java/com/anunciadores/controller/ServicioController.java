package com.anunciadores.controller;

import com.anunciadores.dto.CoordinadorDTO;
import com.anunciadores.dto.MinisterioDto;
import com.anunciadores.dto.ServicioListResponseDto;
import com.anunciadores.dto.ServicioResponseDto;
import com.anunciadores.enums.EMeses;
import com.anunciadores.model.Coordinador;
import com.anunciadores.model.Persona;
import com.anunciadores.model.Servicio;
import com.anunciadores.service.interfaces.IPersonaService;
import com.anunciadores.service.interfaces.IServicioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping
public class ServicioController {

	private Logger LOGGER = LoggerFactory.getLogger(ServicioController.class);

	@Autowired
	private IServicioService servicioService;
	@Autowired
	private IPersonaService personaService;

	private Date cargarfechaActualBogota() throws ParseException {
		Date actualDate ;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
		String fechaActualStr = nowInBogota.format(formatter);
		actualDate = sdf.parse(fechaActualStr);
		return  actualDate;
	}

	@GetMapping("/consultarProgramacion")
	public String consultarProgramacion(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		String url = "buscarServicio";
		Date actualDate = cargarfechaActualBogota();
		List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDateGroup(actualDate);
		//List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));
		if(listProgramacionMinisterio.size()>0) {
			Coordinador cor =servicioService.findCoordinador(listProgramacionMinisterio);
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("programacionMin", listProgramacionMinisterio);
			model.addAttribute("coordinador", cor);
			model.addAttribute("fechaCoordinador", cor != null?  dt1.format(cor.getFechaServicio()): null);
		}else{
			model.addAttribute("programacionMin", null);
		}
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return url;
	}

	@GetMapping("/consultarMiProgramacion")
	public String consultarMiProgramacion(@RequestParam String fecha, @RequestParam int idMinisterio, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		String url = "buscarServicioPersona";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaD = sdf.parse(fecha);
		List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDateAndMinisterio(fechaD,idMinisterio);
		//List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));
		if(listProgramacionMinisterio.size()>0) {
			Coordinador cor =servicioService.findCoordinador(listProgramacionMinisterio);
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("programacionMin", listProgramacionMinisterio);
			model.addAttribute("coordinador", cor);
			model.addAttribute("fechaCoordinador", cor != null?  dt1.format(cor.getFechaServicio()): null);
		}else{
			model.addAttribute("programacionMin", null);
		}
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return url;
	}

	@GetMapping("/editarProgramacion")
	public String editarProgramacion(@RequestParam String fecha, @RequestParam int idMinisterio, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		String url = "editar_programacion";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaD = sdf.parse(fecha);
		List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterioAndPerson(fechaD,idMinisterio);



		model.addAttribute("listaPosiciones", ministerios);
		model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
		model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));
		model.addAttribute("fecha", fecha );
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return url;
	}

	@GetMapping("/registrarAsistencia")
	public String registrarAsistencia(@RequestParam String fecha, @RequestParam int idMinisterio, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		String url = "register_asistencia";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaD = sdf.parse(fecha);
		List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterioAndPerson(fechaD,idMinisterio);

		ministerios = servicioService.limpiarListaPosiciones(ministerios,fechaD,idMinisterio);
		model.addAttribute("listaPosiciones", ministerios);
		model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
		model.addAttribute("servidores", servicioService.findPersonaByidMnisterioAsistencia(idMinisterio));
		model.addAttribute("fecha", fecha );
		model.addAttribute("itemsCombo", servicioService.findItemsCombo() );
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return url;
	}
	@PostMapping("/saveCoordinador")
	public String saveCoordinado(@ModelAttribute CoordinadorDTO cordinador, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		String url = "buscarServicio";
		Date actualDate = cargarfechaActualBogota();

		Coordinador corSave =servicioService.findCoordinadorByFecha(cordinador.getFechaServcio());
		if (corSave != null && corSave.getId()!= 0) {
			List<Persona> personasList= personaService.findAllUsuarios();
			model.addAttribute("personas", personasList);
			model.addAttribute("msj", "Ya existe un coordinador asignado para esta fecha, " +
					"si desea editar porfavor dirijase a la seccion de edicion de coordinador");
			return "register-coordinador";
		}else{
			servicioService.saveCoordinado(cordinador);
			List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDateGroup(actualDate);
			//List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));
			if (listProgramacionMinisterio.size() > 0) {
				Coordinador cor = servicioService.findCoordinador(listProgramacionMinisterio);
				model.addAttribute("programacionMin", listProgramacionMinisterio);
				model.addAttribute("coordinador", cor);
			} else {
				model.addAttribute("programacionMin", null);
			}
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			return url;

		}
	}

	@PostMapping("/updateCoordinador")
	public String updateCoordinador(@ModelAttribute CoordinadorDTO cordinador, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		String url = "buscarServicio";
		Coordinador cor =servicioService.findCoordinadorByFecha(cordinador.getFechaServcio());
		cordinador.setId(cor.getId());

		servicioService.saveCoordinado(cordinador);

		List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDate(cordinador.getFechaServcio());
		//List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));
		if(listProgramacionMinisterio.size()>0) {
			Coordinador cord =servicioService.findCoordinador(listProgramacionMinisterio);
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("programacionMin", listProgramacionMinisterio);
			model.addAttribute("coordinador", cord);
			model.addAttribute("fechaCoordinador", cord != null?  dt1.format(cord.getFechaServicio()): null);
		}else{
			model.addAttribute("programacionMin", null);
			model.addAttribute("fechaBuscada", cordinador.getFechaServcio());
		}
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return url;
	}


	@GetMapping("/editarCoordinador")
	public String editarCoordinador(@RequestParam String fecha, @RequestParam int idPersona, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		String url = "editar-coordinador";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaComoCadena = fecha;

		List<Persona>  personasList= personaService.findAllUsuarios();
		model.addAttribute("personas", personasList);
		model.addAttribute("idPersona", idPersona);
		model.addAttribute("fecha", fechaComoCadena );
		return url;
	}

	@PostMapping("/buscarProgramacionByFecha")
	public String buscarProgramacionByFecha(@RequestParam String fecha, Model model) throws ParseException {
		String url = "buscarServicio";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaD = sdf.parse(fecha);
		List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDate(fechaD);
		//List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));
		if(listProgramacionMinisterio.size()>0) {
			Coordinador cor =servicioService.findCoordinador(listProgramacionMinisterio);
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("programacionMin", listProgramacionMinisterio);
			model.addAttribute("coordinador", cor);
			model.addAttribute("fechaCoordinador", cor != null?  dt1.format(cor.getFechaServicio()): null);
		}else{
			model.addAttribute("programacionMin", null);
			model.addAttribute("fechaBuscada", fecha);
		}
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return url;
	}

	@PostMapping("/miProgramacion")
	public String miProgramacion(@ModelAttribute Persona persona,Model model) throws ParseException {
		String url = "servicioMes";
		List<ServicioResponseDto> listServ = servicioService.buscarProgramacionMes(persona.getId());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaActual = sdf.format(new java.util.Date());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse(fechaActual, dtf);

		int month = ld.getMonthValue();
		int mesSiguiente = month+1;
		String meses =  month + "  y "+ mesSiguiente;

		model.addAttribute("listMes", listServ);
		model.addAttribute("msj", "personasList");
		model.addAttribute("mesActual", meses);
		return url;
	}

	@GetMapping("/crearInforme")
	public String crearInforme(Model model, HttpServletRequest request) throws ParseException {
		String url = "asistenciaProgramacion";
		Date actualDate = cargarfechaActualBogota();
		model.addAttribute("fechaActual", actualDate);

		//String url = "register-informCoordinador";
		Coordinador cor= new Coordinador();
		Persona per = new Persona();
		List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDateGroup(actualDate);
		//List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));

		if(listProgramacionMinisterio.size()>0) {
			cor = servicioService.findCoordinador(listProgramacionMinisterio);
			if (cor == null){
				cor = servicioService.findCoordinadorAdministrator(request);
			}
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("programacionMin", listProgramacionMinisterio);
			model.addAttribute("coordinador", cor);
			model.addAttribute("fechaCoordinador", cor.getFechaServicio() != null?  dt1.format(cor.getFechaServicio()): null);
		}else{
			model.addAttribute("programacionMin", null);
		}

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		if(cor != null && cor.getPersona() != null) {
			per = personaService.findPersonaById(cor.getPersona().getId());
		}

		model.addAttribute("msj", "personasList");
		model.addAttribute("persona", per!=null?per:null);
		model.addAttribute("coordinador", cor);
		model.addAttribute("fechaServicio", cor.getFechaServicio() != null?  dt1.format(cor.getFechaServicio()): null);
		return url;
	}

	@PostMapping("/redirectCrearInforme")
	public String redirectCrearInforme(@ModelAttribute Persona persona, Model model, HttpServletRequest request) throws ParseException {
			String url = "register-informCoordinador";
			Date fechaProgramacion= new Date();
			Date actualDate = cargarfechaActualBogota();
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			//Persona per  = personaService.findPersonaById(persona.getId());
			Coordinador cor = new Coordinador() ;
			List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDateGroup(actualDate);
			if(listProgramacionMinisterio.size()>0) {
				cor =servicioService.findCoordinador(listProgramacionMinisterio);
				if (cor == null){
					cor = servicioService.findCoordinadorAdministrator(request);
				}
				String fechaStr = listProgramacionMinisterio.get(0).getFechaServcio();
				fechaProgramacion = dt1.parse(fechaStr);
			}
			model.addAttribute("msj", "personasList");
			model.addAttribute("persona", cor.getPersona());
			model.addAttribute("coordinador", cor);
			model.addAttribute("fechaServicio",cor.getFechaServicio()!=null?  dt1.format(cor.getFechaServicio()):dt1.format(fechaProgramacion));
			return url;
		}

	@PostMapping("/guardarInforme")
	public String guardarInforme(@ModelAttribute Coordinador cordinador, int idPersona,String fechaServCoord,  Model model) throws ParseException {
		String url = "redirect:/redirectDashboard";
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		Persona per  = personaService.findPersonaById(idPersona);
		cordinador.setPersona(per);
		cordinador.setFechaServicio(dt1.parse(fechaServCoord));
		servicioService.saveCoordinadorEntity(cordinador);
		model.addAttribute("msj", "personasList");
		return url;
	}

	@GetMapping("/redirectHisCordindor")
	public String redirectHisCordindor(@RequestParam(name = "name", required = false, defaultValue = "World") String name,Model model) {
		return "reporteHisCordinador";
	}


}
