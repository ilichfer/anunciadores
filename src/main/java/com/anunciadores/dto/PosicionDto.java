package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;


/**
 *
 * @author valbuena
 */

public class PosicionDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	String nombrePosicion;

	PersonaDto personaDto;
	String asistencia;
	private int idMinisterio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombrePosicion() {
		return nombrePosicion;
	}

	public void setNombrePosicion(String nombrePosicion) {
		this.nombrePosicion = nombrePosicion;
	}

	public PersonaDto getPersonaDto() {
		return personaDto;
	}

	public void setPersonaDto(PersonaDto personaDto) {
		this.personaDto = personaDto;
	}

	public int getIdMinisterio() {
		return idMinisterio;
	}

	public void setIdMinisterio(int idMinisterio) {
		this.idMinisterio = idMinisterio;
	}

	public String getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}
}

