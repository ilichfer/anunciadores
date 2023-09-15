package com.anunciadores.service;

import com.anunciadores.dto.*;
import com.anunciadores.model.Ministerio;
import com.anunciadores.model.Persona;
import com.anunciadores.model.PosicionesMinisterio;
import com.anunciadores.model.Tdc;
import com.anunciadores.repository.IMinisterioRepo;
import com.anunciadores.repository.IPersonaRepo;
import com.anunciadores.repository.IPosicionesRepo;
import com.anunciadores.repository.ITdcRepo;
import com.anunciadores.service.interfaces.IServicioService;
import com.anunciadores.service.interfaces.ITdcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ServicioServiceImpl implements IServicioService {

	@Autowired
	private IMinisterioRepo ministerioRepository;

	@Autowired
	private IPosicionesRepo posicionesRepository;
	@Override
	public List<Ministerio> getAll() {
		List<Ministerio> listaMinisterio= new ArrayList<>();
		listaMinisterio =  ministerioRepository.findAll();
		return listaMinisterio;
	}

	@Override
	public Ministerio findByidMnisterio(int idMinisterio) {
		return ministerioRepository.findById(idMinisterio).get();
	}

	@Override
	public List<PersonaDto> findPersonaByidMnisterio(int idMinisterio) {
		List<PersonaDto> listaDto = new ArrayList<>();
		List<Persona> listaEntity= ministerioRepository.findPersonasByIdMinisterio(idMinisterio);
		listaEntity.forEach(p -> listaDto.add(mapPersonaToDto(p)));
		return listaDto;
	}

	@Override
	public List<MinisterioDto> getPositionByidMinisterio(int idMinisterio) {
		List<PosicionesMinisterio> listaPosiciones = new ArrayList<>();
		List<MinisterioDto> listaPosicionesDto = new ArrayList<>();
		Ministerio ministerio = ministerioRepository.findById(idMinisterio).get();
		listaPosiciones = posicionesRepository.findAllByIdMinisterio(idMinisterio);
		listaPosiciones.forEach( p -> listaPosicionesDto.add(mapEntityToDto(p,ministerio.getNombre())));
		return listaPosicionesDto;
	}

	private MinisterioDto mapEntityToDto (PosicionesMinisterio Posicion, String nombreMinisterio){
		MinisterioDto dto = new MinisterioDto();
		dto.setPosicionDto(new PosicionDto());
		dto.setNombreMinisterio(nombreMinisterio);
		dto.getPosicionDto().setIdMinisterio(Posicion.getIdMinisterio());
		dto.getPosicionDto().setNombrePosicion(Posicion.getNombrePosicion());
		dto.getPosicionDto().setId(Posicion.getId());
		return dto;
	}

	private PersonaDto mapPersonaToDto(Persona per){
		PersonaDto personadto = new PersonaDto();
		personadto.setNombre(per.getNombre());
		personadto.setApellido(per.getApellido());
		personadto.setDocumento(per.getDocumento());
		personadto.setTipodocumento(per.getTipodocumento());
		personadto.setEmail(per.getEmail());
		personadto.setId(per.getId());
		personadto.setFechanacimiento(per.getFechanacimiento());
		personadto.setTelefono(per.getTelefono());
		personadto.setPassword(per.getPassword());
		return  personadto;
	}
}
