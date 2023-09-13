package com.anunciadores.controller;

import com.anunciadores.dto.*;
import com.anunciadores.model.Curso;
import com.anunciadores.model.Pago;
import com.anunciadores.model.Tdc;
import com.anunciadores.service.interfaces.ICursoService;
import com.anunciadores.service.interfaces.IPagoService;
import com.anunciadores.service.interfaces.IPersonaService;
import com.anunciadores.service.interfaces.ITdcService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping
public class TdcController {
	@Autowired
	private ITdcService tdcService;
	
	@Autowired
	private ICursoService cursoService;

	@Autowired
	private IPersonaService personaService;

	List<Pago> pagoList;

	@GetMapping("/uploadimage") public String displayUploadForm() {
		return "imageupload/index";
	}

	@PostMapping("/upload") public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
		try {
		System.out.println("entro a cargar imagen");
		Tdc picture = new Tdc();
		if (tdcService.getTdcByFechaAndPersona(Date.valueOf(LocalDate.now()),1)) {
			String encodedString = Base64.getEncoder().encodeToString(file.getBytes());
			picture.setTdc(encodedString);
			picture.setFechaCreacion(Date.valueOf(LocalDate.now()));
			picture.setIdPersona(1);
			picture.setNombredocumento("TDC_" + Date.valueOf(LocalDate.now()));
			tdcService.save(picture);
			model.addAttribute("message", "su Imagen ha sido cargada correctamente");
			model.addAttribute("msg", null);
		}else {
			model.addAttribute("message", "ud ya a cargardo su TDC del dia de hoy");
			model.addAttribute("msg", null);
		}
		} catch (Exception e) {
			model.addAttribute("message", null);
			model.addAttribute("msg", "Error al cargar imagen");
			System.out.println("Error :: en cargar imagen");
			return  "registerTDC";

		}
		return  "registerTDC";
		}

	@GetMapping("/viewImage") public String getProductoById(@RequestParam int idTdc, Model model) {
		String image=	tdcService.getById(idTdc);

		model.addAttribute("image", image);
			return "visualizarTDC";
		}

	@GetMapping("/listarTdc") public String getdcById( Model model) {
		List<TdcDto> listaTdc =	tdcService.getTdcByFecha(Date.valueOf(LocalDate.now()));
		model.addAttribute("listaTdc", listaTdc);
		return "listarTdc";
	}

	@GetMapping("/consutarPdf")
	public ResponseEntity<Object> consutarEmail(HttpServletResponse response,Model model) throws JsonMappingException, JsonProcessingException {
		Tdc image=	tdcService.getTdcById(1);

		return new ResponseEntity<Object>(image, null, HttpStatus.ACCEPTED);
	}

	@PostMapping("/buscarTdcByFecha") public String buscarTdcByFecha(@RequestParam Date fecha, Model model) {
		List<TdcDto> listaTdc =	tdcService.getTdcByFecha(fecha);
		model.addAttribute("listaTdc", listaTdc);
		return "listarTdc";
	}

	@PostMapping("/buscarTdcByRangoFecha") public String buscarTdcByRangoFecha(@RequestParam Date fechaInicio,@RequestParam Date fechaFin, Model model) {
		if (fechaInicio != null && fechaFin !=null){
		List<TdcReporteDto> listaTdc =	tdcService.findAllBetweenDates(fechaInicio,fechaFin);
		model.addAttribute("listaTdc", listaTdc);}
		return "reporteTdc";
	}

	@PostMapping("/datalleTdcByPersona") public String datalleTdcByPersona(@RequestParam int idPersona, @RequestParam Date fechaInicio,@RequestParam Date fechaFin, Model model) {
		if(fechaInicio != null && fechaFin  != null && idPersona != 0){
		List<TdcDto> listaTdc =	tdcService.findAllBetweenDatesByPersona(fechaInicio,fechaFin,idPersona);
		model.addAttribute("listaTdc", listaTdc);
		model.addAttribute("idPersona", 1);}
		return "reporteTdcPersona";
	}



}