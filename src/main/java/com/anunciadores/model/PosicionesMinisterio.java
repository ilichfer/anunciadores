package com.anunciadores.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.persistence.*;
import java.io.Serializable;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "posiciones_ministerios")

public class PosicionesMinisterio implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "id_ministerio")
	private int idMinisterio;

	@Column(name = "nombre_posicion")
	private String nombrePosicion;

	@Override
	public String toString() {
		return "PosicionesMinisterio{" +
				"id=" + id +
				", idMinisterio=" + idMinisterio +
				", nombrePosicion='" + nombrePosicion + '\'' +
				'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombrePosicion() {
		return nombrePosicion;
	}

	public void setNombrePosicion(String nombrePosicion) {
		this.nombrePosicion = nombrePosicion;
	}

	public int getIdMinisterio() {
		return idMinisterio;
	}

	public void setIdMinisterio(int idMinisterio) {
		this.idMinisterio = idMinisterio;
	}
}

