package com.anunciadores.dto;
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//

import java.io.Serializable;

/**
 *
 * @author valbuena
 */

public class Clientes implements Serializable {

    private String tipoDocumento;

    private int Documento;

    private String primerNombre;

    private String segundoNombre;
 
    private String primerApellido;

    private String segundoApellido;

    private String celular;

    private String direccion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation

    private String email;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientes")
//    private Collection<Vehiculos> vehiculosCollection;

    public Clientes() {
    }

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public Collection<Vehiculos> getVehiculosCollection() {
//		return vehiculosCollection;
//	}
//
//	public void setVehiculosCollection(Collection<Vehiculos> vehiculosCollection) {
//		this.vehiculosCollection = vehiculosCollection;
//	}


	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getDocumento() {
		return Documento;
	}

	public void setDocumento(int documento) {
		Documento = documento;
	}
    
    

    
}
