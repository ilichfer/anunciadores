package com.anunciadores.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping
public class WhatsappController {

	private static final String ACCESS_TOKEN = "TU_ACCESS_TOKEN";
	private static final String PHONE_NUMBER_ID = "102796769513879";
	private static final String API_URL = "https://graph.facebook.com/v18.0/" + PHONE_NUMBER_ID + "/messages";

	@PostMapping("/send")
	public ResponseEntity<String> sendNotification(@RequestBody Map<String, String> request) {
		String phoneNumber = request.get("phone");

		// 1. Crear el cliente (En Java 8/Spring usamos RestTemplate)
		RestTemplate restTemplate = new RestTemplate();

		// 2. Configurar Headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + ACCESS_TOKEN);

		// 3. Construir el cuerpo (Usando Maps para evitar errores de sintaxis en JSON)
		Map<String, Object> body = new HashMap<>();
		body.put("messaging_product", "whatsapp");
		body.put("to", phoneNumber);
		body.put("type", "template");

		Map<String, Object> template = new HashMap<>();
		template.put("name", "hello_world"); // Plantilla gratuita de prueba

		Map<String, String> language = new HashMap<>();
		language.put("code", "en_us");
		template.put("language", language);

		body.put("template", template);

		// 4. Ejecutar la petición
		try {
			HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
			ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);

			return ResponseEntity.ok("Enviado con éxito: " + response.getBody());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al conectar con Meta: " + e.getMessage());
		}
	}
	}




