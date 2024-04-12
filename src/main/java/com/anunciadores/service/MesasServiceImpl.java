package com.anunciadores.service;

import java.util.ArrayList;
import java.util.List;

import com.anunciadores.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anunciadores.model.Actividad;
import com.anunciadores.model.Curso;
import com.anunciadores.model.Mesa;
import com.anunciadores.model.Persona;
import com.anunciadores.service.interfaces.IMesasService;

@Service
public class MesasServiceImpl implements IMesasService {

	@Autowired
	private IActividadRepo ActividadRepository;
	
	@Autowired
	private IMesasRepo mesasRepository;
	
	@Autowired
	private MesasRepoImpl mesaDAO;

	@Autowired
	private IPersonaRepo personaRepo;

	List<Curso> listaActividades = new ArrayList<>();
	Actividad actividadEntity;
	List<Persona> listaPersonas;




	@Override
	public void delete(Mesa mesa) {
		mesasRepository.deleteById(mesa.getId());		
	}




	@Override
	public List<Actividad> listarActiviades() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<Persona> BuscarPersonasSinMesa(Mesa mesa) {
		return personaRepo.buscarPersonaSinMesas(mesa.getId());
	}

}
