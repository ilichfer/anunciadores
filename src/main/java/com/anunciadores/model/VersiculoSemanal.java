package com.anunciadores.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "versiculo_semanal")

public class VersiculoSemanal implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "fecha_inicio")
	public Date fechaInicio;

	@Column(name = "fecha_fin")
	public Date fechaFin;

	@Column(name = "versiculo_texto")
	public String versiculoTexto;

	@Column(name = "cita_biblica")
	public String citaBiblica;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getVersiculoTexto() {
		return versiculoTexto;
	}

	public void setVersiculoTexto(String versiculoTexto) {
		this.versiculoTexto = versiculoTexto;
	}

	public String getCitaBiblica() {
		return citaBiblica;
	}

	public void setCitaBiblica(String citaBiblica) {
		this.citaBiblica = citaBiblica;
	}
}
