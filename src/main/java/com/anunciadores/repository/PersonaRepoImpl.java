package com.anunciadores.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.anunciadores.model.InscripcionActividad;
import com.anunciadores.model.Inscripciones;
import com.anunciadores.model.Persona;


@Service
public class PersonaRepoImpl{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IPersonaRepo PersonaRepository;
	
	@Autowired
	private InscripcionRepo inscripcionesRepository;
	
	@Autowired
	private InscripcionActividadRepo inscripcionActividadRepository;
	
	public Persona buscarEmail(String email) {
				
		StringBuilder sql = new StringBuilder();
		Persona retorno = new Persona();
		try {
			sql.append("select * from persona where email= '" + email+"'" );
			System.out.println("Consulta sql ===> "+sql.toString());

					retorno = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Persona>() {
				@Override
				public Persona extractData(ResultSet rs) throws SQLException, DataAccessException {

					while (rs.next())
						return new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("documento"), rs.getString("telefono"), rs.getString("fechanacimiento"),
								rs.getString("tipodocumento"), rs.getString("email"), rs.getString("password"));

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public Persona buscarByDocumento(Integer doc) {
		
		StringBuilder sql = new StringBuilder();
		Persona retorno = new Persona();
		try {
			sql.append("select * from persona where documento= '" + doc+"'" );

					retorno = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Persona>() {
				@Override
				public Persona extractData(ResultSet rs) throws SQLException, DataAccessException {

					while (rs.next())
						return new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("documento"), rs.getString("telefono"), rs.getString("fechanacimiento"),
								rs.getString("tipodocumento"), rs.getString("email"), rs.getString("password"));

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}


	public List<Persona> buscarPersonaByCurso(Integer idcurso) {
		StringBuilder sql = new StringBuilder();
		Persona retorno = new Persona();
		List<Persona> personaList = new ArrayList<Persona>();
		try {
			sql.append("select distinct p.* from persona p "
						+ "join inscripciones i on p.id  = i.id_persona "					
						+ "join curso c on i.id_curso = c.id " 
						+ "where c.id =" + idcurso
						+ "");
			System.out.println("personas de un curso === >"+sql.toString());
			retorno = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Persona>() {
				@Override
				public Persona extractData(ResultSet rs) throws SQLException, DataAccessException {

					while (rs.next())
						personaList.add(new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("documento"), rs.getString("telefono"), rs.getString("fechanacimiento"),
								rs.getString("tipodocumento"), rs.getString("email"), rs.getString("password")));

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return personaList;
	}


	public List<Persona> buscarPersonaSinCurso(Integer idcurso) {
		StringBuilder sql = new StringBuilder();
		Persona retorno = new Persona();
		List<Persona> personaList = new ArrayList<Persona>();
		try {
			sql.append("select * from persona p " 
					+ "where id not in ("
					+ "select i.id_persona from inscripciones i " + 
					"join curso c on i.id_curso = c.id " + 
					"where i.id_curso =" + idcurso
					+ ")"
					+ "and p.id in( select pr.id_persona from persona_rol pr)");

			retorno = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Persona>() {
				@Override
				public Persona extractData(ResultSet rs) throws SQLException, DataAccessException {

					while (rs.next())
						personaList.add(new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("documento"), rs.getString("telefono"), rs.getString("fechanacimiento"),
								rs.getString("tipodocumento"), rs.getString("email"), rs.getString("password")));

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return personaList;
	}


	public void eliminarPersonaConCurso(Integer idPersona,Integer idCurso) {
		StringBuilder sql = new StringBuilder();	
		Map<String, Object> parameters = new HashMap<>();
		try {
			
			sql.append("delete from inscripciones where id_persona =" + idPersona + 
					" and id_curso = "+idCurso);
			jdbcTemplate.execute(sql.toString());
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void agregarPersonaConCurso(Integer idPersona, int idCurso) {

		try {
						
			Inscripciones inscripcion = new Inscripciones();
			inscripcion.setIdCurso(idCurso);
			inscripcion.setIdPersona(idPersona);
			
			inscripcionesRepository.save(inscripcion);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void agregarPersonaActividad(Integer idPersona, int idActividad) {

		try {
						
			InscripcionActividad inscripcion = new InscripcionActividad();
			inscripcion.setIdActividad(idActividad);
			inscripcion.setIdPersona(idPersona);
			
			inscripcionActividadRepository.save(inscripcion);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Persona> buscarAsistentes() {
		StringBuilder sql = new StringBuilder();
		Persona retorno = new Persona();
		List<Persona> personaList = new ArrayList<Persona>();
		try {
			sql.append("select * from persona p "
						+ "WHERE p.id not in( "					
						+ "select pr.id_persona from persona_rol pr) " );
			System.out.println("asistentes === >"+sql.toString());
			retorno = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Persona>() {
				@Override
				public Persona extractData(ResultSet rs) throws SQLException, DataAccessException {

					while (rs.next())
						personaList.add(new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("documento"), rs.getString("telefono"), rs.getString("fechanacimiento"),
								rs.getString("tipodocumento"), rs.getString("email"), rs.getString("password")));

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return personaList;
	}
	
	public List<Persona> buscarUsuarios() {
		StringBuilder sql = new StringBuilder();
		Persona retorno = new Persona();
		List<Persona> personaList = new ArrayList<Persona>();
		try {
			sql.append("select * from persona p "
						+ "WHERE p.id  in( "					
						+ "select pr.id_persona from persona_rol pr) " );
			System.out.println("asistentes === >"+sql.toString());
			retorno = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Persona>() {
				@Override
				public Persona extractData(ResultSet rs) throws SQLException, DataAccessException {

					while (rs.next())
						personaList.add(new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("documento"), rs.getString("telefono"), rs.getString("fechanacimiento"),
								rs.getString("tipodocumento"), rs.getString("email"), rs.getString("password")));

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return personaList;
	}


}
