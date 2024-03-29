package com.anunciadores.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.anunciadores.model.*;
import com.anunciadores.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.anunciadores.dto.PersonaDto;
import com.anunciadores.service.interfaces.ICursoService;
import com.anunciadores.service.interfaces.IPagoService;
import com.anunciadores.service.interfaces.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private IPersonaRepo personaRepository;

	@Autowired
	private RolesRepoImpl rolesDao;

	@Autowired
	private IRolesRepo rolesRepo;

	@Autowired
	private IRolesPersonaRepo rolesPersonaRepository;

	@Autowired
	private PersonaRepoImpl daoPersona;

	@Autowired
	private InscripcionRepo inscripcionesRepository;

	@Autowired
	private ConsolidacionRepoImpl consolidacionDao;
	
	@Autowired
	private IPagoService pagoService;
	@Autowired
	private ICursoService cursoService;
	
	List<Persona> listPersonas;
	List<PersonaDto> listPersonasDto;

	@Override
	public List<Persona> findAllUsuarios() {
		return daoPersona.buscarUsuarios();
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
	public Persona findPersonaById(Integer id) {
		Optional<Persona> persona = personaRepository.findById(id);
		return persona.get();
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

			per = daoPersona.buscarEmail(email);
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

				List<Rol> roles = rolesDao.buscarRoles(personadto.getId());

				for (com.anunciadores.model.Rol rol : roles) {
					if (rol.getDescripcion().equalsIgnoreCase("ADMINISTRADOR")) {
						personadto.getRoles().add(rol);
						personadto.setAdmin(true);
						personadto.setUser(false);
					} else {
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
		listPersonas= daoPersona.buscarPersonaByCurso(idCurso);
		return listPersonas;
	}
	
	@Override
	public List<Persona> buscarTodosSinCurso(int idCurso) {
//		List<Persona> listaPersonas=  daoPersona.buscarPersonaSinCurso(idCurso);
//		List<PersonaDto> listaPersonasConsolidacion = new ArrayList<>();
//		listaPersonas.forEach(p -> listaPersonasConsolidacion.add(agregarConsolidacion(p)));
//		return listaPersonasConsolidacion;

		return daoPersona.buscarPersonaSinCurso(idCurso);
	}

	@Override
	public void eliminarPersonaCurso(int idPersona, int idCurso) {
		daoPersona.eliminarPersonaConCurso(idPersona, idCurso);
	}

	@Override
	public void agregarPersonaCurso(int idPersona, int idCurso) {
		try {
			daoPersona.agregarPersonaConCurso(idPersona, idCurso);

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
			daoPersona.agregarPersonaActividad(idPersona, idActividad);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public PersonaDto buscarByDocumento(Integer doc) {
		Persona per = new Persona();
		PersonaDto personadto = new PersonaDto();
		try {

			per = daoPersona.buscarByDocumento(doc);
			if (per.getDocumento() != null) {							
			personadto.setNombre(per.getNombre());
			personadto.setApellido(per.getApellido());
			personadto.setDocumento(per.getDocumento());
			personadto.setTipodocumento(per.getTipodocumento());
			personadto.setEmail(per.getEmail());
			personadto.setId(per.getId());
			personadto.setFechanacimiento(per.getFechanacimiento());
			personadto.setTelefono(per.getTelefono());
			personadto.setPassword(per.getPassword() != null ? per.getPassword() : "");
			}else {
				personadto = new PersonaDto();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return personadto;
	}

	@Override
	public Persona saveAsistente(Persona persona) {
		persona.setPassword("");
		return personaRepository.save(persona);
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

		Consolidacion consolidacion = consolidacionDao.listarConsolidacionByPersona(persona.getId());
		if (consolidacion != null) {
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
		List<Persona> personas = daoPersona.buscarUsuarios();
		personas.forEach(p -> listDto.add(mapPersonaDto(p)));
		return listDto;
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
