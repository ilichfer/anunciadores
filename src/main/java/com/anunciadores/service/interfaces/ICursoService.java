package com.anunciadores.service.interfaces;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.anunciadores.dto.CursoDto;
import com.anunciadores.model.Curso;


public interface ICursoService {
	public List<Curso> findAll();

	List<Curso> findAllActive();
	List<Curso> findAllActiveByPerson(int idPersona);

	public Curso save(CursoDto curso) throws ParseException;
	public Curso desactivarCurso(Curso curso) throws ParseException;

	public Curso findCursoById(Integer id);
	
	public List<CursoDto> findCursosDtoByIdPersona(Integer id);
	public List<Curso> findCursosByIdPersona(Integer id);
	
	public Curso delete(Curso curso);

	Date ParseFecha(String fecha) throws ParseException;

	String formatFecha(String fecha) throws ParseException;
	
}
