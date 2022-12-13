package com.anunciadores.service.interfaces;

import java.util.List;

import com.anunciadores.dto.BibliaDto;
import com.anunciadores.dto.CapituloDto;
import com.anunciadores.dto.CapitulosDto;
import com.anunciadores.dto.LibroDto;
import com.anunciadores.dto.LibrosDto;
import com.anunciadores.dto.VersiculoDto;
import com.anunciadores.dto.VersiculoResponseDto;
import com.anunciadores.dto.VersiculosDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


public interface IBibliaService {
	public BibliaDto findBible(String idioma) throws JsonMappingException, JsonProcessingException;
	
	public LibrosDto findBook(String idBible) throws JsonMappingException, JsonProcessingException;
	public CapitulosDto findChapters(String idBible,String idBook ) throws JsonMappingException, JsonProcessingException;
	public VersiculosDto findIdVerses(String idBible,String idChapter ) throws JsonMappingException, JsonProcessingException;
	public VersiculoResponseDto findVerse(String idBible,String idVerse) throws JsonMappingException, JsonProcessingException;
	public VersiculoDto findVerseDay() throws JsonMappingException, JsonProcessingException;
}
