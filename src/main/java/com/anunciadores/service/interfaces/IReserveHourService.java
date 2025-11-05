package com.anunciadores.service.interfaces;

import com.anunciadores.dto.ResponseTelegram;
import com.anunciadores.dto.ServicioResponseDto;
import com.anunciadores.model.Persona;
import com.anunciadores.model.TimeSlot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.text.ParseException;
import java.util.List;


public interface IReserveHourService {
	public List<TimeSlot> reserveSlot();

}
