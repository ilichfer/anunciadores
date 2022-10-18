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

import com.anunciadores.dto.CursoDto;
import com.anunciadores.model.Curso;
import com.anunciadores.service.interfaces.ICursoService;

@Controller
@RequestMapping
public class CursoController {
	@Autowired
	private ICursoService cursoService;

	List<Curso> CursosList;

	@GetMapping("/listarCursos")
	public String Cursos(HttpServletResponse response, Model model) {
		CursosList = cursoService.findAll();
		model.addAttribute("cursos", CursosList);
		return "cursos";
	}

	@GetMapping("/buscarCurso/{id}")
	public ResponseEntity<Object> getProductoById(@PathVariable Integer id) {

		return ResponseEntity.ok(cursoService.findProductoById(id));

	}

	@PostMapping("/saveCurso")
	public String save(@ModelAttribute CursoDto Curso) throws ParseException {
//		Curso per = cursoService.findCursoByEmail(Curso.getEmail());
		String url = "redirect:/404.html";
		if (Curso != null) {
			Curso CursoSave = cursoService.save(Curso);
			url = "redirect:/index2.html";
		}
		return url;
	}

	@GetMapping("/eliminarCurso")
	public String deleteProductoById(@ModelAttribute Curso curso, HttpServletResponse response,
			Model model) {	
		cursoService.delete(curso);
		return "redirect:/listar";

	}
	
	@PostMapping("/agregarPersona")
	public String agregarPersona(@RequestParam String idCurso,@RequestParam String idPersona) {

		String url = "redirect:/404.html";
	
			url = "redirect:/index2.html";
	
		return url;
	}	
	
	@GetMapping("/editarCurso")
	public String editarCursoById(@ModelAttribute CursoDto curso, HttpServletResponse response,
			Model model) throws ParseException {	
		Curso cursoMostrar = new Curso();
//		cursoService.save(curso);
		System.out.println("fechaInicio para modificacion " + curso.getFechaInicio());
		System.out.println("fechaFin para modificacion " + curso.getFechaFin());
		cursoMostrar.setId(curso.getId());
//		cursoMostrar.setFechaInicio(cursoService.ParseFecha(curso.getFechaInicio()));
//		cursoMostrar.setFechaFin(cursoService.ParseFecha(curso.getFechaFin()));
		cursoMostrar.setNombreCurso(curso.getNombreCurso());
		cursoMostrar.setValorTotal(curso.getValorTotal());
		
		curso.setFechaInicio(cursoService.formatFecha(curso.getFechaInicio()));
		curso.setFechaFin(cursoService.formatFecha(curso.getFechaFin()));
		model.addAttribute("curso", curso);
		
		return "edit-curso";

	}
	
	@GetMapping("/regresar")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "personasCurso";
	}

}