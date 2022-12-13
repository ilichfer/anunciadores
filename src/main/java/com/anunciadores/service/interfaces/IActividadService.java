package com.anunciadores.service.interfaces;

import java.text.ParseException;
import java.util.List;

import com.anunciadores.dto.ActividadDto;
import com.anunciadores.model.Actividad;
import com.anunciadores.model.Mesa;
import com.anunciadores.model.Persona;


public interface IActividadService {
	public List<Actividad> listarActiviades();

	public Actividad save(ActividadDto Actividad) throws ParseException;

	public Actividad findActividadById(Integer id);

	public void delete(ActividadDto actividadDto);
	
	public List<Persona> buscarTodosSinActividad(int idActividad);
	
	public List<Persona> buscarPersonasActividad(int idActividad);
	
	public List<Mesa> buscarMesasActividad(ActividadDto actividadDto);
	
}
