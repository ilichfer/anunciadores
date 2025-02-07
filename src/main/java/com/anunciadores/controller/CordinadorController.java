package com.anunciadores.controller;

import com.anunciadores.dto.CoordinadorDTO;
import com.anunciadores.dto.CustomMultipartFile;
import com.anunciadores.dto.TdcDto;
import com.anunciadores.dto.TdcReporteDto;
import com.anunciadores.model.Pago;
import com.anunciadores.model.Persona;
import com.anunciadores.model.Tdc;
import com.anunciadores.service.interfaces.ICordinadorService;
import com.anunciadores.service.interfaces.ICursoService;
import com.anunciadores.service.interfaces.IPersonaService;
import com.anunciadores.service.interfaces.ITdcService;
import com.anunciadores.util.UtilDate;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping
public class CordinadorController {
	@Autowired
	private ICordinadorService cordinadorService;

	@Autowired
	private ICursoService cursoService;

	@Autowired
	private IPersonaService personaService;

	@Autowired
	private UtilDate utilDate;

	List<Pago> pagoList;

	private final Logger log = LoggerFactory.getLogger(CordinadorController.class);

	@PostMapping("/buscarInformesByRangoFecha") public String buscarTdcByRangoFecha(@RequestParam Date fechaInicio,@RequestParam Date fechaFin, Model model) {
		if (fechaInicio != null && fechaFin !=null){
		List<CoordinadorDTO> reportes =	cordinadorService.findAllBetweenDates(fechaInicio,fechaFin);
		model.addAttribute("reportes", reportes);
		model.addAttribute("fechaInicio", fechaInicio);
		model.addAttribute("fechaFin", fechaFin);
		}
		return "reporteHisCordinador";
	}

}