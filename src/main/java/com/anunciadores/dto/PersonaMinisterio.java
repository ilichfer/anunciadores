package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author valbuena
 */

public class PersonaMinisterio implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private List<Integer> idPersona;

	private int idMinisterio;

	private List<String> selecionado;

	private boolean aceptarTerminos;

	private List<Long> categoriasSeleccionadas;

	public List<Long> getCategoriasSeleccionadas() {
		return categoriasSeleccionadas;
	}

	public void setCategoriasSeleccionadas(List<Long> categoriasSeleccionadas) {
		this.categoriasSeleccionadas = categoriasSeleccionadas;
	}

	// Getters y setters
	public boolean isAceptarTerminos() {
		return aceptarTerminos;
	}

	public void setAceptarTerminos(boolean aceptarTerminos) {
		this.aceptarTerminos = aceptarTerminos;
	}

	public List<Integer> getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(List<Integer> idPersona) {
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

	public List<String> getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(List<String> selecionado) {
		this.selecionado = selecionado;
	}
}

