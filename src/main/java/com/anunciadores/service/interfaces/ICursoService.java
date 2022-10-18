package com.anunciadores.service.interfaces;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.anunciadores.dto.CursoDto;
import com.anunciadores.model.Curso;


public interface ICursoService {
	public List<Curso> findAll();

	public Curso save(CursoDto curso) throws ParseException, com.sun.el.parser.ParseException;

	public Curso findProductoById(Integer id);
	
	public Curso delete(Curso curso);

	Date ParseFecha(String fecha) throws ParseException, com.sun.el.parser.ParseException;

	String formatFecha(String fecha) throws ParseException,  com.sun.el.parser.ParseException;
	
}
