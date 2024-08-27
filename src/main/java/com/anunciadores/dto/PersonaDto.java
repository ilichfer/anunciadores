package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.anunciadores.model.PermisosMenu;
import com.anunciadores.model.Rol;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 *
 * @author valbuena
 */


public class PersonaDto implements Serializable {
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
    
    private boolean consolidacion;
    
    private List<Rol> roles;
	private List<PermisosMenu> permisosMenu;


	private Rol rolUnico;
    private boolean admin;
    private boolean user;
    private boolean coordinadorActual;
    private Date fechaCoordinador;
    private boolean validarPago;

      public PersonaDto() {
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


	public PersonaDto(int id, String nombre, String apellido, Integer documento, String telefono, String fechanacimiento, String tipodocumento, String email, String password, boolean consolidacion, List<Rol> roles, List<PermisosMenu> permisosMenu, Rol rolUnico, boolean admin, boolean user, boolean validarPago) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.telefono = telefono;
		this.fechanacimiento = fechanacimiento;
		this.tipodocumento = tipodocumento;
		this.email = email;
		this.password = password;
		this.consolidacion = consolidacion;
		this.roles = roles;
		this.permisosMenu = permisosMenu;
		this.rolUnico = rolUnico;
		this.admin = admin;
		this.user = user;
		this.validarPago = validarPago;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isUser() {
		return user;
	}

	public void setUser(boolean user) {
		this.user = user;
	}

	public boolean isConsolidacion() {
		return consolidacion;
	}

	public void setConsolidacion(boolean consolidacion) {
		this.consolidacion = consolidacion;
	}

	public boolean isValidarPago() {
		return validarPago;
	}

	public void setValidarPago(boolean validarPago) {
		this.validarPago = validarPago;
	}

	public Rol getRolUnico() {
		return rolUnico;
	}

	public void setRolUnico(Rol rolUnico) {
		this.rolUnico = rolUnico;
	}

	public List<PermisosMenu> getPermisosMenu() {
		return permisosMenu;
	}

	public void setPermisosMenu(List<PermisosMenu> permisosMenu) {
		this.permisosMenu = permisosMenu;
	}

	public boolean isCoordinadorActual() {
		return coordinadorActual;
	}

	public void setCoordinadorActual(boolean coordinadorActual) {
		this.coordinadorActual = coordinadorActual;
	}

	public Date getFechaCoordinador() {
		return fechaCoordinador;
	}

	public void setFechaCoordinador(Date fechaCoordinador) {
		this.fechaCoordinador = fechaCoordinador;
	}
}
