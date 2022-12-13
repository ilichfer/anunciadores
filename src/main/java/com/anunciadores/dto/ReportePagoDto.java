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

public class ReportePagoDto {

	private int id;
	private String nombreCurso;
	private String persona;
	private int valorTotal;
	private int pago;
	private int deuda;

	public int getIdCurso() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public int getPago() {
		return pago;
	}

	public void setPago(int pago) {
		this.pago = pago;
	}

	public int getDeuda() {
		return deuda;
	}

	public void setDeuda(int deuda) {
		this.deuda = deuda;
	}

	public ReportePagoDto(int id, String nombreCurso, String persona, int valorTotal, int pago, int deuda) {
		super();
		this.id = id;
		this.nombreCurso = nombreCurso;
		this.persona = persona;
		this.valorTotal = valorTotal;
		this.pago = pago;
		this.deuda = deuda;
	}

	public ReportePagoDto() {
		super();
	}

	
}
