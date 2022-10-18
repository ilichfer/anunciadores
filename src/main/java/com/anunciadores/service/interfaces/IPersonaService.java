package com.anunciadores.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.anunciadores.model.Persona;


public interface IPersonaService {
	public List<Persona> findAll();

	public Persona save(Persona persona);

	public Optional<Persona> findProductoById(Integer id);
	
	public Persona delete(Persona persona);

	
	public Persona buscarEmail(String email);

	public List<Persona> findAllByCurso(int idCurso);

	public List<Persona> buscarTodosSinCurso(int idCurso);

	void agregarPersonaCurso(int idPersona, int idCurso);

	void eliminarPersonaCurso(int idPersona, int idCurso);
}
