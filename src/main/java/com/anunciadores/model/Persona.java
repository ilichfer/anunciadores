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

/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "persona")

public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "documento")
    private Integer documento;
    
    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "fechanacimiento")
    private String fechanacimiento;
    
    @Column(name = "tipodocumento")
    private String tipodocumento;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
	@Column(name = "genero")
    private String genero;
	@Column(name = "estado_civil")
    private String estadoCivil;

	@Column(name = "pais_nacimiento")
	private String paisNacimiento;

	@Column(name = "ciudad")
	private String ciudad;
	@Column(name = "discapacidad")
	private Boolean discapacidad;
	@Column(name = "descripcion_discapacidad")
	private String descDiscapacidad;
	@Column(name = "pertenece_minoria")
	private Boolean perteneceMinoria;
	@Column(name = "descripcion_minoria")
	private String descMinoria;
	@Column(name = "direccion")
	private String direccion;
	@Column(name = "ciudad_depto_direccion")
	private String ciudadDeptoDireccion;
	@Column(name = "celular")
	private String celular;
	@Column(name = "ocupacion")
	private String ocupacion;
	@Column(name = "escolaridad")
	private String escolaridad;
	@Column(name = "fecha_convercion_cristo")
	private String fechaConvercionCristo;
	@Column(name = "fecha_llegada_adc")
	private String fechaLlegadaAdc;
	@Column(name = "fecha_bautizo")
	private String fechaBautizo;
	@Column(name = "fecha_bautizo_espiritu")
	private String fechaBautizoEspiritu;

	@Column(name = "consolidacion")
	private Boolean consolidacion;

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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getPaisNacimiento() {
		return paisNacimiento;
	}

	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Boolean getDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(Boolean discapacidad) {
		this.discapacidad = discapacidad;
	}

	public Boolean getPerteneceMinoria() {
		return perteneceMinoria;
	}

	public void setPerteneceMinoria(Boolean perteneceMinoria) {
		this.perteneceMinoria = perteneceMinoria;
	}

	public boolean isDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(boolean discapacidad) {
		this.discapacidad = discapacidad;
	}

	public String getDescDiscapacidad() {
		return descDiscapacidad;
	}

	public void setDescDiscapacidad(String descDiscapacidad) {
		this.descDiscapacidad = descDiscapacidad;
	}

	public boolean isPerteneceMinoria() {
		return perteneceMinoria;
	}

	public void setPerteneceMinoria(boolean perteneceMinoria) {
		this.perteneceMinoria = perteneceMinoria;
	}

	public String getDescMinoria() {
		return descMinoria;
	}

	public void setDescMinoria(String descMinoria) {
		this.descMinoria = descMinoria;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudadDeptoDireccion() {
		return ciudadDeptoDireccion;
	}

	public void setCiudadDeptoDireccion(String ciudadDeptoDireccion) {
		this.ciudadDeptoDireccion = ciudadDeptoDireccion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getEscolaridad() {
		return escolaridad;
	}

	public void setEscolaridad(String escolaridad) {
		this.escolaridad = escolaridad;
	}

	public String getFechaConvercionCristo() {
		return fechaConvercionCristo;
	}

	public void setFechaConvercionCristo(String fechaConvercionCristo) {
		this.fechaConvercionCristo = fechaConvercionCristo;
	}

	public String getFechaLlegadaAdc() {
		return fechaLlegadaAdc;
	}

	public void setFechaLlegadaAdc(String fechaLlegadaAdc) {
		this.fechaLlegadaAdc = fechaLlegadaAdc;
	}

	public String getFechaBautizo() {
		return fechaBautizo;
	}

	public void setFechaBautizo(String fechaBautizo) {
		this.fechaBautizo = fechaBautizo;
	}

	public String getFechaBautizoEspiritu() {
		return fechaBautizoEspiritu;
	}

	public void setFechaBautizoEspiritu(String fechaBautizoEspiritu) {
		this.fechaBautizoEspiritu = fechaBautizoEspiritu;
	}

	public Boolean getConsolidacion() {
		return consolidacion;
	}

	public void setConsolidacion(Boolean consolidacion) {
		this.consolidacion = consolidacion;
	}
}
