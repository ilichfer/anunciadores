package com.anunciadores.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.anunciadores.dto.AsignacionConsolidacionDto;
import com.anunciadores.dto.PersonaConsolidacionDto;
import com.anunciadores.model.Persona;
import com.anunciadores.repository.IPersonaRepo;
import com.anunciadores.service.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.anunciadores.dto.ConsolidacionDto;
import com.anunciadores.model.Mesa;
import com.anunciadores.service.interfaces.IConsolidacionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@RequestMapping
public class consolidacionController {
	
	@Autowired
	private IConsolidacionService consolidacionService;

	@Autowired
	private IPersonaService personaService;

	@Autowired
	private IPersonaRepo personaRepoImpl;

	

	@GetMapping("/buscarConsolidacion")
	public String buscarConsolidacionById( Model model) {
		String nombre="consolidacion";
		List<PersonaConsolidacionDto> servidoresConsolidacion= consolidacionService.listarservidoresConsolidacion(nombre);
		model.addAttribute("personas", servidoresConsolidacion);
		return "personasConsolidacion";

	}

	@PostMapping("/buscarConsolidacionByPersona")
	public String buscarConsolidacionByPersona(@RequestParam int idPersona, Model model) {
		List<AsignacionConsolidacionDto> personasList;
		String nombre="consolidacion";
		personasList = consolidacionService.listarConsolidacionByServidor(idPersona);
		model.addAttribute("personas", personasList);
		model.addAttribute("msj", "Personas asignadas para consolidacion");
		model.addAttribute("titulo", "Lista de Personas a consolidar");
		model.addAttribute("add", true);
		model.addAttribute("delete", false);
		model.addAttribute("nombreCurso", "nombreCurso");
		model.addAttribute("idCurso", "idCurso");
		model.addAttribute("pago", false);
		return "personasAConsolidar";

	}

	@PostMapping("/saveConsolidacion")
	public String saveConsolidacion(@ModelAttribute ConsolidacionDto consolidacionDto, HttpServletResponse response, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
		String url = "asignarPadres";
		if (consolidacionDto != null && consolidacionDto.getIdPersonaConsolidar() !=0) {
			 Boolean asigancion = consolidacionService.asignarPersonaAPadreEspiritual(consolidacionDto.getIdPadreEspiritual(), consolidacionDto.getIdPersonaConsolidar());
			if (asigancion) {
				url = "redirect:/buscarConsolidacion";
			}else {
				List<Persona> listtConsolidacion = personaRepoImpl.listarConsolidacion();
				Persona persona = personaService.findPersonaById(consolidacionDto.getIdPadreEspiritual());
				model.addAttribute("id", persona.getId());
				model.addAttribute("nombre", persona.getNombre());
				model.addAttribute("asistentes", listtConsolidacion);
				model.addAttribute("message", "ya posee una consolidacion asignada");
			}
		}else{
			List<Persona> listtConsolidacion = personaRepoImpl.listarConsolidacion();
			Persona persona = personaService.findPersonaById(consolidacionDto.getIdPadreEspiritual());
			model.addAttribute("message", "debe seleccionar una de las opciones disponibles");
			model.addAttribute("id", persona.getId());
			model.addAttribute("nombre", persona.getNombre());
			model.addAttribute("asistentes", listtConsolidacion);
		}
		return url;
	}

	@GetMapping("/eliminarConsolidacion")
	public String deleteProductoById(@ModelAttribute Mesa mesa, HttpServletResponse response,
			Model model) {	
		
		return "mesas";

	}

	@PostMapping("/guardarActividadConsolidacion")
	public String guardarActividadConsolidacion(@RequestParam int idPersona,@RequestParam int idConsolidacion,@RequestParam String desConsolidacion, Model model) {
		List<AsignacionConsolidacionDto> personasList;
		consolidacionService.saveDescripcionConsolidacion(idConsolidacion, desConsolidacion);
		personasList = consolidacionService.listarConsolidacionByServidor(idPersona);
		model.addAttribute("personas", personasList);
		model.addAttribute("msj", "Personas asignadas para consolidacion");
		model.addAttribute("titulo", "Lista de Personas a consolidar");
		model.addAttribute("add", true);
		model.addAttribute("delete", false);
		model.addAttribute("nombreCurso", "nombreCurso");
		model.addAttribute("idCurso", "idCurso");
		model.addAttribute("idPersona", idPersona);
		model.addAttribute("pago", false);
		return "personasAConsolidar";

	}

	@GetMapping("/redirectActividadConsolidacion")
	public String redirectActividadConsolidacion(@RequestParam int idPersona,@RequestParam int idConsolidacion, Model model) {
		model.addAttribute("idConsolidacion", idConsolidacion);
		model.addAttribute("idPersona", idPersona);
		return "register-desconsolidacion";

	}

}