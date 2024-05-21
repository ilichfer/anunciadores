package com.anunciadores.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anunciadores.dto.PersonaDto;

@FeignClient(name = "persona" ,url = "http://44.211.227.165:8080")
public interface PersonaFeingClient {
	
	
	@GetMapping("/consutarEmail?email=")
	public PersonaDto buscarPersona(@RequestParam String email);

	@GetMapping("/consutarDoc?doc=")
	public PersonaDto consutarDoc(@RequestParam String email);

}
