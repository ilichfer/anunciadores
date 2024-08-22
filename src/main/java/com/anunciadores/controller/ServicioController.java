package com.anunciadores.controller;

import com.anunciadores.dto.CoordinadorDTO;
import com.anunciadores.dto.MinisterioDto;
import com.anunciadores.dto.ServicioListResponseDto;
import com.anunciadores.dto.ServicioResponseDto;
import com.anunciadores.model.Coordinador;
import com.anunciadores.model.Persona;
import com.anunciadores.model.Servicio;
import com.anunciadores.service.interfaces.IPersonaService;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping
public class ServicioController {

	private Logger LOGGER = LoggerFactory.getLogger(ServicioController.class);

	@Autowired
	private IServicioService servicioService;
	@Autowired
	private IPersonaService personaService;

	@GetMapping("/consultarProgramacion")
	public String consultarProgramacion(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
		String url = "buscarServicio";
		List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDateGroup(Date.valueOf(LocalDate.now()));
		//List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));
		if(listProgramacionMinisterio.size()>0) {
			Coordinador cor =servicioService.findCoordinador(listProgramacionMinisterio);
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("programacionMin", listProgramacionMinisterio);
			model.addAttribute("coordinador", cor);
			model.addAttribute("fechaCoordinador", dt1.format(cor.getFechaServicio()));
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
	@PostMapping("/saveCoordinador")
	public String saveCoordinado(@ModelAttribute CoordinadorDTO cordinador, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		String url = "buscarServicio";

		Coordinador corSave =servicioService.findCoordinadorByFecha(cordinador.getFechaServcio());
		if (corSave != null && corSave.getId()!= 0) {
			List<Persona> personasList= personaService.findAllUsuarios();
			model.addAttribute("personas", personasList);
			model.addAttribute("msj", "Ya existe un coordinador asignado para esta fecha, " +
					"si desea editar porfavor dirijase a la seccion de edicion de coordinador");
			return "register-coordinador";
		}else{
			servicioService.saveCoordinado(cordinador);
			List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDateGroup(Date.valueOf(LocalDate.now()));
			//List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));
			if (listProgramacionMinisterio.size() > 0) {
				Coordinador cor = servicioService.findCoordinador(listProgramacionMinisterio);
				model.addAttribute("programacionMin", listProgramacionMinisterio);
				model.addAttribute("coordinador", cor);
			} else {
				model.addAttribute("programacionMin", null);
			}
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			return url;

		}
	}

	@PostMapping("/updateCoordinador")
	public String updateCoordinador(@ModelAttribute CoordinadorDTO cordinador, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		String url = "buscarServicio";
		Coordinador cor =servicioService.findCoordinadorByFecha(cordinador.getFechaServcio());
		cordinador.setId(cor.getId());

		servicioService.saveCoordinado(cordinador);

		List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDate(cordinador.getFechaServcio());
		//List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));
		if(listProgramacionMinisterio.size()>0) {
			Coordinador cord =servicioService.findCoordinador(listProgramacionMinisterio);
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("programacionMin", listProgramacionMinisterio);
			model.addAttribute("coordinador", cord);
			model.addAttribute("fechaCoordinador", dt1.format(cor.getFechaServicio()));
		}else{
			model.addAttribute("programacionMin", null);
			model.addAttribute("fechaBuscada", cordinador.getFechaServcio());
		}
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return url;
	}


	@GetMapping("/editarCoordinador")
	public String editarCoordinador(@RequestParam Date fecha, @RequestParam int idPersona, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		String url = "editar-coordinador";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaComoCadena = sdf.format(fecha);

		List<Persona>  personasList= personaService.findAllUsuarios();
		model.addAttribute("personas", personasList);
		model.addAttribute("idPersona", idPersona);
		model.addAttribute("fecha", fechaComoCadena );
		return url;
	}

	@PostMapping("/buscarProgramacionByFecha")
	public String buscarProgramacionByFecha(@RequestParam Date fecha, Model model) {
		String url = "buscarServicio";
		List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDate(fecha);
		//List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));
		if(listProgramacionMinisterio.size()>0) {
			Coordinador cor =servicioService.findCoordinador(listProgramacionMinisterio);
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("programacionMin", listProgramacionMinisterio);
			model.addAttribute("coordinador", cor);
			model.addAttribute("fechaCoordinador", dt1.format(cor.getFechaServicio()));
		}else{
			model.addAttribute("programacionMin", null);
			model.addAttribute("fechaBuscada", fecha);
		}
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return url;
	}

	@PostMapping("/miProgramacion")
	public String miProgramacion(@ModelAttribute Persona persona,Model model) throws ParseException {
		String url = "servicioMes";
		List<ServicioResponseDto> listServ = servicioService.buscarProgramacionMes(persona.getId());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaActual = sdf.format(new java.util.Date());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse(fechaActual, dtf);

		int month = ld.getMonthValue();
		model.addAttribute("listMes", listServ);
		model.addAttribute("msj", "personasList");
		model.addAttribute("mesActual", month);
		return url;
	}


}
