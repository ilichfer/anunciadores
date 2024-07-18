package com.anunciadores.service.interfaces;

import com.anunciadores.dto.*;
import com.anunciadores.model.Ministerio;
import com.anunciadores.model.Persona;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface IServicioService {

	public List<Ministerio> getAll();

	Ministerio saveMinisterio(String nombreMinisterio);

	Ministerio saveMinisterio(MinisterioDto ministerio);

	public void deleteMinisterio(int idMinisterio);

	public Ministerio findByidMnisterio(int idMinisterio);
	public List<PersonaDto> findPersonaByidMnisterio(int idMinisterio);

	public List<MinisterioDto> getPositionByidMinisterio(int idMinisterio);

	List<MinisterioDto> getPositionByidMinisterioAndPerson(Date fecha, int idMinisterio);

	public List<PersonaDto> getPeopleWithoutMinisterio(int idMinisterio);

	public void saveProgramacion(ServicioDto servidores, Date fechaServicio);

	void updateProgramacion(ServicioDto servidores, Date fechaServicio);

	public Optional<Persona> validarProgramacionByFecha(ServicioDto servidores, Date fechaServicio);

	Optional<Persona> validarActualizarProgramacionByFecha(ServicioDto servidores, Date fechaServicio, int ministerio);

	public List<ServicioListResponseDto>findProgramacionByDate(Date fechaActual);
	public List<ServicioListResponseDto>findProgramacionByDateAndMinisterio(Date fechaActual, int idMinisterio);

	public  List<ServicioListResponseDto> findProgramacionByDateGroup(Date fechaActual);

	void agregarPersonaAMinisterio(int idPersona, int idMinisterio);

	void savePosicion(PosicionDto posicionDto);

	void editPosicion(PosicionDto posicionDto);

	PosicionDto findPosicion(int idposicion);

	public Boolean validarDuplicados(ServicioDto servidores);

	public List<MinisterioDto> poblarPosiciones(List<MinisterioDto>  ministerios,ServicioDto servicioDto);

}
