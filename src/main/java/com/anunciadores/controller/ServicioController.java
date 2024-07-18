package com.anunciadores.controller;

import com.anunciadores.dto.MinisterioDto;
import com.anunciadores.dto.ServicioListResponseDto;
import com.anunciadores.model.Persona;
import com.anunciadores.service.interfaces.IServicioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping
public class ServicioController {

	private Logger LOGGER = LoggerFactory.getLogger(ServicioController.class);

	@Autowired
	private IServicioService servicioService;

	@GetMapping("/consultarProgramacion")
	public String consultarProgramacion(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
		String url = "buscarServicio";
		List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDateGroup(Date.valueOf(LocalDate.now()));
		//List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));
		if(listProgramacionMinisterio.size()>0) {
			//model.addAttribute("programacion", listProgramacionMinisterio);
			model.addAttribute("programacionMin", listProgramacionMinisterio);
		}else{
			model.addAttribute("programacionMin", null);
		}
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return url;
	}
	@GetMapping("/editarProgramacion")
	public String editarProgramacion(@RequestParam Date fecha, @RequestParam int idMinisterio, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		String url = "editar_programacion";
		List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterioAndPerson(fecha,idMinisterio);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaComoCadena = sdf.format(fecha);


		model.addAttribute("listaPosiciones", ministerios);
		model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
		model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));
		model.addAttribute("fecha", fechaComoCadena );
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return url;
	}

	@PostMapping("/buscarProgramacionByFecha")
	public String buscarProgramacionByFecha(@RequestParam Date fecha, Model model) {
		String url = "buscarServicio";
		List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDate(fecha);
		//List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));
		if(listProgramacionMinisterio.size()>0) {
			//model.addAttribute("programacion", listProgramacionMinisterio);
			model.addAttribute("programacionMin", listProgramacionMinisterio);
		}else{
			model.addAttribute("programacionMin", null);
			model.addAttribute("fechaBuscada", fecha);
		}
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return url;
	}


}
