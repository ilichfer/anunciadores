package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;


/**
 *
 * @author valbuena
 */


public class ConsolidacionDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    
    private int idPadreEspiritual;

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

	public ConsolidacionDto(int id, int idPadreEspiritual, int idPersonaConsolidar) {
		super();
		this.id = id;
		this.idPadreEspiritual = idPadreEspiritual;
		this.idPersonaConsolidar = idPersonaConsolidar;
	}

	public ConsolidacionDto() {
		super();
	}
    
}
