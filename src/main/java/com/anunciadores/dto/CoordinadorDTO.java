package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.anunciadores.model.Persona;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;


/**
 *
 * @author valbuena
 */

public class CoordinadorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;

	private int idPersona;

	private Date fechaServcio;
	private String fechaString;

	public Date fechaServicio;
	public String notasServicio;

	private Persona persona;

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public Date getFechaServcio() {
		return fechaServcio;
	}

	public void setFechaServcio(Date fechaServcio) {
		this.fechaServcio = fechaServcio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFechaString() {
		return fechaString;
	}

	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}

	public Date getFechaServicio() {
		return fechaServicio;
	}

	public void setFechaServicio(Date fechaServicio) {
		this.fechaServicio = fechaServicio;
	}

	public String getNotasServicio() {
		return notasServicio;
	}

	public void setNotasServicio(String notasServicio) {
		this.notasServicio = notasServicio;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}

