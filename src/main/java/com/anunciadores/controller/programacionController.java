package com.anunciadores.controller;


import com.anunciadores.dto.MinisterioDto;
import com.anunciadores.dto.PersonaDto;
import com.anunciadores.dto.PosicionDto;
import com.anunciadores.dto.ServicioDto;
import com.anunciadores.model.Ministerio;
import com.anunciadores.model.Persona;
import com.anunciadores.service.interfaces.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping
public class programacionController {

	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IBibliaService bibliaService;

	@Autowired
	private IPagoService pagoService;


	@Autowired
	private ITdcService tdcService;

	@Autowired
	private IServicioService servicioService;
	
	List<Persona> personasList;


	@GetMapping("/listarPosiciones") public String listarPosiciones(@RequestParam int idMinisterio, Model model) {
		List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterio(idMinisterio);
		model.addAttribute("listaPosiciones", ministerios);
		model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
		model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));
		return "listarPosiciones";
	}

	@GetMapping("/eliminarMinisterio")
	public String deleteProductoById(@RequestParam int idMinisterio, HttpServletResponse response, Model model)
			throws ParseException {
		servicioService.deleteMinisterio(idMinisterio);
		List<Ministerio>  ministerios =servicioService.getAll();
		model.addAttribute("ministerios", ministerios);
		return "listarMinisterios";
	}

	@PostMapping("/guardarServicio")
	public String save(@ModelAttribute ServicioDto servicio, @RequestParam Date fechaServicio,@RequestParam int idMinisterio, HttpServletResponse response, Model model) {

		if(servicio.getEncargado().contains("0") || fechaServicio == null){
			model.addAttribute("message", "todos los campos son obligatorios");
			List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterio(idMinisterio);
			model.addAttribute("listaPosiciones", ministerios);
			model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
			model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));
			return "listarPosiciones";
		}else {
			if(servicioService.validarDuplicados(servicio)) {
				Optional<Persona> per =servicioService.validarProgramacionByFecha(servicio, fechaServicio);
				if(per.isPresent()){
					model.addAttribute("message", "el servidor "+per.get().getNombre()+" ya tiene una asignacion para esta fecha " + fechaServicio);
					List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterio(idMinisterio);
					model.addAttribute("listaPosiciones", ministerios);
					model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
					model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));
					return "listarPosiciones";
				}else{
					servicioService.saveProgramacion(servicio, fechaServicio);
					List<Ministerio> ministerios = servicioService.getAll();
					model.addAttribute("ministerios", ministerios);
					return "ministerios";
				}
			}else{
				model.addAttribute("message", "un servidor solo puede tener una asignacion por fecha" );
				List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterio(idMinisterio);
				model.addAttribute("listaPosiciones", ministerios);
				model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
				model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));
				return "listarPosiciones";
			}
		}
	}


	@PostMapping("/actualizarServicio")
	public String actualizarServicio(@ModelAttribute ServicioDto servicio, @RequestParam Date fechaServicio,@RequestParam int idMinisterio, HttpServletResponse response, Model model) {

		if(servicio.getEncargado().contains("0") || fechaServicio == null){
			model.addAttribute("message", "todos los campos son obligatorios");
			List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterio(idMinisterio);
			model.addAttribute("listaPosiciones", ministerios);
			model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
			model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));
			return "listarPosiciones";
		}else {
			if(servicioService.validarDuplicados(servicio)) {
				Optional<Persona> per =servicioService.validarActualizarProgramacionByFecha(servicio, fechaServicio, idMinisterio);
				if(per.isPresent()){
					model.addAttribute("message", "el servidor "+per.get().getNombre()+" ya tiene una asignacion para la fecha " + fechaServicio +" en otro ministerio ");
					List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterio(idMinisterio);
					model.addAttribute("listaPosiciones", ministerios);
					model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
					model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));
					return "listarPosiciones";
				}else{
					servicioService.updateProgramacion(servicio, fechaServicio);
					List<Ministerio> ministerios = servicioService.getAll();
					model.addAttribute("ministerios", ministerios);
					return "redirect:/consultarProgramacion";
				}
			}else{
				model.addAttribute("message", "un servidor solo puede tener una asignacion para esta fecha" );
				List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterio(idMinisterio);
				List<MinisterioDto>  ministeriosEditar =  servicioService.poblarPosiciones(ministerios,servicio);
				model.addAttribute("listaPosiciones", ministeriosEditar);
				model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
				model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));



				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fechaComoCadena = sdf.format(fechaServicio);


				//model.addAttribute("listaPosiciones", ministerios);
				//model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));
				model.addAttribute("fecha", fechaComoCadena );

				return "editar_programacion";
			}
		}
	}
	@PostMapping("/saveMinisterio")
	public String save(@RequestParam String nombreMinisterio, HttpServletResponse response, Model model) throws ParseException, JsonProcessingException {
		String url = "redirect:/404.html";

		if (nombreMinisterio != null) {
			Ministerio ministerioSave = servicioService.saveMinisterio(nombreMinisterio);

			List<Ministerio>  ministerios =servicioService.getAll();
			model.addAttribute("ministerios", ministerios);
			return "listarMinisterios";
		}
		return url;
	}

	@GetMapping("/redirecteditarMinisterio")
	public String redirecteditarMinisterio(@RequestParam int idMinisterio, @RequestParam String nombreMinisterio, HttpServletResponse response, Model model) throws ParseException, JsonProcessingException {
		String url = "redirect:/404.html";

		if (nombreMinisterio != null ) {
			MinisterioDto ministerioDto = new MinisterioDto();
			ministerioDto.setNombreMinisterio(nombreMinisterio);
			ministerioDto.setId(idMinisterio);
			model.addAttribute("ministerio", ministerioDto);
			return "edit-ministerio";
		}
		return url;
	}

	@PostMapping("/editarMinisterio")
	public String editarMinisterio(@ModelAttribute MinisterioDto ministerioDto, HttpServletResponse response, Model model) throws ParseException, JsonProcessingException {
		String url = "redirect:/404.html";

		if (ministerioDto != null ) {

			Ministerio ministerioSave = servicioService.saveMinisterio(ministerioDto);

			List<Ministerio>  ministerios =servicioService.getAll();
			model.addAttribute("ministerios", ministerios);
			return "listarMinisterios";
		}
		return url;
	}

	@GetMapping("/buscarPersonasSinMinisterio")
	public String buscarPersonasSinMinisterio(@RequestParam int idMinisterio, @RequestParam String nombreMinisterio, Model model) {
		List<PersonaDto> personasList = servicioService.getPeopleWithoutMinisterio(idMinisterio);

		model.addAttribute("personas", personasList);
		model.addAttribute("msj", "Personas las cuales se puede agregar al Ministerio: " + nombreMinisterio);
		model.addAttribute("titulo", "Lista de Personas no inscritas al ministerio");
		model.addAttribute("add", true);
		model.addAttribute("delete", false);
		model.addAttribute("nombreMinisterio", nombreMinisterio);
		model.addAttribute("idMinisterio", idMinisterio);
//		model.addAttribute("consolidacion", false);
		model.addAttribute("pago", false);
		return "personasSinMinisterio";
	}

	@GetMapping("/agregarPersonasAMinisterio")
	public String agregarPersonasAMinisterio(@RequestParam int idPersona, @RequestParam int idMinisterio,
									   @RequestParam String nombreMinisterio, Model model) {
		servicioService.agregarPersonaAMinisterio(idPersona, idMinisterio);
		List<PersonaDto> personasList = servicioService.findPersonaByidMnisterio(idMinisterio);
		model.addAttribute("personas", personasList);
		model.addAttribute("msj", "Personas inscritas al Ministerio: " + nombreMinisterio);
		model.addAttribute("titulo", "Lista de Personas inscritas");
		model.addAttribute("add", false);
		model.addAttribute("delete", true);
		model.addAttribute("idMinisterio", idMinisterio);
		model.addAttribute("consolidacion", false);
		return "personasMinisterio";
	}

	@GetMapping("/personasMinisterio")
	public String personasMinisterio(@RequestParam int idMinisterio, @RequestParam String nombreMinisterio, Model model) {
		List<PersonaDto> personasList = servicioService.findPersonaByidMnisterio(idMinisterio);
		boolean consolidacion =false;

		model.addAttribute("personas", personasList);
		model.addAttribute("idMinisterio", idMinisterio);
		model.addAttribute("nombreMinisterio", nombreMinisterio);
		model.addAttribute("msj", "Personas inscritas al Ministerio: " + nombreMinisterio);
		model.addAttribute("titulo", "Lista de Personas inscritas");
		model.addAttribute("add", false);
		model.addAttribute("delete", true);
		model.addAttribute("consolidacion", consolidacion);
		return "personasMinisterio";
	}

	@GetMapping("/redirectedAgregarPosicion")
	public String redirectedAgregarPosicion(@RequestParam int idMinisterio, @RequestParam String nombreMinisterio, HttpServletResponse response, Model model) throws ParseException, JsonProcessingException {
		String url = "redirect:/404.html";

		if (nombreMinisterio != null ) {
			MinisterioDto ministerioDto = new MinisterioDto();
			ministerioDto.setNombreMinisterio(nombreMinisterio);
			ministerioDto.setId(idMinisterio);
			model.addAttribute("ministerio", ministerioDto);
			return "register-posicion";
		}
		return url;
	}

	@GetMapping("/verPosiciones") public String verPosiciones(@RequestParam int idMinisterio, Model model) {
		List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterio(idMinisterio);
		model.addAttribute("listaPosiciones", ministerios);
		model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
		return "verPosiciones";
	}

	@PostMapping("/agregarPosicion")
	public String agregarPosicion(@ModelAttribute PosicionDto posicionDto, HttpServletResponse response, Model model) throws ParseException, JsonProcessingException {
		String url = "redirect:/404.html";

		if (posicionDto != null) {
			servicioService.savePosicion(posicionDto);
			List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterio(posicionDto.getIdMinisterio());
			model.addAttribute("listaPosiciones", ministerios);
			model.addAttribute("ministerio", servicioService.findByidMnisterio(posicionDto.getIdMinisterio()));
			return "verPosiciones";
		}
		return url;
	}

	@GetMapping("/redirecteditarPosicion")
	public String redirecteditarPosicion(@RequestParam int idPosicion, HttpServletResponse response, Model model) throws ParseException, JsonProcessingException {

		PosicionDto posicion = servicioService.findPosicion(idPosicion);
		model.addAttribute("posicion", posicion);
		return "editar-posicion";
	}

	@PostMapping("/editarPosicion")
	public String editarPosicion(@ModelAttribute PosicionDto posicionDto, HttpServletResponse response, Model model) throws ParseException, JsonProcessingException {
		String url = "redirect:/404.html";

		if (posicionDto != null) {
			servicioService.editPosicion(posicionDto);
			List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterio(posicionDto.getIdMinisterio());
			model.addAttribute("listaPosiciones", ministerios);
			model.addAttribute("ministerio", servicioService.findByidMnisterio(posicionDto.getIdMinisterio()));
			return "verPosiciones";
		}
		return url;
	}

}
