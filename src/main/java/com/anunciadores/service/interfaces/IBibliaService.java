package com.anunciadores.service.interfaces;

import java.text.ParseException;
import java.util.Date;

import com.anunciadores.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


public interface IBibliaService {
	public BibliaDto findBible(String idioma) throws JsonMappingException, JsonProcessingException;
	
	public LibrosDto findBook(String idBible) throws JsonMappingException, JsonProcessingException;
	public CapitulosDto findChapters(String idBible,String idBook ) throws JsonMappingException, JsonProcessingException;
	public VersiculosDto findIdVerses(String idBible,String idChapter ) throws JsonMappingException, JsonProcessingException;
	public VersiculoResponseDto findVerse(String idBible,String idVerse) throws JsonMappingException, JsonProcessingException;
	public VersiculoDto findVerseDay() throws JsonMappingException, JsonProcessingException;
	public LibrosDto findAllBooks() throws JsonMappingException, JsonProcessingException;
	public VersiculoSaveDto saveVerseWeek(VersiculoSaveDto VersiculoSave) throws JsonProcessingException, ParseException;
	public VersiculoSaveDto buscarVersiculoSemanal() throws ParseException;
}
