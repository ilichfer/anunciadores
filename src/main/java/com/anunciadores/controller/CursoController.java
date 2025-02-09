package com.anunciadores.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.anunciadores.dto.HistoricoNotasDto;
import com.anunciadores.model.NotasCurso;
import com.anunciadores.service.interfaces.IPersonaService;
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

import com.anunciadores.dto.CursoDto;
import com.anunciadores.dto.VersiculoDto;
import com.anunciadores.model.Curso;
import com.anunciadores.model.Persona;
import com.anunciadores.service.interfaces.IBibliaService;
import com.anunciadores.service.interfaces.ICursoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@RequestMapping
public class CursoController {
	@Autowired
	private ICursoService cursoService;
	
	@Autowired
	private IBibliaService bibliaService;

	@Autowired
	private IPersonaService personaService;

	List<Curso> CursosList;

	List<Persona> personasList;

	@GetMapping("/listarCursos")
	public String Cursos(HttpServletResponse response, Model model) {
		CursosList = cursoService.findAll();
		model.addAttribute("cursos", CursosList);
		return "cursos";
	}

	@PostMapping("/listarCursosAbiertos")
	public String cursosAbiertos(@ModelAttribute Persona persona,HttpServletResponse response, Model model) {
		CursosList = cursoService.findAllActiveByPerson(persona.getId());
		model.addAttribute("cursos", CursosList);
		model.addAttribute("persona", persona);
		return "cursosAutoInscripcion";
	}

	@GetMapping("/buscarCurso/{id}")
	public ResponseEntity<Object> getProductoById(@PathVariable Integer id) {

		return ResponseEntity.ok(cursoService.findCursoById(id));

	}

	@PostMapping("/saveCurso")
	public String save(@ModelAttribute CursoDto Curso, HttpServletResponse response, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
		String url = "redirect:/404.html";
		
		if (Curso != null) {
			Curso CursoSave = cursoService.save(Curso);
			CursosList = cursoService.findAll();
			model.addAttribute("cursos", CursosList);
			url = "cursos";
		}
		return url;
	}

	@PostMapping("/saveNotasCurso")
	public String saveNotasCurso(@ModelAttribute NotasCurso notasCurso,@RequestParam int idPersona,@RequestParam int idCurso,  Model model) throws ParseException, JsonMappingException, JsonProcessingException {
		StringBuilder url = new StringBuilder();
		url.append("redirect:/notasCurso?idCurso=");
		if (notasCurso != null) {
			notasCurso.setPersona(personaService.findPersonaById(idPersona));
			notasCurso.setCurso(cursoService.findCursoById(idCurso));

			NotasCurso notas = cursoService.saveNotasCurso(notasCurso);
			CursosList = cursoService.findAll();
			model.addAttribute("idCurso", idCurso);
			url.append(idCurso);
			url.append("&nombreCurso=Curso%20pruebas%20");

		}
		return url.toString();
	}

	@PostMapping("/historicoNotas")
	public String historicoNotas(@ModelAttribute HistoricoNotasDto idHistorico, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
			List<NotasCurso> historicoNotas = cursoService.findHistoricoNotas(idHistorico.getIdHistorico());
			model.addAttribute("historicoNotas", historicoNotas.isEmpty()?null:historicoNotas);
		return "historicoNotas";
	}

	@GetMapping("/eliminarCurso")
	public String deleteProductoById(@ModelAttribute Curso curso, HttpServletResponse response, Model model)
			throws ParseException {

		cursoService.desactivarCurso(curso);
		CursosList = cursoService.findAll();
		model.addAttribute("cursos", CursosList);
		return "cursos";

	}
	
	@PostMapping("/agregarPersona")
	public String agregarPersona(@RequestParam String idCurso,@RequestParam String idPersona) {

		String url = "redirect:/404.html";
	
			url = "index";
	
		return url;
	}	
	
	@GetMapping("/editarCurso")
	public String editarCursoById(@ModelAttribute CursoDto curso, HttpServletResponse response,
			Model model) throws ParseException {	
		Curso cursoMostrar = new Curso();
		cursoMostrar = cursoService.findCursoById(curso.getId());
		cursoMostrar.setId(curso.getId());
//		cursoMostrar.setFechaInicio(cursoService.ParseFecha(curso.getFechaInicio()));
//		cursoMostrar.setFechaFin(cursoService.ParseFecha(curso.getFechaFin()));
		cursoMostrar.setNombreCurso(curso.getNombreCurso());
		cursoMostrar.setValorTotal(curso.getValorTotal());
		
		curso.setFechaInicio(cursoService.formatFecha(curso.getFechaInicio()));
		curso.setFechaFin(cursoService.formatFecha(curso.getFechaFin()));

		personasList= personaService.findAllUsuarios();
		model.addAttribute("personas", personasList);
		model.addAttribute("curso", cursoMostrar);
		
		return "edit-curso";

	}
	
	@GetMapping("/regresar")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		model.addAttribute("pago", false);
		model.addAttribute("consolidacion", false);
		return "personasCurso";
	}
	
	@PostMapping("/buscarCursosByPersona")
	public String buscarCursosByPersona(@ModelAttribute Persona persona,HttpServletResponse response, Model model) {
		List<CursoDto> CursosList =  cursoService.findCursosDtoByIdPersona(persona.getId());
		
		model.addAttribute("consolidar", true);
		model.addAttribute("cursos", CursosList);
		return "cursosUsuario";
	}
	
	@GetMapping("/CursosPrueba")
	public String CursosPrueba(@RequestParam int id, Model model) {
		List<Curso> CursosList =  cursoService.findCursosByIdPersona(id);		
		CursosList = cursoService.findAll();
		model.addAttribute("cursos", CursosList);
		return "cursos";
	}

	@GetMapping("/redirectNotasCurso")
	public String redirectNotasCurso(@RequestParam int idCurso,  Model model) throws ParseException, JsonMappingException, JsonProcessingException {
		StringBuilder url = new StringBuilder();
		url.append("redirect:/notasCurso?idCurso=");
		url.append(idCurso);
		url.append("&nombreCurso=Curso%20pruebas%20");
		return url.toString();
	}

}