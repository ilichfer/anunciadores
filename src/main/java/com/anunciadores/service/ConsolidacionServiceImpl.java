package com.anunciadores.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anunciadores.model.Consolidacion;
import com.anunciadores.repository.IConsolidacionRepo;
import com.anunciadores.service.interfaces.IConsolidacionService;

@Service
public class ConsolidacionServiceImpl implements IConsolidacionService {
	
	@Autowired
	private IConsolidacionRepo cosolidacionRepository;

	@Override
	public void asignarPersonaAPadreEspiritual(int idPadreEspiritual, int idPersonaConsolidar) {
		
		Consolidacion consolidacion = new Consolidacion();
		consolidacion.setIdPadreEspiritual(idPadreEspiritual);
		consolidacion.setIdPersonaConsolidar(idPersonaConsolidar);
		
		cosolidacionRepository.save(consolidacion);
	}

	

}
