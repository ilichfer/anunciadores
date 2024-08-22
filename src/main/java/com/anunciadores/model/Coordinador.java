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
@Table(name = "coordinador")

public class Coordinador implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "fecha_servicio")
	public Date fechaServicio;

	@Column(name = "notas_servicio")
	public String notasServicio;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_persona", referencedColumnName = "id")
	private Persona persona;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaServicio() {
		return fechaServicio;
	}

	public void setFechaServicio(Date fechaServicio) {
		this.fechaServicio = fechaServicio;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getNotasServicio() {
		return notasServicio;
	}

	public void setNotasServicio(String notasServicio) {
		this.notasServicio = notasServicio;
	}
}
