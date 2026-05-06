package com.anunciadores.service;

import com.anunciadores.auth.dto.updateServiceRequest;
import com.anunciadores.dto.*;
import com.anunciadores.enums.ECombos;
import com.anunciadores.mapper.mapperParametros;
import com.anunciadores.model.*;
import com.anunciadores.model.PersonaMinisterio;
import com.anunciadores.repository.*;
import com.anunciadores.service.interfaces.IMInisteryService;
import com.anunciadores.service.interfaces.IServicioService;
import com.anunciadores.util.UtilDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MinistryServiceImpl implements IMInisteryService {

	private Logger LOGGER = LoggerFactory.getLogger(MinistryServiceImpl.class);

	@Autowired
	private IMinisterioRepo ministerioRepository;

	@Autowired
	private IPosicionesRepo posicionesRepository;

	@Autowired
	private IServicioRepo servicioRepository;
	@Autowired
	private ICoordinadorRepo coordinadorRepo;

	@Autowired
	private IPersonaMinisterioRepo personaMinisterioRepoSitory;

	@Autowired
	private IPersonaRepo personaRepository;

	@Autowired
	private IParametrosRepo parametrosRepo;

	@Autowired
	private mapperParametros mapperParametros;

	@Autowired
	private UtilDate utilDate;

	@Override
	public List<Ministry> getAllministriesWithPositions() {
		List<Ministry> ministryList = new ArrayList<>();

		List<Ministerio> listMin = ministerioRepository.findAll();


		if(listMin != null && listMin.size() > 0){
			listMin.forEach(m -> {

				Ministry min = new Ministry();
				List<PosicionesMinisterio> listpositions = posicionesRepository.findAllByIdMinisterio(m.getId());

				min = new Ministry();
				min.setId(String.valueOf(m.getId()));
				min.setName(m.getNombre());

				List<PositionDto> listP = new ArrayList<>();
				listpositions.forEach(p -> listP.add(createPosition(p)));
				min.setPositions(listP);

				ministryList.add(min);
			});


		}

		return ministryList;
	}

	private PositionDto createPosition(PosicionesMinisterio p ){
		PositionDto position = new PositionDto();
		position.setId(p.getId());
		position.setName(p.getNombrePosicion());
		return position;
	}

	@Override
	public void agregarPersonasAMinisterio(int idPersona, int idMinisterio) {
		try {
				PersonaMinisterio personaSave = new PersonaMinisterio();
				personaSave.setIdMinisterio(idMinisterio);
				personaSave.setIdPersona(idPersona);
				personaMinisterioRepoSitory.save(personaSave);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Ministry getMinistryWithPositions(Integer idMinisterio) {
		List<Ministry> ministryList = new ArrayList<>();

		Optional<Ministerio> ministerio = ministerioRepository.findById(idMinisterio);
		Ministry min = new Ministry();

		if(ministerio.isPresent()){
				List<PosicionesMinisterio> listpositions = posicionesRepository.findAllByIdMinisterio(ministerio.get().getId());

				min = new Ministry();
				min.setId(String.valueOf(ministerio.get().getId()));
				min.setName(ministerio.get().getNombre());

				List<PositionDto> listP = new ArrayList<>();
				listpositions.forEach(p -> listP.add(createPosition(p)));
				min.setPositions(listP);
		}
		return min;
	}
	@Transactional
	@Override
	public void updateService(updateServiceRequest updateRequest) throws ParseException {

		Date fechaaServicio = utilDate.convertStringToDate(updateRequest.getDate());
		servicioRepository.deleteByFechaServicioAndIdMinisterio(fechaaServicio,updateRequest.getMinistryId());

		List<Servicio> progServicio = new ArrayList<>();

		for (int i = 0; i < updateRequest.getAssignments().size(); i++) {
			if (updateRequest.getAssignments().get(i).getPersonId() != 0) {
				Servicio servicio = new Servicio();
				PosicionesMinisterio posicionEntity = posicionesRepository.findMinisterioByName(updateRequest.getAssignments().get(i).getIdPosicion(), updateRequest.getMinistryId());
				servicio.setFechaServicio(utilDate.convertStringToDate(updateRequest.getDate()));
				servicio.setIdMinisterio(updateRequest.getMinistryId());
				servicio.setIdPersona(updateRequest.getAssignments().get(i).getPersonId());
				servicio.setIdPosicion(updateRequest.getAssignments().get(i).getIdPosicion());
				progServicio.add(servicio);
			}
			servicioRepository.saveAll(progServicio);
		}
	}
}
