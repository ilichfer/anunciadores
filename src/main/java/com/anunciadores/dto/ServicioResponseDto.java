package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 *
 * @author valbuena
 */

public class ServicioResponseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String posicion;

	private String encargado;

	private String fechaServcio;
	private String nombreMinisterio;
	private Integer idMinisterio;

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getEncargado() {
		return encargado;
	}

	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}

	public String getFechaServcio() {
		return fechaServcio;
	}

	public void setFechaServcio(String fechaServcio) {
		this.fechaServcio = fechaServcio;
	}

	public String getNombreMinisterio() {
		return nombreMinisterio;
	}

	public void setNombreMinisterio(String nombreMinisterio) {
		this.nombreMinisterio = nombreMinisterio;
	}

	public Integer getIdMinisterio() {
		return idMinisterio;
	}

	public void setIdMinisterio(Integer idMinisterio) {
		this.idMinisterio = idMinisterio;
	}

	public ServicioResponseDto(String posicion, String encargado, String fechaServcio, String nombreMinisterio, Integer idMinisterio) {
		this.posicion = posicion;
		this.encargado = encargado;
		this.fechaServcio = fechaServcio;
		this.nombreMinisterio = nombreMinisterio;
		this.idMinisterio = idMinisterio;
	}
}

