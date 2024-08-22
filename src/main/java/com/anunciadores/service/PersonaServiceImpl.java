package com.anunciadores.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.anunciadores.model.*;
import com.anunciadores.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.anunciadores.dto.PersonaDto;
import com.anunciadores.service.interfaces.ICursoService;
import com.anunciadores.service.interfaces.IPagoService;
import com.anunciadores.service.interfaces.IPersonaService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonaServiceImpl implements IPersonaService {

	private Logger LOGGER = LoggerFactory.getLogger(PersonaServiceImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private IPersonaRepo personaRepository;

	@Autowired
	private IConsolidacionRepo iConsolidacionRepo;

	@Autowired
	private RolesRepoImpl rolesDao;

	@Autowired
	private IRolesRepo rolesRepo;

	@Autowired
	private IPermisosRepo permisosRepo;

	@Autowired
	private IParamMenuRepo paramMenuRepo;

	@Autowired
	private IPermisosSubMenuRepo permisosSubMenuRepo;

	@Autowired
	private IRolesPersonaRepo rolesPersonaRepository;

	@Autowired
	private IEstudiosPersonaRepo estudiosPersonaRepository;

	//@Autowired
	//private PersonaRepoImpl daoPersona;

	@Autowired
	private InscripcionRepo inscripcionesRepository;

	@Autowired
	private ConsolidacionRepoImpl consolidacionDao;
	
	@Autowired
	private IPagoService pagoService;
	@Autowired
	private ICursoService cursoService;

	@Autowired
	private InscripcionActividadRepo inscripcionActividadRepository;
	
	List<Persona> listPersonas;
	List<PersonaDto> listPersonasDto;

	@Override
	public List<Persona> findAllUsuarios() {
		return personaRepository.findUsuarios();
	}

	@Override
	public Persona save(Persona persona) {
		RolPersona rolPersona = new RolPersona();
		persona.setPassword(encriptar(persona.getPassword()));
		Persona personaSave = personaRepository.save(persona);

		rolPersona.setIdPersona(personaSave.getId());
		rolPersona.setIdRol(2);
		rolesPersonaRepository.save(rolPersona);

		return personaSave;
	}

	@Override
	public Persona update(Persona persona) {
		Persona pUpdate = personaRepository.findByDocumento(persona.getDocumento());
		persona.setPassword(pUpdate.getPassword() != null ? pUpdate.getPassword() : "");
		return personaRepository.save(persona);
	}

	@Override
	public Persona findPersonaById(Integer id) {
		Optional<Persona> persona = personaRepository.findById(id);
		return persona.get();
	}

	@Override
	public Persona findPersonaByNombre(String nombre) {
		Persona persona = personaRepository.findByNombre(nombre);
		return persona;
	}

	@Override
	public String delete(Persona persona) {
		List<RolPersona> listaRolPersona = rolesDao.buscarRolesPersona(persona.getId());
		if (listaRolPersona != null) {
			for (RolPersona rol : listaRolPersona) {

				rolesPersonaRepository.delete(rol);
				personaRepository.deleteById(persona.getId());
				return "usuario";
			}

		}
		personaRepository.deleteById(persona.getId());
		return "asistente";
	}

	@Override
	public PersonaDto buscarEmail(String email) {
		Persona per = new Persona();
		PersonaDto personadto = new PersonaDto();
		try {
			per = personaRepository.findByEmail(email);

			//per = daoPersona.buscarEmail(email);
			if (per != null) {
				personadto.setNombre(per.getNombre());
				personadto.setApellido(per.getApellido());
				personadto.setDocumento(per.getDocumento());
				personadto.setTipodocumento(per.getTipodocumento());
				personadto.setEmail(per.getEmail());
				personadto.setId(per.getId());
				personadto.setFechanacimiento(per.getFechanacimiento());
				personadto.setTelefono(per.getTelefono());
				personadto.setPassword(per.getPassword());
				personadto.setRoles(new ArrayList<Rol>());

				List<RolPersona> rol = new ArrayList<>();

				List<Rol> roles = rolesDao.buscarRoles(personadto.getId());

				for (com.anunciadores.model.Rol rolAsignado : roles) {
					if (rolAsignado.getDescripcion().equalsIgnoreCase("ADMINISTRADOR")) {
						personadto.getRoles().add(rolAsignado);
						personadto.setAdmin(true);
						personadto.setUser(false);
					} else {
						personadto.getRoles().add(rolAsignado);
						personadto.setAdmin(false);
						personadto.setUser(true);
					}

				}
			}
//			personadto.setRoles(rolesDao.buscarRoles(personadto.getId()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return personadto;
	}

	@Override
	public List<Persona> findAllByCurso(int idCurso) {
		//listPersonas= daoPersona.buscarPersonaByCurso(idCurso);
		listPersonas= personaRepository.findPersonaByCurso(idCurso);
		return listPersonas;
	}
	
	@Override
	public List<Persona> buscarTodosSinCurso(int idCurso) {
//		List<Persona> listaPersonas=  daoPersona.buscarPersonaSinCurso(idCurso);
//		List<PersonaDto> listaPersonasConsolidacion = new ArrayList<>();
//		listaPersonas.forEach(p -> listaPersonasConsolidacion.add(agregarConsolidacion(p)));
//		return listaPersonasConsolidacion;

		//return daoPersona.buscarPersonaSinCurso(idCurso);
		return personaRepository.findPersonaSinCurso(idCurso);
	}

	@Override
	public void eliminarPersonaCurso(int idPersona, int idCurso) {
		//daoPersona.eliminarPersonaConCurso(idPersona, idCurso);
		try {
			personaRepository.deletePersonaConCurso(idPersona, idCurso);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void eliminarPersonaMinisterio(int idPersona, int idMinisterio) {
		//daoPersona.eliminarPersonaConCurso(idPersona, idCurso);
		try {
			personaRepository.deletePersonaMinisterio(idPersona, idMinisterio);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void agregarPersonaCurso(int idPersona, int idCurso) {
		try {

			Inscripciones inscripcion = new Inscripciones();
			inscripcion.setIdCurso(idCurso);
			inscripcion.setIdPersona(idPersona);

			Inscripciones perInscrita= inscripcionesRepository.save(inscripcion);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	public String encriptPass(String password) {
//		try {
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			byte[] messageDigest = md.digest(password.getBytes());
//			BigInteger number = new BigInteger(1, messageDigest);
//			String hashtext = number.toString(16);
//
//			while (hashtext.length() < 32) {
//				hashtext = "0" + hashtext;
//			}
//			return hashtext;
//		} catch (NoSuchAlgorithmException e) {
//			throw new RuntimeException(e);
//		}
//	}

	@Override
	public void agregarPersonaActividad(int idPersona, int idActividad) {
		try {

			InscripcionActividad inscripcion = new InscripcionActividad();
			inscripcion.setIdActividad(idActividad);
			inscripcion.setIdPersona(idPersona);

			inscripcionActividadRepository.save(inscripcion);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public PersonaDto buscarByDocumento(Integer doc) {
		Persona per = new Persona();
		PersonaDto personadto = new PersonaDto();
		try {

			//per = daoPersona.buscarByDocumento(doc);
			per = personaRepository.findByDocumento(doc);
			if (per != null && per.getDocumento() != null) {
			personadto.setNombre(per.getNombre());
			personadto.setApellido(per.getApellido());
			personadto.setDocumento(per.getDocumento());
			personadto.setTipodocumento(per.getTipodocumento());
			personadto.setEmail(per.getEmail());
			personadto.setId(per.getId());
			personadto.setFechanacimiento(per.getFechanacimiento());
			personadto.setTelefono(per.getTelefono());
			personadto.setPassword(per.getPassword() != null ? per.getPassword() : "");
			personadto.setRoles(new ArrayList<Rol>());
				List<RolPersona> rol = new ArrayList<>();

				List<Rol> roles = rolesDao.buscarRoles(personadto.getId());
				List<PermisosMenu> permisos = null;

				for (com.anunciadores.model.Rol rolAsignado : roles) {
					if (rolAsignado.getDescripcion().equalsIgnoreCase("ADMINISTRADOR")) {
						permisos = permisosRepo.findByIdPersona(personadto.getId());
						personadto.getRoles().add(rolAsignado);
						personadto.setAdmin(true);
						personadto.setUser(false);
						personadto.setPermisosMenu(permisos);
					} else {
						List<ParamMenu> menuList = paramMenuRepo.findAll();
						personadto.getRoles().add(rolAsignado);
						personadto.setAdmin(false);
						personadto.setUser(true);
						personadto.setPermisosMenu(contruirPermisosServidor( menuList));
					}

				}
			}else {
				personadto = new PersonaDto();
			}

		} catch (Exception e) {
			LOGGER.error("[buscarByDocumento] " + e.getMessage());
			e.printStackTrace();
			personadto = new PersonaDto();
			personadto.setId(1);
			throw new RuntimeException("[buscarByDocumento]"+e.getMessage());
		}
		return personadto;
	}

	private List<PermisosMenu> contruirPermisosServidor(List<ParamMenu> menuList){
		List<PermisosMenu> listPermisosIniciales = new ArrayList<>();
		String estadoInicial = "false";

		for (ParamMenu boton : menuList) {
			PermisosMenu permisoInicial = new PermisosMenu();
			permisoInicial.setIdPersona(1);
			//permisoInicial.setIdMenu(boton.getId());
			permisoInicial.setEstado(estadoInicial);
			permisoInicial.setNombreBotonMenu(boton.getNombreBotonMenu());
			permisoInicial.setMenu(boton);
			listPermisosIniciales.add(permisoInicial);
		}
		return listPermisosIniciales;
	}

	@Override
	public Persona saveAsistente(Persona persona) {
		persona.setPassword("");
		persona.setDiscapacidad(persona.getDiscapacidad()!=null?persona.getDiscapacidad():false);
		persona.setPerteneceMinoria(persona.getPerteneceMinoria()!=null?persona.getPerteneceMinoria():false);
		return personaRepository.save(persona);
	}

	@Override
	public Persona saveAsistenteConsolidacion(Persona persona, Consolidacion consolidacion) {
		persona.setPassword("");
		persona.setConsolidacion(true);
		persona.setDiscapacidad(persona.getDiscapacidad()!=null?persona.getDiscapacidad():false);
		persona.setPerteneceMinoria(persona.getPerteneceMinoria()!=null?persona.getPerteneceMinoria():false);
		persona = personaRepository.save(persona);
		consolidacion.setIdPersona(persona.getId());
		consolidacion.setAceptaConsolidacion(persona.getConsolidacion()!=null?persona.getConsolidacion():false);
		iConsolidacionRepo.save(consolidacion);
		return persona;
	}

	@Override
	public List<PersonaDto> buscarConsolidacion(List<Persona> listaPersonas, int idCurso) {
		List<PersonaDto> listaPersonasConsolidacion = new ArrayList<>();
		listaPersonas.forEach(p -> listaPersonasConsolidacion.add(agregarConsolidacion(p,idCurso)));
		return listaPersonasConsolidacion;
	}

	public PersonaDto agregarConsolidacion(Persona persona, int idCurso) {
		PersonaDto perConsolidacion = new PersonaDto();
		perConsolidacion.setNombre(persona.getNombre());
		perConsolidacion.setApellido(persona.getApellido());
		perConsolidacion.setDocumento(persona.getDocumento());
		perConsolidacion.setEmail(persona.getEmail());
		perConsolidacion.setFechanacimiento(persona.getFechanacimiento());
		perConsolidacion.setId(persona.getId());
		perConsolidacion.setTelefono(persona.getTelefono());
		perConsolidacion.setTipodocumento(persona.getTipodocumento());

		inscripcionConsolidacion inscripcionConsolidacion = consolidacionDao.listarConsolidacionByPersona(persona.getId());
		if (inscripcionConsolidacion != null) {
			perConsolidacion.setConsolidacion(true);
		} else {
			perConsolidacion.setConsolidacion(false);
		}
		if (idCurso != 0) {
			List<Pago> pagoList = pagoService.findPagosByIdCurso(persona.getId(), idCurso);
			Curso curso = new Curso();
			curso = cursoService.findCursoById(idCurso);
			int pagoTotal = 0;
			int adeuda = 0;
			
			for (Pago pago : pagoList) {
				pagoTotal = pagoTotal +pago.getValor();
			}
			if (pagoTotal >= curso.getValorTotal()) {
				perConsolidacion.setValidarPago(true);
			};
		}
		
//		cosolidacionRepository.save(consolidacion);

		return perConsolidacion;
	}

	@Override
	public String encriptar(String Pass) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(Pass.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Persona personaDtoToEntity(PersonaDto dto) {
		Persona per = new Persona();
		try {
			per.setNombre(dto.getNombre());
			per.setApellido(dto.getApellido());
			per.setDocumento(dto.getDocumento());
			per.setTipodocumento(dto.getTipodocumento());
			per.setEmail(dto.getEmail());
			per.setId(dto.getId());
			per.setFechanacimiento(dto.getFechanacimiento());
			per.setTelefono(dto.getTelefono());
			per.setPassword(dto.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return per;
	}
	
	@Override
	public Persona savePassword(Persona persona) {
		persona.setPassword(encriptar(persona.getPassword()));
		return personaRepository.save(persona);
	}

	@Override
	public List<PersonaDto> findAllUsuariosRol() {
		List<PersonaDto> listDto = new ArrayList<>();
		List<Persona> personas = personaRepository.findUsuarios();
		personas.forEach(p -> listDto.add(mapPersonaDto(p)));
		return listDto;
	}

	@Override
	public void findUsuariosRol(int idPersona,int idRolNuevo) {
		List<RolPersona> rol = new ArrayList<>();
		rol = rolesPersonaRepository.findRolByidPersona(idPersona);
		RolPersona rolUpdate = rol.get(0);
		rolUpdate.setIdRol(idRolNuevo);
		rolesPersonaRepository.save(rolUpdate);
		if(rolUpdate.getIdRol()==1){
			List<PermisosMenu>listpermisos = permisosRepo.findByIdPersona(idPersona);
			if (listpermisos.size() == 0) {
				List<PermisosMenu> listRolsInicial= crearRolesPrimerVezAdmin(idPersona);
				for (PermisosMenu permiso : listRolsInicial) {
					permisosRepo.save(permiso);
				}
			}
		}

	}

	private List<PermisosMenu> crearRolesPrimerVezAdmin(int idPersona) {
		List<ParamMenu> menuList = paramMenuRepo.findAll();

		List<PermisosMenu> listPermisosIniciales = new ArrayList<>();
		String estadoInicial = "true";

		for (ParamMenu boton : menuList) {
			PermisosMenu permisoInicial = new PermisosMenu();
			permisoInicial.setIdPersona(idPersona);
			//permisoInicial.setIdMenu(boton.getId());
			if (boton.getNombreBotonMenu().equals("menuAdministrar")) {
				permisoInicial.setEstado("false");
			}else{
				permisoInicial.setEstado(estadoInicial);
			}
			permisoInicial.setNombreBotonMenu(boton.getNombreBotonMenu());
			listPermisosIniciales.add(permisoInicial);
		}
		return listPermisosIniciales;
	}

	@Override
	public List<EstudioPersona> findEstudiosPersona(int idPersona) {
		List<EstudioPersona> estudios = estudiosPersonaRepository.findByIdPersona(idPersona);
		return estudios;
	}

	@Override
	public EstudioPersona saveEstudio(EstudioPersona estudioNew) {
		EstudioPersona estudio = estudiosPersonaRepository.save(estudioNew);
		return estudio;
	}

	private PersonaDto mapPersonaDto(Persona persona){
		PersonaDto dto = new PersonaDto();
		try {
			dto.setNombre(persona.getNombre());
			dto.setApellido(persona.getApellido());
			dto.setDocumento(persona.getDocumento());
			dto.setTipodocumento(persona.getTipodocumento());
			dto.setEmail(persona.getEmail());
			dto.setId(persona.getId());
			dto.setFechanacimiento(persona.getFechanacimiento());
			dto.setTelefono(persona.getTelefono());
			dto.setPassword(persona.getPassword());
			List<Rol> roles = rolesDao.buscarRoles(persona.getId());
			roles.forEach(r -> dto.setRolUnico(r) );
			dto.setRoles(roles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}


}
