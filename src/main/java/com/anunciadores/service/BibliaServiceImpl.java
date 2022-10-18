package com.anunciadores.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.anunciadores.dto.BibliaDto;
import com.anunciadores.dto.CapituloDto;
import com.anunciadores.dto.LibroDto;
import com.anunciadores.dto.VersiculoDto;
import com.anunciadores.service.interfaces.IBibliaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class BibliaServiceImpl implements IBibliaService {

	private static String url = "https://api.scripture.api.bible/v1/bibles";
	private static String token = "1f9d97c52fb351d56a1bc7ffe1140e58";

	@Override
	public BibliaDto findBible(String idioma) throws JsonMappingException, JsonProcessingException {
		BibliaDto biblia = new BibliaDto();
		StringBuilder request = new StringBuilder();
		request.append(url);
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
	public List<LibroDto> findBook(String idBible) throws JsonMappingException, JsonProcessingException {
		List<LibroDto> libros = new ArrayList<LibroDto>();
		StringBuilder request = new StringBuilder();
		request.append(url);
		request.append("/" + idBible + "/books");

		ResponseEntity<String> respuesta = consumirUrl(request.toString());

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(respuesta.getBody());
		JsonNode data = root.path("data");
		System.out.println(data.size());
		for (int i = 0; i < data.size(); i++) {
			LibroDto libro = new LibroDto();
			JsonNode bible = data.path(i);
			libro.setName(bible.path("name").asText());
			libro.setBibleId(bible.path("bibleId").asText());
			libro.setAbbreviation(bible.path("abbreviation").asText());
			libro.setNameLong(bible.path("nameLong").asText());
			libro.setId(bible.path("id").asText());

			libros.add(libro);
		}

		return libros;
	}

	@Override
	public List<CapituloDto> findChapters(String idBible, String idBook)
			throws JsonMappingException, JsonProcessingException {
		StringBuilder request = new StringBuilder();
		request.append(url);
		request.append("/" + idBible + "/books/" + idBook + "/chapters");
		ResponseEntity<String> respuesta = consumirUrl(request.toString());
		List<CapituloDto> capitulos = new ArrayList<CapituloDto>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(respuesta.getBody());
		JsonNode data = root.path("data");

		for (int i = 0; i < data.size(); i++) {
			CapituloDto capitulo = new CapituloDto();
			JsonNode bible = data.path(i);
			capitulo.setId(bible.path("id").asText());
			capitulo.setBibleId(bible.path("bibleId").asText());
			capitulo.setBookId(bible.path("bookId").asText());
			capitulo.setNumber(bible.path("number").asText());
			capitulo.setReference(bible.path("reference").asText());

			capitulos.add(capitulo);
		}
		
		return capitulos;
	}

	@Override
	public List<VersiculoDto> findIdVerses(String idBible, String idChapter)
			throws JsonMappingException, JsonProcessingException {
		
		StringBuilder request = new StringBuilder();
		request.append(url);
		request.append("/" + idBible + "/chapters/" + idChapter + "/verses");
		ResponseEntity<String> respuesta = consumirUrl(request.toString());
		List<VersiculoDto> versiculos = new ArrayList<VersiculoDto>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(respuesta.getBody());
		JsonNode data = root.path("data");

		for (int i = 0; i < data.size(); i++) {
			VersiculoDto versiculo = new VersiculoDto();
			JsonNode bible = data.path(i);
			versiculo.setId(bible.path("id").asText());
			versiculo.setOrgId(bible.path("orgId").asText());
			versiculo.setBibleId(bible.path("bibleId").asText());
			versiculo.setBookId(bible.path("bookId").asText());
			versiculo.setChapterId(bible.path("chapterId").asText());
			versiculos.add(versiculo);
		}
		return versiculos;
		
	}

	@Override
	public VersiculoDto findVerse(String idBible, String idVerse)
			throws JsonMappingException, JsonProcessingException {
		
		StringBuilder request = new StringBuilder();
		request.append(url);
		request.append("/" + idBible + "/verses/"+idVerse+"?content-type=text");
		ResponseEntity<String> respuesta = consumirUrl(request.toString());
		List<VersiculoDto> versiculos = new ArrayList<VersiculoDto>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(respuesta.getBody());
		JsonNode pasaje = root.path("data");

		VersiculoDto versiculo = new VersiculoDto();
		versiculo.setId(pasaje.path("id").asText());
		versiculo.setOrgId(pasaje.path("orgId").asText());
		versiculo.setBibleId(pasaje.path("bibleId").asText());
		versiculo.setBookId(pasaje.path("bookId").asText());
		versiculo.setChapterId(pasaje.path("chapterId").asText());
		versiculo.setContent(pasaje.path("content").asText());
		versiculo.setReference(pasaje.path("reference").asText());
		
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

		BibliaDto biblia = findBible("spa");
		List<LibroDto> libros = findBook(biblia.getId());
		
		int lib = libRamdom();
		LibroDto libro = libros.get(lib);

		List<CapituloDto> capitulos = findChapters(biblia.getId(), libro.getId());
		
		int cap = Ramdom(capitulos.size());
		CapituloDto capitulo = capitulos.get(cap);
		List<VersiculoDto> versiculos = findIdVerses(biblia.getId(), capitulo.getId());

		int ver = Ramdom(versiculos.size());
		VersiculoDto Versiculo = versiculos.get(ver);
		
		VersiculoDto pasaje = findVerse(biblia.getId(), Versiculo.getId());
		return pasaje;
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

}
