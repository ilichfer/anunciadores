package com.anunciadores.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.anunciadores.dto.CapitulosDto;
import com.anunciadores.dto.LibrosDto;
import com.anunciadores.dto.VersiculoResponseDto;
import com.anunciadores.dto.VersiculosDto;
import com.anunciadores.dto.VersionBiblesDto;

@FeignClient(name = "biblia" ,url = "https://api.scripture.api.bible/v1/bibles", configuration = FeignClientConfiguration.class)
public interface BibliaFeingClient {
	
	
	@GetMapping("?language=spa")
	public VersionBiblesDto buscarBiblia(@RequestHeader(value = "api-key", required = true) String token);
	
	@GetMapping(value = "/{idBible}/books")
	public LibrosDto buscarLibro(@PathVariable("idBible") String idBible,@RequestHeader(value = "api-key", required = true) String token);
	
	@GetMapping(value = "/{idBible}/books/{idBook}/chapters")
	public CapitulosDto buscarCapitulos(@PathVariable("idBible") String idBible,@PathVariable("idBook") String idBook,@RequestHeader(value = "api-key", required = true) String token);
	
	@GetMapping(value = "/{idBible}/chapters/{idChapter}/verses")
	public VersiculosDto buscarVersiculos(@PathVariable("idBible") String idBible,@PathVariable("idChapter") String idChapter,@RequestHeader(value = "api-key", required = true) String token);
		
	@GetMapping(value = "/{idBible}/verses/{idVerse}?content-type=text")
	public VersiculoResponseDto buscarVersiculo(@PathVariable("idBible") String idBible,@PathVariable("idVerse") String idVerse,@RequestHeader(value = "api-key", required = true) String token);
	

}
