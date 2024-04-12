package com.anunciadores.service.interfaces;

import com.anunciadores.dto.AsignacionConsolidacionDto;
import com.anunciadores.dto.PersonaConsolidacionDto;
import com.anunciadores.model.Persona;

import java.util.List;

public interface IConsolidacionService {
	
	Boolean asignarPersonaAPadreEspiritual(int idPadreEspiritual, int idPersonaConsolidar);
	List<PersonaConsolidacionDto> listarservidoresConsolidacion(String nombreMinisterio);
	List<AsignacionConsolidacionDto> listarConsolidacionByServidor(int idServidor);void

	saveDescripcionConsolidacion(int idConsolidacion, String DescConsolidacion);
}
