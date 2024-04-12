package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;

/**
 *
 * @author valbuena
 */


public class PagoDto {
 
    private int idPersona; 
    private int idCurso;
    private int valorTotal;
    private int valorPagado;

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public int getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getValorPagado() {
		return valorPagado;
	}

	public void setValorPagado(int valorPagado) {
		this.valorPagado = valorPagado;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public PagoDto(int idPersona, int idCurso, int valorTotal, int valorPagado) {
		super();
		this.idPersona = idPersona;
		this.idCurso = idCurso;
		this.valorTotal = valorTotal;
		this.valorPagado = valorPagado;
	}

	
            
}
