package com.anunciadores.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.anunciadores.dto.CursoDto;
import com.anunciadores.dto.PersonaDto;
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

import com.anunciadores.dto.ActividadDto;
import com.anunciadores.model.Actividad;
import com.anunciadores.model.Curso;
import com.anunciadores.model.Mesa;
import com.anunciadores.model.Persona;
import com.anunciadores.service.interfaces.IActividadService;
import com.anunciadores.service.interfaces.IBibliaService;
import com.anunciadores.service.interfaces.IPersonaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@RequestMapping
public class actividadController {
	@Autowired
	private IActividadService actividadService;
	
	@Autowired
	private IBibliaService bibliaService;
	
	@Autowired(required=true)
	private IPersonaService personaService;

	List<Actividad> ActividadesList;
	
	List<Persona> personasList;
	
	List<Mesa> mesasList;

	@GetMapping("/listarActividades")
	public String Cursos(HttpServletResponse response, Model model) {
		ActividadesList = actividadService.listarActiviades();
		model.addAttribute("actividades", ActividadesList);
		return "actividades";
	}

	@GetMapping("/actividadesAutoIncripcion")
	public String listarActividadesAutoIncripcion(HttpServletResponse response, Model model) {
		ActividadesList = actividadService.listarActiviades();
		model.addAttribute("actividades", ActividadesList);
		return "actividadesAutoInscripcion";
	}

	@GetMapping("/buscarActividad/{id}")
	public ResponseEntity<Object> getProductoById(@PathVariable Integer id) {

		return ResponseEntity.ok(actividadService.findActividadById(id));

	}

	@PostMapping("/saveActividad")
	public String save(@ModelAttribute ActividadDto actividadDto, HttpServletResponse response, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
		String url = "redirect:/404.html";
		if (actividadDto != null) {
			Actividad actividad = actividadService.save(actividadDto);
			ActividadesList = actividadService.listarActiviades();
			model.addAttribute("actividades", ActividadesList);
			url = "actividades";
		}
		return url;
	}

	@GetMapping("/eliminarActividad")
	public String deleteProductoById(@ModelAttribute ActividadDto actividadDto, HttpServletResponse response,
			Model model) {	
		actividadService.delete(actividadDto);
		ActividadesList = actividadService.listarActiviades();
		model.addAttribute("actividades", ActividadesList);
		return "actividades";

	}
	
//	@GetMapping("/mesasActividad")
//	public String mesasActividad(@ModelAttribute ActividadDto actividadDto ,Model model) {
//		
//		mesasList = actividadService.buscarMesasActividad(actividadDto);
//		model.addAttribute("mesas", mesasList);
//		return "mesas";
//	}	
	
	@GetMapping("/editarActividad")
	public String editarCursoById(@ModelAttribute ActividadDto actividadDto, HttpServletResponse response,
			Model model) throws ParseException {
		model.addAttribute("actividad", actividadDto);
		
		return "edit-actividad";

	}
	
	@GetMapping("/agregarPersonaActividad")
	public String AgregarPersonasCurso(@RequestParam int idPersona, @RequestParam int idActividad,
			@RequestParam String nombreActividad, Model model) {
		personaService.agregarPersonaActividad(idPersona, idActividad);
		personasList = actividadService.buscarPersonasActividad(idActividad);
		model.addAttribute("personas", personasList);
		model.addAttribute("msj", "Personas asociadas a la actividad: " + nombreActividad);
		model.addAttribute("titulo", "Lista de Personas inscritas");
		model.addAttribute("add", false);
		model.addAttribute("delete", true);
		model.addAttribute("idActividad", idActividad);
		return "personasActividad";
	}
	
	@GetMapping("/buscarPersonasSinActividad")
	public String buscarPersonasSinCurso(@RequestParam int idActividad, @RequestParam String nombreActividad, Model model) {
		personasList = actividadService.buscarTodosSinActividad(idActividad);
		model.addAttribute("personas", personasList);
		model.addAttribute("msj", "Personas las cuales se puede agregar a la actividad: " + nombreActividad);
		model.addAttribute("titulo", "Lista de Personas no inscritas");
		model.addAttribute("add", true);
		model.addAttribute("delete", false);
		model.addAttribute("nombreActividad", nombreActividad);
		model.addAttribute("idActividad", idActividad);
		return "personasActividad";
	}

	@PostMapping("/hisMisActividades")
	public String hisMisActividades(@RequestParam int idPersonaMinisterioUser, Model model) {
		List<Actividad>  actividades = actividadService.listarActiviadesByPersona(idPersonaMinisterioUser);

		model.addAttribute("actividades", actividades);
		return "misActividades";
	}
	


}