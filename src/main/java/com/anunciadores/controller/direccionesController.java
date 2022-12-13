package com.anunciadores.controller;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

import com.anunciadores.dto.VersiculoDto;
import com.anunciadores.model.Curso;
import com.anunciadores.model.Persona;
import com.anunciadores.service.interfaces.IBibliaService;
import com.anunciadores.service.interfaces.ICursoService;
import com.anunciadores.service.interfaces.IPagoService;
import com.anunciadores.service.interfaces.IPersonaService;
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
	
	List<Persona> personasList;


	@GetMapping("/redirectCurso")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		return "register-curso";
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
		model.addAttribute("name", action);
		return "registerOut";
	}
	
	@GetMapping("/redirectDashboard")
	public String login(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
		VersiculoDto dia =bibliaService.findVerseDay();
		model.addAttribute("dia", dia);
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
	@GetMapping("/perfil")
	public String perfil(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		return "perfil";
	}
	@GetMapping("/redirectPago")
	public String redirectPago(@RequestParam int idCurso, @RequestParam int idPersona, Model model) {
		
		Curso curso = cursoService.findCursoById(idCurso);
		Persona persona =personaService.findPersonaById(idPersona);
		model.addAttribute("persona", persona);
		model.addAttribute("curso", curso);
		return "registrarPago";
	}
	

}
