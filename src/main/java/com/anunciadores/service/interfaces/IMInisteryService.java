package com.anunciadores.service.interfaces;

import com.anunciadores.auth.dto.updateServiceRequest;
import com.anunciadores.dto.*;
import com.anunciadores.model.Coordinador;
import com.anunciadores.model.Ministerio;
import com.anunciadores.model.Persona;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface IMInisteryService {

	public List<Ministry> getAllministriesWithPositions();

	void agregarPersonasAMinisterio(int idPersona, int idMinisterio);

	public Ministry getMinistryWithPositions( Integer idMinisterio);

	public void updateService( updateServiceRequest updateRequest) throws ParseException;

}
