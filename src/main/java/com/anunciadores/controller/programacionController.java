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
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		ministerios = servicioService.getPositionInitial(ministerios);
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
		List<String> encargadosList = new ArrayList<>();
		List<String> posicionesList =  new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaComoCadena = sdf.format(fechaServicio);
		model.addAttribute("fecha", fechaComoCadena );

		try {
			for (int i = 0; i < servicio.getEncargado().size() ; i++) {
				if (!servicio.getEncargado().get(i).equals("0")&&!servicio.getEncargado().get(i).equals("")) {
					encargadosList.add(servicio.getEncargado().get(i));
					posicionesList.add(servicio.getPosicion().get(i));
				}

			}
			servicio.setEncargado(encargadosList);
			servicio.setPosicion(posicionesList);


			if(servicioService.validarDuplicados(servicio)) {
				Optional<Persona> per =servicioService.validarProgramacionByFecha(servicio, fechaServicio);
				if(per.isPresent()){
					model.addAttribute("message", "Servicio Guardado EXitosamente!!! \n el servidor "+per.get().getNombre()+" ya tiene una asignacion para la fecha " + fechaServicio +" en otro ministerio ");
					servicioService.updateProgramacion(servicio, fechaServicio,idMinisterio);
					List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterioAndPerson(fechaServicio,idMinisterio);
					List<MinisterioDto>  ministeriosEditar =  servicioService.poblarPosiciones(ministerios,servicio);
					model.addAttribute("listaPosiciones", ministeriosEditar);
					model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
					model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));

					return "editar_programacion";

				}else{
					servicioService.saveProgramacion(servicio, fechaServicio);
					List<Ministerio> ministerios = servicioService.getAll();
					model.addAttribute("ministerios", ministerios);
					return "ministerios";
				}
			}else{
				Persona perDobleAsignacion = servicioService.identificarDuplicados(servicio);
				model.addAttribute("message", "Servicio Guardado Exitosamente!!! \n el servidor "+perDobleAsignacion.getNombre()+" posee doble asignacion para la fecha " + fechaServicio +" en este ministerio ");
				servicioService.updateProgramacion(servicio, fechaServicio,idMinisterio);
				List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterioAndPerson(fechaServicio,idMinisterio);
				List<MinisterioDto>  ministeriosEditar =  servicioService.poblarPosiciones(ministerios,servicio);
				model.addAttribute("listaPosiciones", ministeriosEditar);
				model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
				model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));
				model.addAttribute("fecha", fechaComoCadena );

				return "editar_programacion";
			}
		}catch (Exception e) {
			model.addAttribute("message", "Error: " +e.getMessage());
			List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterio(idMinisterio);
			ministerios =  servicioService.poblarPosiciones(ministerios,servicio);
			model.addAttribute("listaPosiciones", ministerios);
			model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
			model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));
			return "listarPosiciones";
		}
	}


	@PostMapping("/actualizarServicio")
	public String actualizarServicio(@ModelAttribute ServicioDto servicio, @RequestParam Date fechaServicio,@RequestParam int idMinisterio, HttpServletResponse response, Model model) {

		List<MinisterioDto>  ministerioOriginal = servicioService.getPositionByidMinisterioAndPerson(fechaServicio,idMinisterio);

		List<String> encargadosList = new ArrayList<>();
		List<String> posicionesList =  new ArrayList<>();
		for (int i = 0; i < servicio.getEncargado().size() ; i++) {
			if (!servicio.getEncargado().get(i).equals("0")&&!servicio.getEncargado().get(i).equals("")) {
				encargadosList.add(servicio.getEncargado().get(i));
				posicionesList.add(servicio.getPosicion().get(i));
			}

		}
		servicio.setEncargado(encargadosList);
		servicio.setPosicion(posicionesList);
			if(servicioService.validarDuplicados(servicio)) {
				Optional<Persona> per =servicioService.validarActualizarProgramacionByFecha(servicio, fechaServicio, idMinisterio);
				if(per.isPresent()){
					model.addAttribute("message", "Servicio Guardado Exitosamente!!! \n el servidor "+per.get().getNombre()+" ya tiene una asignacion para la fecha " + fechaServicio +" en otro ministerio ");
					servicioService.updateProgramacion(servicio, fechaServicio,idMinisterio);
					List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterioAndPerson(fechaServicio,idMinisterio);
					List<MinisterioDto>  ministeriosEditar =  servicioService.poblarPosiciones(ministerios,servicio);
					model.addAttribute("listaPosiciones", ministeriosEditar);
					model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
					model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String fechaComoCadena = sdf.format(fechaServicio);
					model.addAttribute("fecha", fechaComoCadena );

					return "editar_programacion";
				}else{
					//servicioService.deleteProgramacion(fechaServicio);
					servicioService.updateProgramacion(servicio, fechaServicio,idMinisterio);
					List<Ministerio> ministerios = servicioService.getAll();
					model.addAttribute("ministerios", ministerios);
					return "redirect:/consultarProgramacion";
				}
			}else{
				Persona perDobleAsignacion = servicioService.identificarDuplicados(servicio);
				model.addAttribute("message", "Servicio Guardado Exitosamente!!! \n el servidor "+perDobleAsignacion.getNombre()+" posee doble asignacion para la fecha " + fechaServicio +" en este ministerio ");
				servicioService.updateProgramacion(servicio, fechaServicio,idMinisterio);
				List<MinisterioDto>  ministerios = servicioService.getPositionByidMinisterioAndPerson(fechaServicio,idMinisterio);
				List<MinisterioDto>  ministeriosEditar =  servicioService.poblarPosiciones(ministerios,servicio);
				model.addAttribute("listaPosiciones", ministeriosEditar);
				model.addAttribute("ministerio", servicioService.findByidMnisterio(idMinisterio));
				model.addAttribute("servidores", servicioService.findPersonaByidMnisterio(idMinisterio));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fechaComoCadena = sdf.format(fechaServicio);
				model.addAttribute("fecha", fechaComoCadena );

				return "editar_programacion";
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
