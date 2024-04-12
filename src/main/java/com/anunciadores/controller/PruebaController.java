package com.anunciadores.controller;

import com.anunciadores.client.BibliaFeingClient;
import com.anunciadores.client.PruebaFeingClient;
import com.anunciadores.dto.CustomMultipartFile;
import com.anunciadores.dto.TdcDto;
import com.anunciadores.dto.TdcReporteDto;
import com.anunciadores.model.Pago;
import com.anunciadores.model.Tdc;
import com.anunciadores.service.interfaces.ICursoService;
import com.anunciadores.service.interfaces.IPersonaService;
import com.anunciadores.service.interfaces.ITdcService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping
public class PruebaController {
	@Autowired
	PruebaFeingClient pruebaFeingClient;


	@GetMapping("/consultar") public String getProductoById(@RequestParam int idTdc, Model model) {
		Object[] pru =pruebaFeingClient.buscarUrl("Bearer 00be24a7-f16c-36b2-bc3a-c5a2661b3c43","2021-01-16T15:00:00",
				"Dispensacion","SIE000000618");
		System.out.println(pru);
			return "visualizarTDC";
		}





}