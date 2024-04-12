package com.anunciadores.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.anunciadores.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.anunciadores.model.RolPersona;


@Service
public class RolesRepoImpl{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IPersonaRepo PersonaRepository;
	
	@Autowired
	private InscripcionRepo inscripcionesRepository;
	
	@Autowired
	private InscripcionActividadRepo inscripcionActividadRepository;
	


	public List<Rol> buscarRoles(Integer idPersona) {
		StringBuilder sql = new StringBuilder();
		Rol retorno = new Rol();
		List<Rol> rolesList = new ArrayList<Rol>();
		try {
			sql.append("SELECT r.* FROM persona_rol pr  " 
					+ "join rol r on  pr.id_rol = r.id" + 
					" where pr.id_persona = " + idPersona
					+ "");

			retorno = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Rol>() {
				@Override
				public Rol extractData(ResultSet rs) throws SQLException, DataAccessException {

					while (rs.next())
						rolesList.add(new Rol(rs.getInt("id"), rs.getString("descripcion_rol")));

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rolesList;
	}
	
	public List<RolPersona> buscarRolesPersona(Integer idPersona) {
		StringBuilder sql = new StringBuilder();
		RolPersona retorno = new RolPersona();
		List<RolPersona> rolesList = new ArrayList<RolPersona>();
		try {
			sql.append("SELECT * FROM persona_rol pr  " 
					+" where pr.id_persona = " + idPersona);

			retorno = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<RolPersona>() {
				@Override
				public RolPersona extractData(ResultSet rs) throws SQLException, DataAccessException {

					while (rs.next())
						rolesList.add(new RolPersona(rs.getInt("id"),rs.getInt("id_persona"),rs.getInt("id_rol")));

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rolesList;
	}

	

}
