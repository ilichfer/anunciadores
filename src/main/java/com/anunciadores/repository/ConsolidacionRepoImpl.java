package com.anunciadores.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.anunciadores.model.inscripcionConsolidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.anunciadores.model.Persona;

@Service
public class ConsolidacionRepoImpl {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Persona> listarConsolidacion1() {

		StringBuilder sql = new StringBuilder();
		Persona retorno = new Persona();
		List<Persona> personaList = new ArrayList<Persona>();
		try {
			sql.append("select * from persona p ");
			sql.append("join consolidacion c on  p.id <> c.id_persona_consolidar ");
			sql.append("WHERE p.id not in(select pr.id_persona from persona_rol pr) ");
			System.out.println("buscar personas sin consolidacion ===> " + sql.toString());

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

//	public Consolidacion listarConsolidacionByPersona(int idPersona) {
//		
//		StringBuilder sql = new StringBuilder();
//		Consolidacion Consolidacion = new Consolidacion() ;
////		List<Persona> personaList = new ArrayList<Persona>();
//		try {
//			sql.append("select * from consolidacion c "  );
//			sql.append("WHERE c.id_padre_espiritual = " +idPersona );
//			System.out.println("buscar personas sin consolidacion ===> "+sql.toString());
//
//					retorno = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Consolidacion>() {
//						@Override
//						public Consolidacion extractData(ResultSet rs) throws SQLException, DataAccessException {
//
//							while (rs.next())
//								
//							retorno.setId(rs.getInt("id"));
//							retorno.setIdPadreEspiritual(rs.getInt("id_padre_espiritual"));
//							retorno.setIdPersonaConsolidar(rs.getInt("id_persona_consolidar"));
//							return retorno;
//								
//							
//							
//						}
//			});
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return retorno;
//	}

	public inscripcionConsolidacion listarConsolidacionByPersona(int idPersona) {

		StringBuilder sql = new StringBuilder();
		sql.append("select * from consolidacion c ");
		sql.append("WHERE c.id_persona = " + idPersona);

//		Query query = entityManager.createNativeQuery(sql.toString(), Consolidacion.class);
//		Consolidacion consolidacion = (Consolidacion) query.getResultList();
		try {
			return (inscripcionConsolidacion) entityManager.createNativeQuery(sql.toString(), inscripcionConsolidacion.class)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public Persona listarPersonaConsolidacion(int idPersona) {

		StringBuilder sql = new StringBuilder();
		sql.append("select p.* from consolidacion c ");
		sql.append("join persona p on  c.id_persona_consolidar = p.id ");
		sql.append("WHERE c.id_persona = " + idPersona);

//		Query query = entityManager.createNativeQuery(sql.toString(), Consolidacion.class);
//		Consolidacion consolidacion = (Consolidacion) query.getResultList();
		try {
			return (Persona) entityManager.createNativeQuery(sql.toString(), Persona.class)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
