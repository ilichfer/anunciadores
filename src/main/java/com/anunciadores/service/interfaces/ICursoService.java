package com.anunciadores.service.interfaces;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.anunciadores.dto.CursoDto;
import com.anunciadores.dto.PersonaDto;
import com.anunciadores.model.Curso;
import com.anunciadores.model.NotasCurso;
import com.anunciadores.model.Persona;


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
	public NotasCurso findNotasByCurso(int curso,int idPersona);
	public List<NotasCurso> findHistoricoNotas(int idPersona);

	public NotasCurso saveNotasCurso(NotasCurso notas) throws ParseException;

	Date ParseFecha(String fecha) throws ParseException;

	String formatFecha(String fecha) throws ParseException;

	List<PersonaDto> buscarNotasXPersonas(int idCurso,List<Persona> estudiantes);
	
}
