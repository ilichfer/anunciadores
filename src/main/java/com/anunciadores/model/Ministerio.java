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
import java.sql.Date;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "ministerios")

public class Ministerio implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	String nombre;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

