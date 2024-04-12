package com.anunciadores.dto;
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


public class RolPersonaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    private int idPersona;

    private int idRol;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public RolPersonaDto(int id, int idPersona, int idRol) {
		super();
		this.id = id;
		this.idPersona = idPersona;
		this.idRol = idRol;
	}

	public RolPersonaDto() {
		super();
	}

	
    
}
