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
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "rol")

public class rol implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
	@Basic(optional = false)
	@NotNull
    
    @Column(name = "id")
    private int id;
    
    @Column(name = "descripcion_rol")
    private String descripcion;

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

	public rol(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public rol() {
		super();
	}

	
	
	
}
