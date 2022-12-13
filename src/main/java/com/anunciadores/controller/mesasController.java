package com.anunciadores.controller;

import java.text.ParseException;
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

import com.anunciadores.dto.ActividadDto;
import com.anunciadores.model.Actividad;
import com.anunciadores.model.Curso;
import com.anunciadores.model.Mesa;
import com.anunciadores.model.Persona;
import com.anunciadores.repository.IMesasRepo;
import com.anunciadores.service.interfaces.IActividadService;
import com.anunciadores.service.interfaces.IBibliaService;
import com.anunciadores.service.interfaces.IMesasService;
import com.anunciadores.service.interfaces.IPersonaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@RequestMapping
public class mesasController {
	@Autowired
	private IActividadService actividadService;
	
	@Autowired
	private IMesasService mesasService;
	
	@Autowired
	private IBibliaService bibliaService;
	
	@Autowired
	private IPersonaService personaService;

	List<Actividad> ActividadesList;
	
	List<Persona> personasList;
	
	List<Mesa> mesasList;

	

	@GetMapping("/buscarMesa/{id}")
	public ResponseEntity<Object> getProductoById(@PathVariable Integer id) {

		return ResponseEntity.ok(actividadService.findActividadById(id));

	}

	@PostMapping("/saveMesa")
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

	@GetMapping("/eliminarMesa")
	public String deleteProductoById(@ModelAttribute Mesa mesa, HttpServletResponse response,
			Model model) {	
		mesasService.delete(mesa);
		
		return "mesas";

	}
	
	@GetMapping("/personasMesa")
	public String personasMesa(@ModelAttribute Mesa mesa, HttpServletResponse response,
			Model model) {	
		personasList= mesasService.BuscarPersonasSinMesa(mesa);
		model.addAttribute("personas", personasList);
		return "personasMesa";

	}
	
	@GetMapping("/mesasActividad")
	public String mesasActividad(@ModelAttribute ActividadDto actividadDto ,Model model) {
		
		mesasList = actividadService.buscarMesasActividad(actividadDto);
		model.addAttribute("mesas", mesasList);
		return "mesas";
	}	
	
//	@GetMapping("/editarActividad")
//	public String editarCursoById(@ModelAttribute ActividadDto actividadDto, HttpServletResponse response,
//			Model model) throws ParseException {	
//		Curso cursoMostrar = new Curso();
////		actividadService.save(curso);
////		System.out.println("fechaInicio para modificacion " + actividadDto.getFechaInicio());
////		System.out.println("fechaFin para modificacion " + actividadDto.getFechaFin());
//		cursoMostrar.setId(actividadDto.getId());
////		cursoMostrar.setFechaInicio(actividadService.ParseFecha(curso.getFechaInicio()));
////		cursoMostrar.setFechaFin(actividadService.ParseFecha(curso.getFechaFin()));
////		cursoMostrar.setNombreCurso(actividadDto.getNombreCurso());
////		cursoMostrar.setValorTotal(actividadDto.getValorTotal());
//		
//		model.addAttribute("actividad", actividadDto);
//		
//		return "edit-curso";
//
//	}
	
//	@GetMapping("/agregarPersonaActividad")
//	public String AgregarPersonasCurso(@RequestParam int idPersona, @RequestParam int idActividad,
//			@RequestParam String nombreActividad, Model model) {
//		personaService.agregarPersonaActividad(idPersona, idActividad);
//		personasList = actividadService.buscarPersonasActividad(idActividad);
//		model.addAttribute("personas", personasList);
//		model.addAttribute("msj", "Personas asociadas a la actividad: " + nombreActividad);
//		model.addAttribute("titulo", "Lista de Personas inscritas");
//		model.addAttribute("add", false);
//		model.addAttribute("delete", true);
//		model.addAttribute("idActividad", idActividad);
//		return "personasActividad";
//	}
	
//	@GetMapping("/buscarPersonasSinActividad")
//	public String buscarPersonasSinCurso(@RequestParam int idActividad, @RequestParam String nombreActividad, Model model) {
//		personasList = actividadService.buscarTodosSinActividad(idActividad);
//		model.addAttribute("personas", personasList);
//		model.addAttribute("msj", "Personas las cuales se puede agregar a la actividad: " + nombreActividad);
//		model.addAttribute("titulo", "Lista de Personas no inscritas");
//		model.addAttribute("add", true);
//		model.addAttribute("delete", false);
//		model.addAttribute("nombreActividad", nombreActividad);
//		model.addAttribute("idActividad", idActividad);
//		return "personasActividad";
//	}
	

	


}