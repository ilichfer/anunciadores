package com.anunciadores.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.anunciadores.dto.PersonaDto;
import com.anunciadores.model.Consolidacion;
import com.anunciadores.model.EstudioPersona;
import com.anunciadores.model.Pago;
import com.anunciadores.model.Persona;


public interface IPersonaService {
	public List<Persona> findAllUsuarios();

	public Persona save(Persona persona);
	public Persona update(Persona persona);
	
	public Persona saveAsistente(Persona persona);
	public Persona saveAsistenteConsolidacion(Persona persona, Consolidacion consolidacion);

	public Persona findPersonaById(Integer id);
	public Persona findPersonaByNombre(String nombre);

	public String delete(Persona persona);

	public PersonaDto buscarByDocumento(Integer doc);
	
	public PersonaDto buscarEmail(String email);

	public List<Persona> findAllByCurso(int idCurso);

	public List<Persona> buscarTodosSinCurso(int idCurso);

	void agregarPersonaCurso(int idPersona, int idCurso);

	void eliminarPersonaCurso(int idPersona, int idCurso);
	void eliminarPersonaMinisterio(int idPersona, int idMinisterio);

	void agregarPersonaActividad(int idPersona, int idActividad);
	
	public List<PersonaDto> buscarConsolidacion(List<Persona> listaPersonas,int idCUerso);
	
	public String encriptar(String Pass);

	public Persona personaDtoToEntity(PersonaDto dto);

	public Persona savePassword(Persona persona);

	//List<PersonaDto> findCursoValorByPersona(List<Persona> personas);

	public List<PersonaDto> findAllUsuariosRol();
	public List<PersonaDto> findBirthdayByMonth();

	public void findUsuariosRol(int idPersona,int idRolNuevo);

	List<EstudioPersona> findEstudiosPersona (int idPersona);

	public EstudioPersona saveEstudio(EstudioPersona estudio);

	public List<PersonaDto> getBirthDay (List<PersonaDto> listDto);
}
