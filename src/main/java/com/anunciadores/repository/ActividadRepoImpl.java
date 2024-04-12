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
import com.anunciadores.model.Mesa;
import com.anunciadores.model.Persona;


@Service
public class ActividadRepoImpl{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IPersonaRepo PersonaRepository;
	
	@Autowired
	private InscripcionRepo inscripcionesRepository;
	
	@Autowired
	private InscripcionActividadRepo inscripcionActividadRepository;
	


	public List<Persona> buscarPersonaSinActividad(Integer idActividad) {
		StringBuilder sql = new StringBuilder();
		Persona retorno = new Persona();
		List<Persona> personaList = new ArrayList<Persona>();
		try {
			sql.append("select * from persona p " 
					+ "where id not in ("
					+ "select i.id_persona from inscripcion_actividades i " + 
					" join actividades a on i.id_actividad = a.id" + 
					" where i.id_actividad =" + idActividad
					+ ")");

			retorno = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Persona>() {
				@Override
				public Persona extractData(ResultSet rs) throws SQLException, DataAccessException {

					while (rs.next())
						personaList.add(new Persona());

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return personaList;
	}


	public void eliminarPersonaConActividad1(Integer idPersona,Integer idCurso) {
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
	

	public void agregarPersonaConActividad(Integer idPersona, int idCurso) {

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
	
	public List<Persona> buscarPersonaByActividad(Integer idActividad) {
		StringBuilder sql = new StringBuilder();
		Persona retorno = new Persona();
		List<Persona> personaList = new ArrayList<Persona>();
		try {
			sql.append("select distinct p.* from persona p "
						+ "join inscripcion_actividades i on p.id  = i.id_persona "					
						+ "join actividades a on i.id_actividad = a.id " 
						+ "where a.id =" + idActividad
						+ "");
			System.out.println("personas de una actividad === >"+sql.toString());
			retorno = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Persona>() {
				@Override
				public Persona extractData(ResultSet rs) throws SQLException, DataAccessException {

					while (rs.next())
						personaList.add(new Persona());

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return personaList;
	}
	 
	
			 public List<Mesa> buscarMesasByActividad(Integer idActividad) {
			StringBuilder sql = new StringBuilder();
			Mesa retorno = new Mesa();
			List<Mesa> mesasList = new ArrayList<Mesa>();
			try {
				sql.append("SELECT * FROM actividades act  "
							+ "join mesa m on act.id = m.id_actividad "					
							+ "where act.id=" + idActividad
							+ "");
				System.out.println("mesas de una actividad === >"+sql.toString());
				retorno = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Mesa>() {
					@Override
					public Mesa extractData(ResultSet rs) throws SQLException, DataAccessException {

						while (rs.next())
							mesasList.add(new Mesa(rs.getInt("id"), rs.getInt("id_actividad"), rs.getString("nombre_mesa")));

						return null;
					}
				});

			} catch (Exception e) {
				e.printStackTrace();
			}
			return mesasList;
		}
	
	
	

}
