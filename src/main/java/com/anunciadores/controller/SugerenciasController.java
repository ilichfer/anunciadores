package com.anunciadores.controller;

import com.anunciadores.dto.ActividadDto;
import com.anunciadores.model.Actividad;
import com.anunciadores.model.Mesa;
import com.anunciadores.model.Persona;
import com.anunciadores.model.Sugerencia;
import com.anunciadores.repository.IPersonaRepo;
import com.anunciadores.service.interfaces.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class SugerenciasController {
	private ISugerenciaService sugerenciaService;


	public SugerenciasController(ISugerenciaService sugerenciaService) {
		this.sugerenciaService = sugerenciaService;
	}

	@PostMapping("/sugerencia")
	public String createSugerencia(@RequestParam Integer idPersonaSugerencia, Model model) throws ParseException {

		model.addAttribute("idPersona",idPersonaSugerencia );
		model.addAttribute("fechaRegistro", cargarfechaActualBogota());
		return "register-sugerencia";

	}

	@PostMapping("/saveSugerencia")
	public String save(@ModelAttribute Sugerencia sugerencia,@RequestParam Integer idPersona, Model model) throws ParseException, JsonProcessingException {
		String url = "redirect:/404.html";
		if (sugerencia != null) {
			Sugerencia sugSave = sugerenciaService.saveSugerencia(sugerencia,idPersona);
			url = "redirect:/redirectDashboard";
		}
		return url;
	}


	private String cargarfechaActualBogota() throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
		String fechaActualStr = nowInBogota.format(formatter);
		return  fechaActualStr;
	}


}