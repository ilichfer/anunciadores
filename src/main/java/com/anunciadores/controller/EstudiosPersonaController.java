package com.anunciadores.controller;

import com.anunciadores.dto.CursoDto;
import com.anunciadores.dto.PagoDto;
import com.anunciadores.dto.ReportePagoDto;
import com.anunciadores.model.Curso;
import com.anunciadores.model.EstudioPersona;
import com.anunciadores.model.Pago;
import com.anunciadores.model.Persona;
import com.anunciadores.service.interfaces.ICursoService;
import com.anunciadores.service.interfaces.IPagoService;
import com.anunciadores.service.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class EstudiosPersonaController {
	@Autowired
	private IPersonaService personaService;

	@GetMapping("/estudioPersona")
	public String estudioPersona(@ModelAttribute Persona persona, HttpServletResponse response, Model model) {
		Persona per = personaService.findPersonaById(persona.getId());
		List<EstudioPersona> estudios = personaService.findEstudiosPersona(per.getId());
		model.addAttribute("persona", per);
		model.addAttribute("estudios", estudios);
		return "estudios-personas";

	}
	@PostMapping("/saveEstudio")
	public String estudioPersona(@ModelAttribute EstudioPersona estudio, HttpServletResponse response, Model model) {
		 personaService.saveEstudio(estudio);
		Persona per = personaService.findPersonaById(estudio.getIdPersona());
		List<EstudioPersona> estudios = personaService.findEstudiosPersona(per.getId());
		model.addAttribute("persona", per);
		model.addAttribute("estudios", estudios);
		return "estudios-personas";

	}

}