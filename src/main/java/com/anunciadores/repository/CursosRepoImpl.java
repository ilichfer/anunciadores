package com.anunciadores.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.anunciadores.model.Curso;


@Service
public class CursosRepoImpl{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Curso>cursosByIdPersona(Integer idPersona) {
		StringBuilder sql = new StringBuilder();
		Curso retorno = new Curso();
		List<Curso> cuersosList = new ArrayList<Curso>();
		try {
			sql.append("SELECT c.* FROM persona p  " 
					+ " join inscripciones ins on p.id = ins.id_persona" 
					+ " join curso c on ins.id_curso = c.id "  
					+ " where p.id= " + idPersona
					+ "");
			retorno = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Curso>() {
				@Override
				public Curso extractData(ResultSet rs) throws SQLException, DataAccessException {

					while (rs.next())
						cuersosList.add(new Curso(rs.getInt("id"), rs.getInt("idPago"), rs.getString("nombreCurso"), rs.getString("fechaInicio"), rs.getString("fechaFin"), rs.getInt("valorTotal"), rs.getBoolean("comentario"), rs.getBoolean("activo")));

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuersosList;
	}
	

	

}
