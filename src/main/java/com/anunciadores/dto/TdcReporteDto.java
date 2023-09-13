package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.anunciadores.model.Persona;

import java.io.Serializable;
import java.sql.Date;


/**
 *
 * @author valbuena
 */

public class TdcReporteDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idPersona;
	private int cantidadEntregados;
	private String nombre;

	public int getCantidadEntregados() {
		return cantidadEntregados;
	}

	public void setCantidadEntregados(int cantidadEntregados) {
		this.cantidadEntregados = cantidadEntregados;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
}

