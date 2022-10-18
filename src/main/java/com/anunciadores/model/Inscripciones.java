package com.anunciadores.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.NotNull;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "inscripciones")

public class Inscripciones implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_inscripcion")
	@SequenceGenerator(name = "sec_inscripcion", sequenceName = "sec_inscripcion", allocationSize = 1)
    @Column(name = "id")
    private int id;
	
    @Column(name = "id_Persona")
    private int idPersona;
	
    @Column(name = "id_Curso")
    private int idCurso;

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
}
