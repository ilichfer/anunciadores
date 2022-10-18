package com.anunciadores.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.NotNull;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "pago")

public class Pago implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_curso")
	@SequenceGenerator(name = "sec_curso", sequenceName = "sec_curso", allocationSize = 1)
    
    @Column(name = "id")
    private int id;
    
    @Column(name = "idpersona")
    private int idpersona;

    @Column(name = "fechaPago")
    private Date fechaPago;

    @Column(name = "valor")
    private int valor;
    
    @Column(name = "id_curso")
    private int id_curso;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdpersona() {
		return idpersona;
	}

	public void setIdpersona(int idpersona) {
		this.idpersona = idpersona;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getId_curso() {
		return id_curso;
	}

	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}

	public Pago(int id, int idpersona, Date fechaPago, int valor, int id_curso) {
		super();
		this.id = id;
		this.idpersona = idpersona;
		this.fechaPago = fechaPago;
		this.valor = valor;
		this.id_curso = id_curso;
	}

	public Pago() {
		super();
	}
    
	
	
}
