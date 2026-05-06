package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.anunciadores.model.Persona;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author valbuena
 */


public class AsignacionServicioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String MInisterio;
	private String posiciones;

	public String getMInisterio() {
		return MInisterio;
	}

	public void setMInisterio(String MInisterio) {
		this.MInisterio = MInisterio;
	}

	public String getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(String posiciones) {
		this.posiciones = posiciones;
	}
}
