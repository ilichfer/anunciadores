package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.anunciadores.model.PermisosMenu;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author valbuena
 */

public class PermisosDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Integer> id;

	public List<String> nombreBotonMenu;

	public List<Boolean> estado;

	public List<Integer> getId() {
		return id;
	}

	public void setId(List<Integer> id) {
		this.id = id;
	}

	public List<String> getNombreBotonMenu() {
		return nombreBotonMenu;
	}

	public void setNombreBotonMenu(List<String> nombreBotonMenu) {
		this.nombreBotonMenu = nombreBotonMenu;
	}

	public List<Boolean> getEstado() {
		return estado;
	}

	public void setEstado(List<Boolean> estado) {
		this.estado = estado;
	}
}

