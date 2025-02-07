package com.anunciadores.controller;


import com.anunciadores.dto.*;
import com.anunciadores.model.Curso;
import com.anunciadores.service.interfaces.IBibliaService;
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
public class BibliaRestController {

	@Autowired
	private IBibliaService bibliaService;

	@GetMapping("/buscarVersiculo") public String buscarVersiculo( Model model) throws JsonProcessingException {
		model.addAttribute("libros", bibliaService.findAllBooks().getData());
		model.addAttribute("capitulos", null);
		model.addAttribute("versiculos", null);
		return "register-versiculo";
	}

	@GetMapping("/capitulosFind")
	public ResponseEntity<List<CapituloDto>> capitulos(@RequestParam String lib) throws JsonMappingException, JsonProcessingException {
		CapitulosDto  caps = bibliaService.findChapters("592420522e16049f-01",lib);
		return new ResponseEntity<List<CapituloDto>>(caps.getData(), null, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/versiculosrests")
	public ResponseEntity<List<VersiculoDto>> versiculos(@RequestParam String capSelecionado) throws JsonMappingException, JsonProcessingException {
		VersiculosDto versiculosEncontrado = bibliaService.findIdVerses("592420522e16049f-01",capSelecionado);
		return new ResponseEntity<List<VersiculoDto>>(versiculosEncontrado.getData(), null, HttpStatus.ACCEPTED);
	}

	@GetMapping("/versiculorests")
	public ResponseEntity<VersiculoDto> versiculo(@RequestParam String verSelecionado) throws JsonMappingException, JsonProcessingException {
		VersiculoResponseDto versiculosEncontrado = bibliaService.findVerse("592420522e16049f-01",verSelecionado);
		return new ResponseEntity<VersiculoDto>(versiculosEncontrado.getData(), null, HttpStatus.ACCEPTED);
	}
	@GetMapping("/capitulosWeb") public String capitulosWeb(@RequestParam String lib, Model model) throws JsonProcessingException {
		model.addAttribute("libros", bibliaService.findAllBooks().getData());
		CapitulosDto caps= bibliaService.findChapters("592420522e16049f-01",lib);
		model.addAttribute("capitulos", caps.getData());
		model.addAttribute("versiculos", null);
		return "register-versiculo";
	}

	@PostMapping("/saveVersiculo")
	public String save(@ModelAttribute VersiculoSaveDto versiculoSave, HttpServletResponse response, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
		String url = "register-versiculo";
		model.addAttribute("msj", "su versiculo ha sido guardado exitosamente ");
		if (versiculoSave != null) {
			VersiculoSaveDto ver = bibliaService.saveVerseWeek(versiculoSave);
			if (ver != null && ver.getTitle() != null){

				model.addAttribute("libros", bibliaService.findAllBooks().getData());
			}
		}
		return url;
	}

	@GetMapping("/versiculoSemanal")
	public String versiculoSemanal(@RequestParam String lib, Model model) throws JsonProcessingException, ParseException {
		 bibliaService.buscarVersiculoSemanal();
		model.addAttribute("libros", bibliaService.findAllBooks().getData());
		CapitulosDto caps= bibliaService.findChapters("592420522e16049f-01",lib);
		model.addAttribute("capitulos", caps.getData());
		model.addAttribute("versiculos", null);
		return "register-versiculo";
	}
	
}
