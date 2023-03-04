package com.anunciadores.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.anunciadores.dto.PersonaDto;
import com.anunciadores.model.Persona;


public interface IPersonaService {
	public List<Persona> findAllUsuarios();

	public Persona save(Persona persona);
	
	public Persona saveAsistente(Persona persona);

	public Persona findPersonaById(Integer id);
	
	public String delete(Persona persona);

	public PersonaDto buscarByDocumento(Integer doc);
	
	public PersonaDto buscarEmail(String email);

	public List<Persona> findAllByCurso(int idCurso);

	public List<Persona> buscarTodosSinCurso(int idCurso);

	void agregarPersonaCurso(int idPersona, int idCurso);

	void eliminarPersonaCurso(int idPersona, int idCurso);
	
	void agregarPersonaActividad(int idPersona, int idActividad);
	
	public List<PersonaDto> buscarConsolidacion(List<Persona> listaPersonas);
	
	public String encriptar(String Pass);
	
}
