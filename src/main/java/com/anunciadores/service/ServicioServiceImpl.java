package com.anunciadores.service;

import com.anunciadores.dto.*;
import com.anunciadores.enums.ECombos;
import com.anunciadores.mapper.mapperParametros;
import com.anunciadores.model.*;
import com.anunciadores.repository.*;
import com.anunciadores.service.interfaces.IServicioService;
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
public class ServicioServiceImpl implements IServicioService {

	private Logger LOGGER = LoggerFactory.getLogger(ServicioServiceImpl.class);

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
	public List<PersonaDto> findPersonaByidMnisterioAsistencia(int idMinisterio) {
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
	public List<MinisterioDto> limpiarListaPosiciones(List<MinisterioDto> posiciones,Date fechaServicio,int idMinisterio) {
		List<MinisterioDto> listaResponse = new ArrayList<>();
		List<Servicio> servicio = servicioRepository.findByFechaServicioAndIdMinisterio( fechaServicio, idMinisterio);

		for (MinisterioDto min: posiciones) {
			for (int i = 0; i < servicio.size(); i++) {
				if (servicio.get(i).getIdPosicion() == min.getPosicionDto().getId() ) {
					min.getPosicionDto().setAsistencia(servicio.get(i).getAsistencia());
					listaResponse.add(min);
				}
			}
		}


		return listaResponse;
	}


	@Override
	public List<PersonaDto> getPeopleWithoutMinisterio(int idMinisterio) {
		List<PersonaDto> listaDto = new ArrayList<>();
		List<Persona> listaEntity= ministerioRepository.findPeopleWithOutMinisterio(idMinisterio);
		listaEntity.forEach(p -> listaDto.add(mapPersonaToDto(p)));
		return listaDto;
	}

	@Override
	public void saveProgramacion(ServicioDto servidores, Date fechaServicio,int idMinisterio) {

		List<Servicio> progServicio = new ArrayList<>();
		try {


		for (int i = 0; i < servidores.getPosicion().size(); i++) {
			Servicio servicio = new Servicio();
			List<PosicionesMinisterio> posicionEntity = posicionesRepository.findMinisterioByName(servidores.getPosicion().get(i),idMinisterio);
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
	public void saveCoordinado(CoordinadorDTO cordinador) {
		try {
			Coordinador cor = new Coordinador();
			Persona per = personaRepository.findById(cordinador.getIdPersona()).get();
			if (cordinador.getId() != 0) {
				cor.setId(cordinador.getId());
			}
			cor.setPersona(per);
			cor.setFechaServicio(cordinador.getFechaServcio());
			coordinadorRepo.save(cor);
		}catch (Exception e) {
		throw e;
		}
	}

	@Override
	public void saveCoordinadorEntity(Coordinador cordinador) {
		try {
			coordinadorRepo.save(cordinador);
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Coordinador findCoordinador(List<ServicioListResponseDto> listProgramacionMinisterio) {
		Coordinador cor = new Coordinador();
		try {
			for (ServicioListResponseDto serv : listProgramacionMinisterio) {
				if (serv.getFechaServcio() != null) {
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
					Date fechaDate = null;
					try {
						fechaDate = formato.parse(serv.getFechaServcio());
					} catch (ParseException e) {
						LOGGER.error("[findCoordinador] " + e.getMessage());
						throw new RuntimeException("[findCoordinador] " +e.getMessage());
					}
					return coordinadorRepo.findByFechaServicio(fechaDate);
				}
			}
		}catch (Exception e){
			LOGGER.error("[findCoordinador] " + e.getMessage());
			throw new RuntimeException("[findCoordinador] " +e.getMessage());
		}
		return cor;
	}

	@Override
	public Coordinador findCoordinadorAdministrator(HttpServletRequest request) {
		HttpSession misession = request.getSession();
		Coordinador cor = new Coordinador();
		Integer menuCordinador= 9;
		Integer idpersona = (int) misession.getAttribute("idPersona");
		try {
			Persona per = personaRepository.findPersonaAndIdMenu(idpersona,menuCordinador);
			cor.setId(per.getId());
			cor.setPersona(per);
		}catch (Exception e){
			e.getMessage();
		}


		return cor;
	}

	@Override
	public Coordinador findCoordinadorByFecha(Date fechaServicio) {
		return coordinadorRepo.findByFechaServicio(fechaServicio);
	}

	@Override
	public Coordinador findCoordinadorByFechaAndIdPersona(String fechaServicio, int idPersona) {
		Date fechaDate= null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fechaDate = formato.parse(fechaServicio);
		} catch (ParseException e) {
			LOGGER.error("[findCoordinador] " + e.getMessage());
			throw new RuntimeException("[findCoordinador] " +e.getMessage());
		}
		Optional<Coordinador > CorOpt = coordinadorRepo.findByIdPersonaAndIdPersona(fechaDate,idPersona);
		if (CorOpt.isPresent()) {
			return CorOpt.get();
		}
		return new Coordinador();
	}

	@Override
	public Boolean validateCoordinadorByFechaAndIdPersona(String fechaServicio, int idPersona) {
		Date fechaDate= null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fechaDate = formato.parse(fechaServicio);
		} catch (ParseException e) {
			LOGGER.error("[findCoordinador] " + e.getMessage());
			throw new RuntimeException("[findCoordinador] " +e.getMessage());
		}
		Optional<Coordinador > CorOpt = coordinadorRepo.findByIdPersonaAndIdPersona(fechaDate,idPersona);
		if (CorOpt.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void updateProgramacion(ServicioDto servidores, Date fechaServicio,int idMinisterio) {

		List<Servicio> progServicio = new ArrayList<>();
		try {
			servicioRepository.deleteByFechaServicioAndIdMinisterio(fechaServicio,idMinisterio);
			for (int i = 0; i < servidores.getPosicion().size(); i++) {
				Servicio servicio = new Servicio();
				List<PosicionesMinisterio> posicionEntity = posicionesRepository.findMinisterioByName(servidores.getPosicion().get(i),idMinisterio);
				servicio.setFechaServicio(fechaServicio);
				servicio.setIdMinisterio(idMinisterio);
				servicio.setIdPersona(Integer.parseInt(servidores.getEncargado().get(i)));
				servicio.setIdPosicion(posicionEntity.get(0).getId());
				progServicio.add(servicio);
			}
			servicioRepository.saveAll(progServicio);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public void deleteProgramacion(Date fechaServicio) {
		try {
			//servicioRepository.deleteByFechaServicioAndIdMinisterio(fechaServicio);
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
	public boolean validarActualizarProgramacionByFechaAndName(ServicioDto servidores, Date fechaServicio, int ministerio) {

		try {
			for (int i = 0; i < servidores.getEncargado().size(); i++) {
				Optional<Servicio> programacion = 	servicioRepository.findProgramacionServidorAndMinisterio(servidores.getEncargado().get(i), fechaServicio, servidores.getPosicion().get(i));

				if(programacion.isPresent()){
					Servicio serSave = new Servicio();
					serSave = programacion.get();
					serSave.setAsistencia(servidores.getAsistencia().get(i));
					serSave = 	servicioRepository.save(serSave);
				}
			}

		}catch (Exception e){
			e.printStackTrace();
			return false;
		}

		return true;
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
		List<ServicioListResponseDto> ListServiceDto = new ArrayList<>();
		List<Object> obj = new ArrayList<>();
		List<ServicioResponseDto> ListServicioDto = new ArrayList<>();
		obj = servicioRepository.findProgramacionByDateAndidMinistery(fechaActual, idMinisterio);
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
		ListServiceDto = buscarMinistarios(ListServicioDto);
		return ListServiceDto;
	}

	@Override
	public List<ServicioListResponseDto> findProgramacionByDateGroup(Date fechaActual) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<ServicioListResponseDto> ListServiceDto = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
		String fechaActualStr = nowInBogota.format(formatter);


		try {
			Date fechaActual1 = sdf.parse(fechaActualStr);
			LocalDate fechaActualizada = nowInBogota.toLocalDate();


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
						LOGGER.error("[findProgramacionByDateGroup] " + e.getMessage());
						throw new RuntimeException("[findProgramacionByDateGroup]"+e);
					}
				});
				break;
			}
		}

		ListServiceDto = buscarMinistarios(ListServicioDto);
		}catch (Exception e){
			LOGGER.error("[findProgramacionByDateGroup] " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("[findProgramacionByDateGroup]"+e);
		}

	//return ListServicioDto;
		return ListServiceDto;
	}

	@Override
	public List<ItemCombo> findItemsCombo() {
		 return mapperParametros.listEntitytoListDto(parametrosRepo.findByGrupo(ECombos.ASISTENCIA.toString()));
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
	public List<ServicioResponseDto> buscarProgramacionMes(int idPersona) throws ParseException {
		List<ServicioResponseDto> listaRespuestas = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaActual = sdf.format(new Date());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse(fechaActual, dtf);
		int mes=0;
		try {
			for ( int i = 0; i < 2; i++) {

				if (i > 0) {
					ld = ld.plusDays(1);
				}
				int monthDays = ld.lengthOfMonth();
				int yearDays = ld.lengthOfYear();
				int year = ld.getYear();
				int month = ld.getMonthValue();

				System.out.printf("Mes % 4d de %d tiene %d días%nAño %d tiene %d días",
						month, year, monthDays,
						year, yearDays);

				String fechainicial = year + "-" + month + "-1";
				String fechaFinal = year + "-" + month + "-" + monthDays;

				Date date1 = sdf.parse(fechainicial);
				Date date2 = sdf.parse(fechaFinal);

				if(month>mes) {
					List<Servicio> servicio = servicioRepository.BuscarServicioMes(date1, date2, idPersona);
					if (!servicio.isEmpty()) {
						servicio.forEach(s -> listaRespuestas.add(buildServicioResponseDto(s)));
					}
					List<Coordinador> listaCoordinador = coordinadorRepo.buscarServicioCoordinadorMes(date1, date2, idPersona);
					if (!listaCoordinador.isEmpty()) {
						listaCoordinador.forEach(c -> listaRespuestas.add(buildServicioCoordinadorResponseDto(c)));
					}
					mes = month;
				}

				if(!listaRespuestas.isEmpty()){
					listaRespuestas.sort(Comparator.comparing(ServicioResponseDto::getFechaServcio));
				}
			}
		}catch (Exception e){

		}
		return listaRespuestas;
	}


	private ServicioResponseDto buildServicioResponseDto(Servicio servicio){
		ServicioResponseDto serv = new ServicioResponseDto();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		serv.setFechaServcio(sdf.format(servicio.getFechaServicio()));
		serv.setEncargado(personaRepository.findById(servicio.getIdPersona()).get().getNombre());
		serv.setPosicion(posicionesRepository.findById(servicio.getIdPosicion()).get().getNombrePosicion());
		Optional<Ministerio> ministerio = ministerioRepository.findById(servicio.getIdMinisterio());
		serv.setNombreMinisterio(ministerio.get().getNombre());
		serv.setIdMinisterio(ministerio.get().getId());
		return serv;
	}

	private ServicioResponseDto buildServicioCoordinadorResponseDto(Coordinador coordinador){
		ServicioResponseDto serv = new ServicioResponseDto();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		serv.setFechaServcio(sdf.format(coordinador.getFechaServicio()));
		serv.setEncargado(coordinador.getPersona().getNombre());
		serv.setPosicion("Coordinador");
		serv.setNombreMinisterio("Coordinador");
		return serv;
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

			for (int i = 0; i < servidores.getEncargado().size(); i++) {
				if (servidores.getEncargado().get(i).equals(89898989)){
					System.out.println(servidores.getEncargado().get(i));
				}
			}

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
	public Persona identificarDuplicados(ServicioDto servidores) {
		final Persona[] pDuplicada = {new Persona()};
		int cont=0;
		for (String e:servidores.getEncargado()) {
			System.out.println("inicia ------> " +e);
			cont=0;
				for (String enc:servidores.getEncargado()) {
					if(enc.equals(e)){
						try {
							cont++;

						}catch (Exception ex){
							ex.printStackTrace();
						}
					}
					System.out.println("interno enc " +enc +" " + cont);
					if (cont > 1){
						return personaRepository.findById(Integer.parseInt(e)).get();
					}
				}

			}
		return pDuplicada[0];
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
				}else if(!m.getPosicionDto().getNombrePosicion().equals(servicioDto.getPosicion().get(i)) &&
						m.getPosicionDto().getPersonaDto()==null){
					PersonaDto perMinisterio= new PersonaDto();
					perMinisterio.setId(0);
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
		if (dto.getPosicionDto().getPersonaDto()==null){
			PersonaDto personaDto = new PersonaDto();
			personaDto.setId(0);
			dto.getPosicionDto().setPersonaDto(personaDto);
		}
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
		servicioDto.setAsistenciaList(mapperParametros.listEntitytoListDto(parametrosRepo.findByGrupo(ECombos.ASISTENCIA.toString())));
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
