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


public class MensajesDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    

    private String descripcion;

	private Persona remitente;

	private Persona destinatario;


	private Date fechaRegistro;
	private String fechaMostrar;


	private boolean activo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Persona getRemitente() {
		return remitente;
	}

	public void setRemitente(Persona remitente) {
		this.remitente = remitente;
	}

	public Persona getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Persona destinatario) {
		this.destinatario = destinatario;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getFechaMostrar() {
		return fechaMostrar;
	}

	public void setFechaMostrar(String fechaMostrar) {
		this.fechaMostrar = fechaMostrar;
	}
}
