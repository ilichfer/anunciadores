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
@Table(name = "consolidacion")

public class Consolidacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
	@Basic(optional = false)
	@NotNull
	
    @Column(name = "id")
    private int id;
    
    @Column(name = "id_padre_espiritual")
    private int idPadreEspiritual;

    @Column(name = "id_persona_consolidar")
    private int idPersonaConsolidar;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPadreEspiritual() {
		return idPadreEspiritual;
	}

	public void setIdPadreEspiritual(int idPadreEspiritual) {
		this.idPadreEspiritual = idPadreEspiritual;
	}

	public int getIdPersonaConsolidar() {
		return idPersonaConsolidar;
	}

	public void setIdPersonaConsolidar(int idPersonaConsolidar) {
		this.idPersonaConsolidar = idPersonaConsolidar;
	}

	public Consolidacion(int id, int idPadreEspiritual, int idPersonaConsolidar) {
		super();
		this.id = id;
		this.idPadreEspiritual = idPadreEspiritual;
		this.idPersonaConsolidar = idPersonaConsolidar;
	}

	public Consolidacion() {
		super();
	}
    
}
