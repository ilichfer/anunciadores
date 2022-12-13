package com.anunciadores.dto;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author valbuena
 */


public class ActividadDto {
    
    private int id;
    
    private String nombreActividad;

    private String fecha;
    
    private int cantidadMesas;
    
    private List<MesaDto> mesas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<MesaDto> getMesas() {
		return mesas;
	}

	public void setMesas(List<MesaDto> mesas) {
		this.mesas = mesas;
	}

	public int getCantidadMesas() {
		return cantidadMesas;
	}

	public void setCantidadMesas(int cantidadMesas) {
		this.cantidadMesas = cantidadMesas;
	}
	
	
    
}
