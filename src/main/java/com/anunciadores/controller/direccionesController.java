package com.anunciadores.controller;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.anunciadores.dto.*;
import com.anunciadores.model.Coordinador;
import com.anunciadores.model.Ministerio;
import com.anunciadores.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anunciadores.model.Curso;
import com.anunciadores.model.Persona;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


@Controller
@RequestMapping
public class direccionesController {

	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IBibliaService bibliaService;

	@Autowired
	private IPagoService pagoService;
	
	@Autowired
	private ICursoService cursoService;

	@Autowired
	private ITdcService tdcService;

	@Autowired
	private IServicioService servicioService;

	@Autowired
	private ICombos combosService;
	
	List<Persona> personasList;


	@GetMapping("/redirectCurso")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		personasList= personaService.findAllUsuarios();
		model.addAttribute("personas", personasList);
		return "register-curso";
	}

	@GetMapping("/redirectCoordinador")
	public String redirectCoordinador(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
						   Model model) {
		personasList= personaService.findAllUsuarios();
		model.addAttribute("personas", personasList);
		model.addAttribute("msj", null);
		return "register-coordinador";
	}

	
	@GetMapping("/redirectPersona")
	public String redirectPersona(@RequestParam(value = "action", required = false) String action,
			Model model) {
		model.addAttribute("name", action);
		return "registrarPersona";
	}
	@GetMapping("/redirectPersonaOut")
	public String redirectPersonaOut(@RequestParam(value = "action", required = false) String action,
			Model model) {
		Persona persona = new Persona();
		ListasCombos listas = combosService.listarParametros();
		model.addAttribute("name", action);
		model.addAttribute("persona", persona);
		model.addAttribute("listaGenero", listas.getListaGenero());
		model.addAttribute("listaEstadoCivil", listas.getEstadoCivil());
		model.addAttribute("listaEscolaridad", listas.getListaEscolaridad());
		model.addAttribute("listaDocumento", listas.getListaDocuemntos());
		return "registerOut";
	}
	
	@GetMapping("/redirectDashboard")
	public String login(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		VersiculoDto dia =bibliaService.findVerseDay();
		List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDateGroup(Date.valueOf(LocalDate.now()));
		model.addAttribute("dia", dia);
		if(listProgramacionMinisterio.size()>0) {
			Coordinador cor = servicioService.findCoordinador(listProgramacionMinisterio);
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("programacionMin", listProgramacionMinisterio);
			model.addAttribute("coordinador", cor);
			model.addAttribute("fechaCoordinador",cor != null?  dt1.format(cor.getFechaServicio()): null);
		}else{
			model.addAttribute("programacionMin", null);
		}
		String url = "index";
		return url;
	}
	
	@GetMapping("/redirectActividad")
	public String redirectActividad( HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
		String url = "register-actividad";
		return url;
	}
	
	@GetMapping("/redirectAsistente")
	public String redirectAsistente(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		return "registerAsistente";
	}

	@GetMapping("/redirectAsistenteConsolidacion")
	public String redirectAsistenteConsolidacion(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
									Model model) {
		return "registerAsistenteConsolidacion";
	}
	
	@GetMapping("/redirectUsuario")
	public String redirectUsuario(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		return "register";
	}
	
	@GetMapping("/redirectLogin")
	public String redirectLogin(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		return "login";
	}
	@GetMapping("/redirectSeguimiento")
	public String redirectSeguimiento(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		return "seguimientoPadres";
	}
	@PostMapping("/perfil")
	public String redirectTDC2(@ModelAttribute Persona persona,Model model) {
		Persona per = personaService.findPersonaById(persona.getId());
		model.addAttribute("persona", per);
		return "perfil";
	}
	@GetMapping("/editarPerfil")
	public String editarPerfil(@RequestParam int idPersona,Model model) {
		Persona per = personaService.findPersonaById(idPersona);
		ListasCombos listas = combosService.listarParametros();
		model.addAttribute("persona", per);
		model.addAttribute("listaGenero", listas.getListaGenero());
		model.addAttribute("listaEstadoCivil", listas.getEstadoCivil());
		model.addAttribute("listaEscolaridad", listas.getListaEscolaridad());
		model.addAttribute("listaDocumento", listas.getListaDocuemntos());
		return "editPerfil";
	}

	@GetMapping("/redirectPago")
	public String redirectPago(@RequestParam int idCurso, @RequestParam int idPersona, Model model) {
		
		Curso curso = cursoService.findCursoById(idCurso);
		Persona persona =personaService.findPersonaById(idPersona);
		model.addAttribute("persona", persona);
		model.addAttribute("curso", curso);
		return "registrarPago";
	}
	
	@GetMapping("/recuperarPass")
	public String recuperarPass(@RequestParam(value = "action", required = false) String action,
			Model model) {
		return "recoverPass";
	}

	@PostMapping("/redirectTDC")
	public String redirectTDC2(@RequestParam int idPersonaTCD, Model model) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
		String fechaformat = nowInBogota.format(formatter);

		model.addAttribute("fecha", fechaformat);
		model.addAttribute("idPersona", idPersonaTCD);
		return "registerTDC";
	}

	@GetMapping("/redirectReporteTDC")
	public String redirectReporteTDC(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
							  Model model) {
		return "reporteTdc";
	}

	@GetMapping("/redirectReporteTdcPersona")
	public String reporteTdcPersona(@RequestParam int idPersona,@RequestParam Date fechaInicio,@RequestParam Date fechaFin,
									 Model model) {
		List<TdcDto> listTCD= tdcService.findAllBetweenDatesByPersona(fechaInicio,fechaFin,idPersona);
		Persona per= personaService.findPersonaById(idPersona);
		model.addAttribute("fechaInicio", fechaInicio);
		model.addAttribute("fechaFin", fechaFin);
		model.addAttribute("idPersona", idPersona);
		model.addAttribute("persona", per);
		model.addAttribute("listaTdc", listTCD);
		return "reporteTdcPersona";
	}

	@GetMapping("/listarMinisterios") public String listarMinisterios( Model model) {
		List<Ministerio>  ministerios =servicioService.getAll();
		model.addAttribute("ministerios", ministerios);
		return "ministerios";
	}

	@GetMapping("/gestionarMinisterios") public String gestionarMinisterios( Model model) {
		List<Ministerio>  ministerios =servicioService.getAll();
		model.addAttribute("ministerios", ministerios);
		return "listarMinisterios";
	}
	@GetMapping("/redirectMinisterio")
	public String redirectMinisterio(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
						   Model model) {
		return "register-ministerio";
	}
}
