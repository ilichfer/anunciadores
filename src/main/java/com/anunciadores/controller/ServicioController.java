package com.anunciadores.controller;

import com.anunciadores.dto.*;
import com.anunciadores.model.Ministerio;
import com.anunciadores.model.Pago;
import com.anunciadores.model.Persona;
import com.anunciadores.model.Tdc;
import com.anunciadores.service.interfaces.ICursoService;
import com.anunciadores.service.interfaces.IPersonaService;
import com.anunciadores.service.interfaces.IServicioService;
import com.anunciadores.service.interfaces.ITdcService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping
public class ServicioController {
	@Autowired
	private IServicioService servicioService;


	@GetMapping("/listarMinisterios") public String listarMinisterios( Model model) {
		List<Ministerio>  ministerios =servicioService.getAll();
		model.addAttribute("ministerios", ministerios);
		return "ministerios";
	}

	@GetMapping("/listarPosiciones") public String listarPosiciones(@RequestParam int idMinisterio, Model model) {
		List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterio(idMinisterio);
		model.addAttribute("listaPosiciones", ministerios);
		model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
		model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));
		return "listarPosiciones";
	}



	@PostMapping("/guardarServicio")
	public String save(@ModelAttribute ServicioDto servicio,@RequestParam Date fechaServicio, HttpServletResponse response, Model model)
			throws JsonMappingException, JsonProcessingException {
		System.out.println("entro");
		return null;
	}

}