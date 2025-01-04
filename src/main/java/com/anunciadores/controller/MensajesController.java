package com.anunciadores.controller;

import com.anunciadores.dto.CursoDto;
import com.anunciadores.dto.MensajesDTO;
import com.anunciadores.dto.PersonaDto;
import com.anunciadores.model.Curso;
import com.anunciadores.model.Persona;
import com.anunciadores.model.Sugerencia;
import com.anunciadores.service.interfaces.IMensajeService;
import com.anunciadores.service.interfaces.IPersonaService;
import com.anunciadores.service.interfaces.ISugerenciaService;
import com.anunciadores.util.UtilDate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping
public class MensajesController {
	private IMensajeService mensajeService;
	@Autowired
	private IPersonaService personaService;

	@Autowired
	private UtilDate utilDate;
	public MensajesController(IMensajeService mensajeService) {
		this.mensajeService = mensajeService;
	}


	@GetMapping("/cargarMensajes")
	public String cargarMensajes( @RequestParam int idPersonaMensajes, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		String url = "bandeja-mensajes";

		List<MensajesDTO> mensajes = mensajeService.buscarMensaje(idPersonaMensajes);
		model.addAttribute("mensajes", mensajes);
		model.addAttribute("idPersona", idPersonaMensajes);
		return url;
	}

	@GetMapping("/cargarMensajesP")
	public String cargarMensajesP( HttpServletRequest request, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
		String url = "bandeja-mensajes";
		HttpSession misession = request.getSession();

		try {
			int  idpersona = (int) misession.getAttribute("idPersona");
			List<MensajesDTO> mensajes = mensajeService.buscarTodosMensaje(idpersona);
			model.addAttribute("mensajes", mensajes);
			model.addAttribute("idPersona", idpersona);
		}catch (Exception e ){
			return "sesionFinalizada";
		}

		return url;
	}

	@GetMapping("/leerMensaje")
	public String leerMensaje(@RequestParam Integer idMensaje, Model model) throws ParseException {
		model.addAttribute("mensaje",mensajeService.buscarMensajeXId(idMensaje));
		return "lector-mensaje";

	}


	private String cargarfechaActualBogota() throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
		String fechaActualStr = nowInBogota.format(formatter);
		return  fechaActualStr;
	}

	@GetMapping("/nuevoMensaje")
	public String nuevoMensaje(HttpServletRequest request,Model model) {
		HttpSession misession = request.getSession();
		try{
			int idpersona = (int) misession.getAttribute("idPersona");
			model.addAttribute("persona", personaService.findPersonaById(idpersona));
			model.addAttribute("personas", personaService.findAllUsuarios());
			return "nuevo-mensaje";
		}catch (Exception e ){
			return "sesionFinalizada";
		}
	}

	@PostMapping("/enviarMensaje")
	public String enviarMensaje(@ModelAttribute MensajesDTO mensaje,@RequestParam Integer remitente,@RequestParam Integer destinatario, HttpServletRequest request,HttpServletResponse response, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
		String url = "bandeja-mensajes";
		try {
			MensajesDTO mensajeSave = null;
			mensaje.setActivo(true);
			mensaje.setFechaRegistro(utilDate.cargarfechaActualBogotaDate());
			if (destinatario == 0) {
				mensajeService.enviarTodosMensajes(mensaje);
			} else {
				mensajeSave = mensajeService.guardarMensaje(mensaje);
			}

			List<MensajesDTO> mensajes = mensajeService.buscarTodosMensaje(remitente);
			model.addAttribute("mensajes", mensajes);
			model.addAttribute("idPersona", remitente);
		}catch (Exception e ){
			return "sesionFinalizada";
		}
		return url;
	}


}