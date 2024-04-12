package com.anunciadores.service;

import com.anunciadores.dto.AsignacionConsolidacionDto;
import com.anunciadores.dto.PersonaConsolidacionDto;
import com.anunciadores.mapper.mapperConsolidacion;
import com.anunciadores.model.*;
import com.anunciadores.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anunciadores.service.interfaces.IConsolidacionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ConsolidacionServiceImpl implements IConsolidacionService {

	@Autowired
	private IInscripcionConsolidacionRepo cosolidacionRepository;

	@Autowired
	private IMinisterioRepo ministerioRepo;

	@Autowired
	private mapperConsolidacion mapperConsolidacion;
	@Autowired
	private IInscripcionConsolidacionRepo inscripcionConsolidacionRepo;
	@Autowired
	private IPersonaRepo personaRepo;
	@Autowired
	private IConsolidacionRepo consolidacionRepo;
	@Autowired
	private IHisConsolidacionRepo hisConsolidacionRepo;

	@Override
	public Boolean asignarPersonaAPadreEspiritual(int idPadreEspiritual, int idPersonaConsolidar) {
		Boolean asignacion= Boolean.FALSE;
		List<inscripcionConsolidacion> insConsolidacion = inscripcionConsolidacionRepo.findByIdPadreEspiritual(idPadreEspiritual);
		if(insConsolidacion.isEmpty() ) {

			inscripcionConsolidacion inscripcionConsolidacion = new inscripcionConsolidacion();
			inscripcionConsolidacion.setIdPadreEspiritual(idPadreEspiritual);
			inscripcionConsolidacion.setIdPersonaConsolidar(idPersonaConsolidar);

			cosolidacionRepository.save(inscripcionConsolidacion);
			asignacion= Boolean.TRUE;
		}
		return asignacion;
	}

	@Override
	public List<PersonaConsolidacionDto> listarservidoresConsolidacion(String nombreMInisterio) {
		List<PersonaConsolidacionDto> servidoresConsolidacion= new ArrayList<>();
		List<Ministerio> ministerios= ministerioRepo.findAll();
		for (Ministerio min:ministerios) {
			if (min.getNombre().equalsIgnoreCase(nombreMInisterio)){
				//personasMIn = ministerioRepo.findPersonasByIdMinisterio(min.getId());
				servidoresConsolidacion = buscarAsignacion(mapperConsolidacion.liitEntitytoConsolidacionDto(ministerioRepo.findPersonasByIdMinisterio(min.getId())));
			}
		}
		return servidoresConsolidacion;
	}

	@Override
	public List<AsignacionConsolidacionDto> listarConsolidacionByServidor(int idServidor) {
		List<Persona> personasList;
		List<AsignacionConsolidacionDto> asignacionlist = new ArrayList<>();
		//Persona per = personaRepo.findById(idServidor);
		personasList = inscripcionConsolidacionRepo.findConsolidacionesByServidor(idServidor);
		if(!personasList.isEmpty() ){
			for (Persona per: personasList) {
				AsignacionConsolidacionDto dto = new AsignacionConsolidacionDto();
				Consolidacion con = consolidacionRepo.findByIdPersona(per.getId());
				dto.setNombre(per.getNombre());
				dto.setApellido(per.getApellido());
				dto.setTelefono(per.getTelefono());
				dto.setHorarioConsolidacionSugerido(con.getHorarioConsolidacionSugerido());
				dto.setHorarioConsolidacionPersona(con.getHorarioConsolidacionPersona());
				dto.setIdConsolidacion(con.getId());
				dto.setIdPersona(idServidor);
				asignacionlist.add(dto);
			}
			return asignacionlist;
		}
		return null;
	}

	@Override
	public void saveDescripcionConsolidacion(int idConsolidacion, String DescConsolidacion) {
		HistoricoConsolidacion his = new HistoricoConsolidacion();
		his.setFechaRegistroConsolidacion(new Date());
		his.setIdConsolidacion(idConsolidacion);
		his.setDescripcionConsolidacion(DescConsolidacion);
		hisConsolidacionRepo.save(his);
	}

	private List<PersonaConsolidacionDto> buscarAsignacion(List<PersonaConsolidacionDto> servidoresConsolidacion){
		List<AsignacionConsolidacionDto> asignacionlist = new ArrayList<>();
		for (PersonaConsolidacionDto per :servidoresConsolidacion) {
			List<inscripcionConsolidacion> insConsolidacion = inscripcionConsolidacionRepo.findByIdPadreEspiritual(per.getId());
			if(insConsolidacion.isEmpty()){
				AsignacionConsolidacionDto dto = new AsignacionConsolidacionDto();
				dto.setNombre("N/A");
				dto.setApellido("");
				dto.setDocumento(0);
				dto.setHorarioConsolidacionPersona("N/A");
				dto.setTelefono("N/A");
				dto.setHorarioConsolidacionPersona("N/A");
				dto.setHorarioConsolidacionSugerido("N/A");
				asignacionlist.add(dto);
				per.setAsignacion(asignacionlist);
			}else {

				AsignacionConsolidacionDto dto = new AsignacionConsolidacionDto();
				Persona perAsignacion = personaRepo.findById(insConsolidacion.get(0).getIdPersonaConsolidar()).get();
				Consolidacion con = consolidacionRepo.findByIdPersona(insConsolidacion.get(0).getIdPersonaConsolidar());
				dto.setNombre(perAsignacion.getNombre());
				dto.setApellido(perAsignacion.getApellido());
				dto.setDocumento(perAsignacion.getDocumento());
				dto.setTelefono(perAsignacion.getTelefono());
				dto.setHorarioConsolidacionPersona(con.getHorarioConsolidacionPersona());
				dto.setHorarioConsolidacionSugerido(con.getHorarioConsolidacionSugerido());
				asignacionlist.add(dto);
				per.setAsignacion(asignacionlist);
			}
		}

		return servidoresConsolidacion;
	}


}
