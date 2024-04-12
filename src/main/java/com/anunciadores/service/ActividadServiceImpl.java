package com.anunciadores.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.anunciadores.repository.IPersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anunciadores.dto.ActividadDto;
import com.anunciadores.model.Actividad;
import com.anunciadores.model.Curso;
import com.anunciadores.model.Mesa;
import com.anunciadores.model.Persona;
import com.anunciadores.repository.ActividadRepoImpl;
import com.anunciadores.repository.IActividadRepo;
import com.anunciadores.service.interfaces.IActividadService;

@Service
public class ActividadServiceImpl implements IActividadService {

	@Autowired
	private IActividadRepo ActividadRepository;
	
	@Autowired
	private IPersonaRepo personaRepository;
	//private ActividadRepoImpl actividadDAO;

	List<Curso> listaActividades = new ArrayList<>();
	Actividad actividadEntity;
	List<Persona> listaPersonas;

	@Override
	public List<Actividad> listarActiviades() {
		return ActividadRepository.findAll();
	}

	@Override
	public Actividad save(ActividadDto actividadDTO) throws ParseException {
		actividadEntity = new Actividad();
		actividadEntity.setNombreActividad(actividadDTO.getNombreActividad());
		actividadEntity.setFecha(actividadDTO.getFecha());

		// TODO Auto-generated method stub
		return ActividadRepository.save(actividadEntity);
	}

	@Override
	public Actividad findActividadById(Integer id) {
		// TODO Auto-generated method stub
		return ActividadRepository.findById(id).get();
	}

	@Override
	public void delete(ActividadDto actividadDTO) {
		ActividadRepository.deleteById(actividadDTO.getId());
	}

	@Override
	public List<Persona> buscarTodosSinActividad(int idActividad) {
		return personaRepository.buscarPersonaSinActividad(idActividad);
	}

	@Override
	public List<Persona> buscarPersonasActividad(int idActividad) {
		//return actividadDAO.buscarPersonaByActividad(idActividad);
		return personaRepository.buscarPersonaByActividad(idActividad);
	}

	@Override
	public List<Mesa> buscarMesasActividad(ActividadDto actividadDto) {
		
		//return actividadDAO.buscarMesasByActividad(actividadDto.getId());
		//return actividadDAO.buscarMesasByActividad(actividadDto.getId());
		return null;
	}

}
