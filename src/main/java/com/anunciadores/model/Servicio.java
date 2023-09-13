package com.anunciadores.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "servicio")

public class Servicio implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "id_persona")
	private int idPersona;

	@Column(name = "id_posicion")
	private int idPosicion;

	@Column(name = "id_ministerio")
	private int idMinisterio;

	@Column(name = "fecha_servicio")
	Date fechaServicio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getIdPosicion() {
		return idPosicion;
	}

	public void setIdPosicion(int idPosicion) {
		this.idPosicion = idPosicion;
	}

	public int getIdMinisterio() {
		return idMinisterio;
	}

	public void setIdMinisterio(int idMinisterio) {
		this.idMinisterio = idMinisterio;
	}

	public Date getFechaServicio() {
		return fechaServicio;
	}

	public void setFechaServicio(Date fechaServicio) {
		this.fechaServicio = fechaServicio;
	}
}

