package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.List;
import java.sql.Date;


/**
 *
 * @author valbuena
 */

public class ServicioDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<String> posicion;

	private List<String> encargado;
	private List<String> asistencia;

	private Date fechaServcio;

	public List<String> getPosicion() {
		return posicion;
	}

	public void setPosicion(List<String> posicion) {
		this.posicion = posicion;
	}

	public List<String> getEncargado() {
		return encargado;
	}

	public void setEncargado(List<String> encargado) {
		this.encargado = encargado;
	}

	public Date getFechaServcio() {
		return fechaServcio;
	}

	public void setFechaServcio(Date fechaServcio) {
		this.fechaServcio = fechaServcio;
	}

	public List<String> getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(List<String> asistencia) {
		this.asistencia = asistencia;
	}
}

