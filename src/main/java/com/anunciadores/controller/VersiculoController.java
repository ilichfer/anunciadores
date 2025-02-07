package com.anunciadores.controller;

import com.anunciadores.dto.*;
import com.anunciadores.model.Pago;
import com.anunciadores.service.interfaces.IBibliaService;
import com.anunciadores.service.interfaces.ICordinadorService;
import com.anunciadores.service.interfaces.ICursoService;
import com.anunciadores.service.interfaces.IPersonaService;
import com.anunciadores.util.UtilDate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping
public class VersiculoController {
	@Autowired
	private IBibliaService bibliaService;

	@Autowired
	private UtilDate utilDate;

	List<Pago> pagoList;

	private final Logger log = LoggerFactory.getLogger(VersiculoController.class);



	@PostMapping("/guardarVersiculo") public String guardarVersiculo(@RequestParam Date fechaInicio,@RequestParam Date fechaFin, Model model) {
		if (fechaInicio != null && fechaFin !=null){
			List<CoordinadorDTO> reportes =	new ArrayList<>();
			model.addAttribute("reportes", reportes);
			model.addAttribute("fechaInicio", fechaInicio);
			model.addAttribute("fechaFin", fechaFin);
		}
		return "register-versiculo";
	}

	@GetMapping("/capitulosrest/")
	public ResponseEntity<CapitulosDto> capitulos(@RequestParam String lib) throws JsonMappingException, JsonProcessingException {
		CapitulosDto  caps = bibliaService.findChapters("592420522e16049f-01",lib);
		return new ResponseEntity<CapitulosDto>(caps, null, HttpStatus.ACCEPTED);
	}

}