package com.anunciadores.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "persona")

public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
       
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_persona")
	@SequenceGenerator(name = "sec_persona", sequenceName = "sec_persona", allocationSize = 1)
    
	@Id
    @Column(name = "id")
    private int id;
    
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "documento")
    private Integer documento;
    
    @Column(name = "telefono")
    private Integer telefono;
    
    @Column(name = "fechanacimiento")
    private String fechanacimiento;
    
    @Column(name = "tipodocumento")
    private String tipodocumento;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;

      public Persona() {
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(String fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Persona(int id, String nombre, String apellido, int documento, int telefono, String fechanacimiento,
			String tipodocumento, String email, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.telefono = telefono;
		this.fechanacimiento = fechanacimiento;
		this.tipodocumento = tipodocumento;
		this.email = email;
		this.password = password;
	}
	
	
}
