package com.anunciadores.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.anunciadores.model.Persona;
import com.anunciadores.repository.IPersonaRepo;
import com.anunciadores.repository.InscripcionRepo;
import com.anunciadores.repository.PersonaRepoImpl;
import com.anunciadores.service.interfaces.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private IPersonaRepo PersonaRepository;

	@Autowired
	private PersonaRepoImpl daoPersona;

	@Autowired
	private InscripcionRepo inscripcionesRepository;

	@Override
	public List<Persona> findAll() {
		return PersonaRepository.findAll();
	}

	@Override
	public Persona save(Persona cliente) {
		return PersonaRepository.save(cliente);
	}

	@Override
	public Optional<Persona> findProductoById(Integer id) {
		Optional<Persona> persona = PersonaRepository.findById(id);
		return persona;
	}

	@Override
	public Persona delete(Persona persona) {
		PersonaRepository.deleteById(persona.getId());
		return persona;
	}

	@Override
	public Persona buscarEmail(String email) {
		Persona per = new Persona();
		try {
			per = daoPersona.buscarEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return per;
	}

	@Override
	public List<Persona> findAllByCurso(int idCurso) {
		return daoPersona.buscarPersonaByCurso(idCurso);
	}

	@Override
	public List<Persona> buscarTodosSinCurso(int idCurso) {
		return daoPersona.buscarPersonaSinCurso(idCurso);
	}

	@Override
	public void eliminarPersonaCurso(int idPersona, int idCurso) {
		daoPersona.eliminarPersonaConCurso(idPersona, idCurso);
	}

	@Override
	public void agregarPersonaCurso(int idPersona, int idCurso) {
		try {
			daoPersona.agregarPersonaConCurso(idPersona, idCurso);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
