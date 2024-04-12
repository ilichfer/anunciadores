package com.anunciadores.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

import javax.persistence.*;

import com.sun.istack.NotNull;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "mesa")

public class Mesa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    
    @Column(name = "id")
    private int id;
    
    @Column(name = "id_actividad")
    private int idActividad;
    
    @Column(name = "nombre_mesa")
    private String nombreMesa;
    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}

	public String getNombreMesa() {
		return nombreMesa;
	}

	public void setNombreMesa(String nombreMesa) {
		this.nombreMesa = nombreMesa;
	}

	public Mesa(int id, int idActividad, String nombreMesa) {
		super();
		this.id = id;
		this.idActividad = idActividad;
		this.nombreMesa = nombreMesa;
	}

	public Mesa() {
		super();
	}
	
	
	
}
