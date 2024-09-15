package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.anunciadores.model.Rol;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author valbuena
 */


public class PersonaConsolidacionDto implements Serializable {
    private static final long serialVersionUID = 1L;

	private int id;

	private String nombre;

	private String apellido;

	private Integer documento;

	private String telefono;

	private String fechanacimiento;

	private String tipodocumento;

	private String email;

	private String password;
	private String genero;
	private String estadoCivil;

	private String paisNacimiento;

	private String ciudad;
	private Boolean discapacidad;
	private String descDiscapacidad;
	private Boolean perteneceMinoria;
	private String descMinoria;
	private String direccion;
	private String ciudadDeptoDireccion;
	private String celular;
	private String ocupacion;
	private String escolaridad;
	private String fechaConvercionCristo;
	private String fechaLlegadaAdc;
	private String fechaBautizo;
	private String fechaBautizoEspiritu;
	private Boolean consolidacion;
	private List<AsignacionConsolidacionDto> asignacion;

	private Integer tamanoLista;

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

	public String getDescDiscapacidad() {
		return descDiscapacidad;
	}

	public void setDescDiscapacidad(String descDiscapacidad) {
		this.descDiscapacidad = descDiscapacidad;
	}

	public Boolean getPerteneceMinoria() {
		return perteneceMinoria;
	}

	public void setPerteneceMinoria(Boolean perteneceMinoria) {
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

	public List<AsignacionConsolidacionDto> getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(List<AsignacionConsolidacionDto> asignacion) {
		this.asignacion = asignacion;
	}

	public Integer getTamanoLista() {
		return tamanoLista;
	}

	public void setTamanoLista(Integer tamanoLista) {
		this.tamanoLista = tamanoLista;
	}
}
