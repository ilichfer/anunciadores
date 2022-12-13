package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.anunciadores.model.Persona;

/**
 *
 * @author valbuena
 */


public class CursoDto {
    
    private int id;
    
    private int idPersona;

    private int idPago;

    private String nombreCurso;
    
    private String fechaInicio;
    
    private String fechaFin;
        
    private int valorTotal;  
    
    private Boolean check;
    
    private PersonaDto personaAConsolidar;
    
      public CursoDto() {
    }

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

	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}


	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}

	public PersonaDto getPersonaAConsolidar() {
		return personaAConsolidar;
	}

	public void setPersonaAConsolidar(PersonaDto personaAConsolidar) {
		this.personaAConsolidar = personaAConsolidar;
	}
}
