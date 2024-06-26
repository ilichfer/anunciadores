package com.anunciadores.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "persona_ministerio")

public class PersonaMinisterio implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "id_persona")
	private int idPersona;

	@Column(name = "id_ministerio")
	private int idMinisterio;

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getIdMinisterio() {
		return idMinisterio;
	}

	public void setIdMinisterio(int idMinisterio) {
		this.idMinisterio = idMinisterio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

