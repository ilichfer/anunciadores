package com.anunciadores.controller;

import com.anunciadores.dto.*;
import com.anunciadores.model.Pago;
import com.anunciadores.model.Persona;
import com.anunciadores.model.Tdc;
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
public class TdcController {
	@Autowired
	private ITdcService tdcService;

	@Autowired
	private ICursoService cursoService;

	@Autowired
	private IPersonaService personaService;

	@Autowired
	private UtilDate utilDate;

	List<Pago> pagoList;

	private final Logger log = LoggerFactory.getLogger(TdcController.class);

	@GetMapping("/uploadimage") public String displayUploadForm() {
		return "imageupload/index";
	}

	@PostMapping("/upload") public String uploadImage(@ModelAttribute Persona persona, Model model, @RequestParam("image") MultipartFile file) throws IOException {
		log.info("cargar imagenes inicio");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
		String fecha = nowInBogota.format(formatter);
		model.addAttribute("fecha", fecha);
		try {
			model.addAttribute("idPersona", persona.getId());
			Tdc picture = new Tdc();
			DateTimeFormatter formatterSAve = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Bogota"));

			String fechaformateada = now.format(formatterSAve);
			Date fechafinal = Date.valueOf(fechaformateada);
			String extension = null;
			if(file != null && file.getSize()>0)
			{
				 extension = FilenameUtils.getExtension(file.getOriginalFilename());
				System.out.println(extension);

				log.info("tamaño del archivo es de "+file.getSize()+" bytes");
				log.info("fecha a usar: "+fechafinal);
			} else{
				model.addAttribute("messageError", "Debe seleccionar una foto o documento de su tiempo con Dios");
				return  "registerTDC";
			}
			if(extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("jpg")  || extension.equalsIgnoreCase("png")|| extension.equalsIgnoreCase("jfif")) {
				BufferedImage image = ImageIO.read(file.getInputStream());
				BufferedImage originalImage = tdcService.resizeImage(image, 480, 1024);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(originalImage, "jpg", baos);
				baos.flush();

				CustomMultipartFile customMultipartFile = new CustomMultipartFile(baos.toByteArray());
				String encodedString = Base64.getEncoder().encodeToString(customMultipartFile.getBytes());
				picture.setTdc(encodedString);
				picture.setPdf(false);
				picture.setImagen(true);
			}
			if(extension.equalsIgnoreCase("pdf") ) {
				picture.setTdc(Base64.getEncoder().encodeToString(file.getBytes()));
				picture.setPdf(true);
				picture.setImagen(false);
			}
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String fechaformat2 = nowInBogota.format(formatter2);
			model.addAttribute("fecha", fechaformat2);
		if (tdcService.getTdcByFechaAndPersona(fechafinal, persona.getId())) {

			picture.setFechaCreacion(fechafinal);
			picture.setIdPersona(persona.getId());
			picture.setNombredocumento("TCD_" + fechafinal);
			log.debug("se va a guardar imagen debug");
			log.info("se va a guardar imagen info");
			tdcService.save(fechafinal,picture);
			model.addAttribute("message", "su Imagen ha sido cargada correctamente");
			model.addAttribute("msg", null);
		}else {
			model.addAttribute("messageError", "ud ya a cargardo su TCD del dia de hoy");
			model.addAttribute("msg", null);
		}
		} catch (Exception e) {
			log.error("error cargar imagen: ", e);
			model.addAttribute("message", null);
			model.addAttribute("messageError", "Error al cargar imagen");
			return  "registerTDC";

		}
		return  "registerTDC";
		}



	@GetMapping("/listarTdc") public String getdcById( Model model) throws ParseException {
		List<TdcDto> listaTdc =	tdcService.getTdcByFecha(utilDate.cargarfechaActualBogotaDate());
		model.addAttribute("listaTdc", listaTdc);
		return "listarTdc";
}

	@GetMapping("/consutarPdf")
	public ResponseEntity<Object> consutarEmail(HttpServletResponse response,Model model)  {
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
		model.addAttribute("listaTdc", listaTdc);
		model.addAttribute("fechaInicio", fechaInicio);
		model.addAttribute("fechaFin", fechaFin);
		}
		return "reporteTdc";
	}

	@PostMapping("/datalleTdcByPersona") public String datalleTdcByPersona(@RequestParam int idPersona, @RequestParam Date fechaInicio,@RequestParam Date fechaFin, Model model) {
		if(fechaInicio != null && fechaFin  != null && idPersona != 0){
		List<TdcDto> listaTdc =	tdcService.findAllBetweenDatesByPersona(fechaInicio,fechaFin,idPersona);
		model.addAttribute("listaTdc", listaTdc);
		model.addAttribute("idPersona", idPersona);}
		return "reporteTdcPersona";
	}

	@GetMapping("/viewImageIndivudual") public String viewImageIndivudual(@RequestParam int idTdc, Model model) {
		Tdc tcdObject=	tdcService.getById(idTdc);
		Persona persona = personaService.findPersonaById(tcdObject.getIdPersona());
		model.addAttribute("tcd", tcdObject);
		model.addAttribute("persona", persona);
		return "visualizarTCDIndividual";
	}

	@GetMapping("/viewImageFilter") public String viewImageFilter(@RequestParam int idTdc, Model model) {
		Tdc tcdObject=	tdcService.getById(idTdc);
		Persona persona = personaService.findPersonaById(tcdObject.getIdPersona());
		model.addAttribute("tcd", tcdObject);
		model.addAttribute("persona", persona);
		return "visualizarTDC";
	}
}