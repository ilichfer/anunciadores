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

public class MinisterioDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	String nombreMinisterio;

	PosicionDto posicionDto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreMinisterio() {
		return nombreMinisterio;
	}

	public void setNombreMinisterio(String nombreMinisterio) {
		this.nombreMinisterio = nombreMinisterio;
	}

	public PosicionDto getPosicionDto() {
		return posicionDto;
	}

	public void setPosicionDto(PosicionDto posicionDto) {
		this.posicionDto = posicionDto;
	}
}

