package com.anunciadores.controller;

import java.text.ParseException;

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



	

	@GetMapping("/buscarConsolidacion/{id}")
	public ResponseEntity<Object> buscarConsolidacionById(@PathVariable Integer id) {

		return null;

	}

	@PostMapping("/saveConsolidacion")
	public String saveConsolidacion(@ModelAttribute ConsolidacionDto consolidacionDto, HttpServletResponse response, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
		String url = "redirect:/404.html";
		if (consolidacionDto != null) {
			consolidacionService.asignarPersonaAPadreEspiritual(consolidacionDto.getIdPadreEspiritual(), consolidacionDto.getIdPersonaConsolidar());

			url = "listarCursos";
		}
		return url;
	}

	@GetMapping("/eliminarConsolidacion")
	public String deleteProductoById(@ModelAttribute Mesa mesa, HttpServletResponse response,
			Model model) {	
		
		return "mesas";

	}
	

}