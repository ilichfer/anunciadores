package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author valbuena
 */


public class AsignacionConsolidacionDto implements Serializable {
    private static final long serialVersionUID = 1L;

	private Integer idConsolidacion;
	private String nombre;

	private String telefono;

	private String apellido;

	private Integer documento;

	private int idPersona;

	private Boolean aceptaConsolidacion;

	private String horarioConsolidacionSugerido;

	private String horarioConsolidacionPersona;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getDocumento() {
		return documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public Boolean getAceptaConsolidacion() {
		return aceptaConsolidacion;
	}

	public void setAceptaConsolidacion(Boolean aceptaConsolidacion) {
		this.aceptaConsolidacion = aceptaConsolidacion;
	}

	public String getHorarioConsolidacionSugerido() {
		return horarioConsolidacionSugerido;
	}

	public void setHorarioConsolidacionSugerido(String horarioConsolidacionSugerido) {
		this.horarioConsolidacionSugerido = horarioConsolidacionSugerido;
	}

	public String getHorarioConsolidacionPersona() {
		return horarioConsolidacionPersona;
	}

	public void setHorarioConsolidacionPersona(String horarioConsolidacionPersona) {
		this.horarioConsolidacionPersona = horarioConsolidacionPersona;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getIdConsolidacion() {
		return idConsolidacion;
	}

	public void setIdConsolidacion(Integer idCosolidacion) {
		this.idConsolidacion = idCosolidacion;
	}
}
