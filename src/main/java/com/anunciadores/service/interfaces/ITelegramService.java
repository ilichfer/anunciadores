package com.anunciadores.service.interfaces;

import com.anunciadores.dto.*;
import com.anunciadores.model.Persona;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


public interface ITelegramService {
	public List<Persona> getUpdatesWithContact() throws JsonMappingException, JsonProcessingException;
	public List<Persona> getContactTelegram() throws JsonMappingException, JsonProcessingException;
	public int getUpdatesWithOutContact() throws JsonMappingException, JsonProcessingException;
	public List<ServicioResponseDto> sendNotification() throws JsonMappingException, JsonProcessingException, ParseException;
	public ResponseTelegram getContact(String mensaje) throws JsonMappingException, JsonProcessingException;


}
