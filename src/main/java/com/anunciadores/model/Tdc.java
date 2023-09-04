package com.anunciadores.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "tdc")

public class Tdc implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "tdc")
	String tdc;

	@Column(name = "fecha_creacion")
	Date fechaCreacion;
	@Column(name = "id_persona")
	int idPersona;

	@Column(name = "nombre_documento")
	String nombredocumento;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTdc() {
		return tdc;
	}

	public void setTdc(String tdc) {
		this.tdc = tdc;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombredocumento() {
		return nombredocumento;
	}

	public void setNombredocumento(String nombredocumento) {
		this.nombredocumento = nombredocumento;
	}
}

