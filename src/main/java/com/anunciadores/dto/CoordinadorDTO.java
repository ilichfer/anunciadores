package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
}

