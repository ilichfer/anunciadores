package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.List;


/**
 *
 * @author valbuena
 */

public class ServicioListResponseDto implements Serializable {

	private List<ServicioResponseDto> servicioDTO;
	private String fechaServcio;
	private String nombreMinisterio;
	private Integer idMinisterio;

	private Integer tamanoLista;

	public List<ServicioResponseDto> getServicioDTO() {
		return servicioDTO;
	}

	public void setServicioDTO(List<ServicioResponseDto> servicioDTO) {
		this.servicioDTO = servicioDTO;
	}

	public String getFechaServcio() {
		return fechaServcio;
	}

	public void setFechaServcio(String fechaServcio) {
		this.fechaServcio = fechaServcio;
	}

	public String getNombreMinisterio() {
		return nombreMinisterio;
	}

	public void setNombreMinisterio(String nombreMinisterio) {
		this.nombreMinisterio = nombreMinisterio;
	}

	public Integer getIdMinisterio() {
		return idMinisterio;
	}

	public void setIdMinisterio(Integer idMinisterio) {
		this.idMinisterio = idMinisterio;
	}

	public Integer getTamanoLista() {
		return tamanoLista;
	}

	public void setTamanoLista(Integer tamanoLista) {
		this.tamanoLista = tamanoLista;
	}
}

