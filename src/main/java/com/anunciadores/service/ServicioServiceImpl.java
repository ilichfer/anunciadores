package com.anunciadores.service;

import com.anunciadores.dto.*;
import com.anunciadores.model.*;
import com.anunciadores.repository.*;
import com.anunciadores.service.interfaces.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ServicioServiceImpl implements IServicioService {

	@Autowired
	private IMinisterioRepo ministerioRepository;

	@Autowired
	private IPosicionesRepo posicionesRepository;

	@Autowired
	private IServicioRepo servicioRepository;

	@Autowired
	private IPersonaMinisterioRepo personaMinisterioRepoSitory;

	@Autowired
	private IPersonaRepo personaRepository;
	@Override
	public List<Ministerio> getAll() {
		List<Ministerio> listaMinisterio= new ArrayList<>();
		listaMinisterio =  ministerioRepository.findAll();
		return listaMinisterio;
	}

	@Override
	public Ministerio saveMinisterio(String nombreMinisterio) {
		Ministerio ministerio = new Ministerio();
		ministerio.setNombre(nombreMinisterio);

		return ministerioRepository.save(ministerio);
	}

	@Override
	public Ministerio saveMinisterio(MinisterioDto ministerioDto) {

		Ministerio ministerio = new Ministerio();
		ministerio.setNombre(ministerioDto.getNombreMinisterio());
		ministerio.setId(ministerioDto.getId());
		return ministerioRepository.save(ministerio);
	}

	@Override
	public void deleteMinisterio(int idMinisterio) {
		 ministerioRepository.deleteById(idMinisterio);
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

	@Override
	public List<MinisterioDto> getPositionByidMinisterioAndPerson(Date fechaActual , int idMinisterio) {
		List<PosicionesMinisterio> listaPosiciones = new ArrayList<>();
		List<MinisterioDto> listaPosicionesDto = new ArrayList<>();

		List<Object> obj = new ArrayList<>();
		List<ServicioResponseDto> ListServicioDto = new ArrayList<>();
		obj = servicioRepository.findProgramacionByDateAndMinistery(fechaActual, idMinisterio);

		Ministerio ministerio = ministerioRepository.findById(idMinisterio).get();
		listaPosiciones = posicionesRepository.findAllByIdMinisterio(idMinisterio);
		List<Object> finalObj = obj;
		listaPosiciones.forEach(p -> listaPosicionesDto.add(mapEntityToDtoAndPerson(p,ministerio.getNombre(), finalObj)));
		return listaPosicionesDto;
	}



	@Override
	public List<PersonaDto> getPeopleWithoutMinisterio(int idMinisterio) {
		List<PersonaDto> listaDto = new ArrayList<>();
		List<Persona> listaEntity= ministerioRepository.findPeopleWithOutMinisterio(idMinisterio);
		listaEntity.forEach(p -> listaDto.add(mapPersonaToDto(p)));
		return listaDto;
	}

	@Override
	public void saveProgramacion(ServicioDto servidores, Date fechaServicio) {

		List<Servicio> progServicio = new ArrayList<>();
		try {


		for (int i = 0; i < servidores.getPosicion().size(); i++) {
			Servicio servicio = new Servicio();
			List<PosicionesMinisterio> posicionEntity = posicionesRepository.findMinisterioByName(servidores.getPosicion().get(i));
			servicio.setIdPosicion(posicionEntity.get(0).getId());
			int posicion = Integer.parseInt(servidores.getEncargado().get(i));
			servicio.setIdPersona(Integer.parseInt(servidores.getEncargado().get(i)));
			servicio.setFechaServicio(fechaServicio);
			servicio.setIdMinisterio(posicionEntity.get(0).getIdMinisterio());
			progServicio.add(servicio);
		}
		servicioRepository.saveAll(progServicio);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void updateProgramacion(ServicioDto servidores, Date fechaServicio) {

		List<Servicio> progServicio = new ArrayList<>();
		try {


			for (int i = 0; i < servidores.getPosicion().size(); i++) {
				Servicio servicio = new Servicio();
				List<PosicionesMinisterio> posicionEntity = posicionesRepository.findMinisterioByName(servidores.getPosicion().get(i));
				//int nombreposi = posicionEntity.get(0).getId();
				Optional<Servicio> programacion = 	servicioRepository.findProgramacionPosition(Integer.parseInt(servidores.getEncargado().get(i)), fechaServicio);
				//servicio.setIdPosicion(posicionEntity.get(0).getId());
				servicio = programacion.get();
				servicio.setIdPosicion(posicionEntity.get(0).getId());
				progServicio.add(servicio);
			}
			servicioRepository.saveAll(progServicio);
		}catch (Exception e){
			e.printStackTrace();
		}
	}



	@Override
	public Optional<Persona> validarProgramacionByFecha(ServicioDto servidores, Date fechaServicio) {

		try {
			for (String servidor:servidores.getEncargado()) {
			Optional<Servicio> programacion = 	servicioRepository.findProgramacionServidor(Integer.parseInt(servidor), fechaServicio);

				if(programacion.isPresent()){
					Optional<Persona> per=	personaRepository.findById(Integer.parseInt(servidor));
					return per;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public Optional<Persona> validarActualizarProgramacionByFecha(ServicioDto servidores, Date fechaServicio, int ministerio) {

		try {
			for (String servidor:servidores.getEncargado()) {
				Optional<Servicio> programacion = 	servicioRepository.findProgramacionServidor(Integer.parseInt(servidor), fechaServicio);

				if(programacion.isPresent() && programacion.get().getIdMinisterio() != ministerio){
					Optional<Persona> per=	personaRepository.findById(Integer.parseInt(servidor));
					return per;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public List<ServicioListResponseDto> findProgramacionByDate(Date fechaActual) {

		List<ServicioListResponseDto> ListServiceDto = new ArrayList<>();
		List<Object> obj = new ArrayList<>();
		List<ServicioResponseDto> ListServicioDto = new ArrayList<>();
			obj = servicioRepository.findProgramacionServicio(fechaActual);
			if (!obj.isEmpty()){
				obj.forEach(O -> {
					try {
						ListServicioDto.add(mapObjectToDto(O));


					} catch (ParseException e) {
						throw new RuntimeException(e);
					}
				});
			}


		ListServiceDto = buscarMinistarios(ListServicioDto);


		//return ListServicioDto;
		return ListServiceDto;
	}

	@Override
	public List<ServicioListResponseDto> findProgramacionByDateAndMinisterio(Date fechaActual, int idMinisterio) {
		List<Object> obj = new ArrayList<>();
		List<ServicioResponseDto> ListServicioDto = new ArrayList<>();
		obj = servicioRepository.findProgramacionByDateAndMinistery(fechaActual, idMinisterio);
		for (int i = 0; i < 6; i++) {

				obj.forEach(O -> {
					try {
						ListServicioDto.add(mapObjectToDto(O));


					} catch (ParseException e) {
						throw new RuntimeException(e);
					}
				});
				break;
			}
		ListServicioDto.size();
		return null;
	}

	@Override
	public List<ServicioListResponseDto> findProgramacionByDateGroup(Date fechaActual) {
		List<ServicioListResponseDto> ListServiceDto = new ArrayList<>();
		try {
		LocalDate fechaActualizada = LocalDate.now();

		List<Object> obj = new ArrayList<>();
		List<ServicioResponseDto> ListServicioDto = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			obj = servicioRepository.findProgramacionServicio(fechaActual);
			if (obj.isEmpty()){
				fechaActualizada = fechaActualizada.plusDays(1);
				fechaActual = java.sql.Date.valueOf(fechaActualizada);
			}else{
				obj.forEach(O -> {
					try {
						ListServicioDto.add(mapObjectToDto(O));


					} catch (ParseException e) {
						throw new RuntimeException(e);
					}
				});
				break;
			}
		}

		ListServiceDto = buscarMinistarios(ListServicioDto);
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	//return ListServicioDto;
		return ListServiceDto;
	}

	@Override
	public void agregarPersonaAMinisterio(int idPersona, int idMinisterio) {
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
	public void savePosicion(PosicionDto posicionDto) {

		PosicionesMinisterio posicion = new PosicionesMinisterio();
		posicion.setNombrePosicion(posicionDto.getNombrePosicion());
		posicion.setIdMinisterio(posicionDto.getIdMinisterio());
		posicionesRepository.save(posicion);

	}

	@Override
	public void editPosicion(PosicionDto posicionDto) {
		PosicionesMinisterio posicion = new PosicionesMinisterio();
		posicion.setNombrePosicion(posicionDto.getNombrePosicion());
		posicion.setIdMinisterio(posicionDto.getIdMinisterio());
		posicion.setId(posicionDto.getId());
		posicionesRepository.save(posicion);
	}

	@Override
	public PosicionDto findPosicion(int idposicion) {
		PosicionDto response = new  PosicionDto();
		PosicionesMinisterio posicion = posicionesRepository.findById(idposicion).get();
		response.setIdMinisterio(posicion.getIdMinisterio());
		response.setNombrePosicion(posicion.getNombrePosicion());
		response.setId(posicion.getId());
		return response;
	}

	@Override
	public Boolean validarDuplicados(ServicioDto servidores) {

		try {


			Set<String> set = new HashSet<>(servidores.getEncargado());

			if(set.size() < servidores.getEncargado().size()){
				return false;
			} else {
				return true;
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<MinisterioDto> poblarPosiciones(List<MinisterioDto> ministerios, ServicioDto servicioDto) {
		List<MinisterioDto> ministeriosFinal = new ArrayList<>();
		ministerios.forEach(m -> {

			m.getPosicionDto().getNombrePosicion();
			for (int i = 0; i < servicioDto.getPosicion().size() ; i++) {
				if (m.getPosicionDto().getNombrePosicion().equals(servicioDto.getPosicion().get(i))){
					PersonaDto perMinisterio= new PersonaDto();
					perMinisterio.setId(Integer.parseInt(servicioDto.getEncargado().get(i)));
					m.getPosicionDto().setPersonaDto(perMinisterio);
				}

			}

			});
		ministeriosFinal = ministerios;

		return ministeriosFinal;
	}

	@Override
	public List<MinisterioDto> getPositionInitial(List<MinisterioDto> Ministerio) {
		PersonaDto perPos= new PersonaDto();
		perPos.setId(0);
		Ministerio.forEach(o->o.getPosicionDto().setPersonaDto(perPos));
		return Ministerio;
	}

	@Override
	public Persona getPersonDuplicate(ServicioDto servidores) {
			Integer contador = 0;
		Optional<Persona> p ;
		for (String o:	servidores.getEncargado()) {
			p = validarRepetido(o,servidores.getEncargado());
			return p.get();
		}
			return new Persona();
	}

	private Optional<Persona> validarRepetido(String idUser, List<String> listUsers){
		int c = 0;
		for (int j = 0; j < listUsers.size(); j++) {
			if ( idUser.equalsIgnoreCase(listUsers.get(j))){
				c= c+ 1;
				if (c > 1){
					return personaRepository.findById(Integer.parseInt(idUser));
				}
			}
		}
		return Optional.empty();
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

	private MinisterioDto mapEntityToDtoAndPerson (PosicionesMinisterio Posicion, String nombreMinisterio, List<Object> obj){
		MinisterioDto dto = new MinisterioDto();
		dto.setPosicionDto(new PosicionDto());
		dto.setNombreMinisterio(nombreMinisterio);
		dto.getPosicionDto().setIdMinisterio(Posicion.getIdMinisterio());
		dto.getPosicionDto().setNombrePosicion(Posicion.getNombrePosicion());
		dto.getPosicionDto().setId(Posicion.getId());
		obj.forEach(o -> {
			Object[] object = (Object[]) o;
			Integer idPosicion = Posicion.getId();
			if (idPosicion.equals(Integer.parseInt(object[2].toString()))) {
				PersonaDto perMinisterio= new PersonaDto();
				perMinisterio.setId(Integer.parseInt(object[0].toString()));
				perMinisterio.setNombre(object[1].toString());
				dto.getPosicionDto().setPersonaDto(perMinisterio);
			}
		});
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

	private ServicioResponseDto mapObjectToDto(Object obj) throws ParseException {
		ServicioResponseDto servicioDto = new ServicioResponseDto("bateriavoz principal","gaby ", "2023-12-04", "ministerio", 1);
		Object[] object = (Object[]) obj;

		String date_s = object[0].toString();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date1 = dt.parse(date_s);
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(dt1.format(date1));
		servicioDto.setFechaServcio(dt1.format(date1));
		servicioDto.setEncargado(object[1].toString());
		servicioDto.setPosicion(object[2].toString());
		servicioDto.setIdMinisterio(Integer.parseInt(object[3].toString()));
		servicioDto.setNombreMinisterio(object[4].toString());
		return  servicioDto;
	}

	private List<ServicioListResponseDto> buscarMinistarios(List<ServicioResponseDto> ListServicioDto) {
		List<ServicioListResponseDto> ListServiceDto = new ArrayList<>();
		List<Ministerio> ministerioslist = ministerioRepository.findAll();
		if (!ListServicioDto.isEmpty()) {
			for (Ministerio m : ministerioslist) {
				ServicioListResponseDto dtoResponse = new ServicioListResponseDto();
				dtoResponse.setFechaServcio(ListServicioDto.get(0).getFechaServcio());
				dtoResponse.setIdMinisterio(m.getId());
				dtoResponse.setNombreMinisterio(m.getNombre());
				dtoResponse.setServicioDTO(
						ListServicioDto.stream().filter(p -> p.getIdMinisterio() == m.getId())
								.collect(Collectors.toList()));
				dtoResponse.setTamanoLista(dtoResponse.getServicioDTO().size() + 1);
				ListServiceDto.add(dtoResponse);
			}
		}
		return ListServiceDto;
	}
}
