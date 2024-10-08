package com.anunciadores.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.anunciadores.controller.ServicioController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.anunciadores.client.BibliaFeingClient;
import com.anunciadores.dto.BibliaDto;
import com.anunciadores.dto.CapituloDto;
import com.anunciadores.dto.CapitulosDto;
import com.anunciadores.dto.LibroDto;
import com.anunciadores.dto.LibrosDto;
import com.anunciadores.dto.VersiculoDto;
import com.anunciadores.dto.VersiculoResponseDto;
import com.anunciadores.dto.VersiculosDto;
import com.anunciadores.dto.VersionBiblesDto;
import com.anunciadores.service.interfaces.IBibliaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class BibliaServiceImpl implements IBibliaService {

	@Value("${token}")
	private String token;

	@Autowired
	BibliaFeingClient bibliaFeingClient;

	private Logger LOGGER = LoggerFactory.getLogger(BibliaServiceImpl.class);

	@Override
	public BibliaDto findBible(String idioma) throws JsonMappingException, JsonProcessingException {
		BibliaDto biblia = new BibliaDto();
		StringBuilder request = new StringBuilder();
		request.append("");
		request.append("?language=" + idioma);
		ResponseEntity<String> respuesta = consumirUrl(request.toString());

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		JsonNode root = mapper.readTree(respuesta.getBody());
		JsonNode data = root.path("data");
		JsonNode bible = data.path(0);
		JsonNode nombre = bible.path("name");
		biblia.setName(nombre.asText());
		JsonNode description = bible.path("description");
		biblia.setDescription(description.asText());
		JsonNode id = bible.path("id");
		biblia.setId(id.asText());
		return biblia;
	}

	@Override
	public LibrosDto findBook(String idBible) throws JsonMappingException, JsonProcessingException {
		LibrosDto librosDto = bibliaFeingClient.buscarLibro(idBible, token);
		return librosDto;
	}

	@Override
	public CapitulosDto findChapters(String idBible, String idBook)
			throws JsonMappingException, JsonProcessingException {
		CapitulosDto capitulos = bibliaFeingClient.buscarCapitulos(idBible, idBook, token);
		return capitulos;
	}

	@Override
	public VersiculosDto findIdVerses(String idBible, String idChapter)
			throws JsonMappingException, JsonProcessingException {

		VersiculosDto versiculos = bibliaFeingClient.buscarVersiculos(idBible, idChapter, token);
		return versiculos;

	}

	@Override
	public VersiculoResponseDto findVerse(String idBible, String idVerse)
			throws JsonMappingException, JsonProcessingException {

		VersiculoResponseDto versiculo = bibliaFeingClient.buscarVersiculo(idBible, idVerse, token);
		return versiculo;
	}

	private ResponseEntity<String> consumirUrl(String request) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		// example of custom header
		headers.set("api-key", token);
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		return restTemplate.exchange(request, HttpMethod.GET, entity, String.class);
	}

	@Override
	public VersiculoDto findVerseDay() throws JsonMappingException, JsonProcessingException {
		try {
/*
			VersionBiblesDto versionesbiblia = bibliaFeingClient.buscarBiblia(token);
			LibrosDto librosDto = findBook(versionesbiblia.getData().get(0).getId());

			int lib = libRamdom();
			LibroDto libro = librosDto.getData().get(lib);
			CapitulosDto capitulos = findChapters(versionesbiblia.getData().get(0).getId(), libro.getId());

			int cap = Ramdom(capitulos.getData().size());
			CapituloDto capitulo = capitulos.getData().get(cap);
			VersiculosDto versiculos = findIdVerses(versionesbiblia.getData().get(0).getId(), capitulo.getId());

			int ver = Ramdom(versiculos.getData().size());
			VersiculoDto Versiculo = versiculos.getData().get(ver);

			VersiculoResponseDto pasaje = findVerse(versionesbiblia.getData().get(0).getId(), Versiculo.getId());
			*/

			VersiculoDto pasajeError = new VersiculoDto();
			pasajeError.setContent("[27] Yo soy el Señor, Dios de toda la humanidad. ¿Hay algo imposible para mí?");
			pasajeError.setReference("Jeremías 32:27");

			return pasajeError;
		} catch (Exception e) {
			System.out.println("error de servicio de biblia");
			LOGGER.error("[findVerseDay] " + e.getMessage());
			e.printStackTrace();
			VersiculoDto pasajeError = new VersiculoDto();
			pasajeError.setContent("[27] Yo soy el Señor, Dios de toda la humanidad. ¿Hay algo imposible para mí?");
			pasajeError.setReference("Jeremías 32:27");
			throw new RuntimeException("[findVerseDay]"+e.getMessage());
		}
	}

	public int libRamdom() {
		int min = 1;
		int max = 66;

		Random random = new Random();

		int value = random.nextInt(max + min) + min;
		return value;

	}

	public int Ramdom(int max) {
		int min = 1;

		Random random = new Random();

		int value = random.nextInt(max + min) + min;
		return value;

	}

	public VersiculoDto constuirVersiculo() {

		return null;
	}

}
