package com.anunciadores.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "permisos_menu")

public class PermisosMenu implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "id_persona")
	private int idPersona;

	@Column(name = "nombre_boton_menu")
	public String nombreBotonMenu;

	@Column(name = "estado")
	public String estado;

	@Column(name = "id_menu")
	public int idMenu;

	@OneToOne
	@JoinColumn(
			name = "id",
			referencedColumnName = "id") // "id_menu" is the foreign key in "permisos_menu" table that references "id" in "param_menu" table
	private ParamMenu menu;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombreBotonMenu() {
		return nombreBotonMenu;
	}

	public void setNombreBotonMenu(String nombreBotonMenu) {
		this.nombreBotonMenu = nombreBotonMenu;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ParamMenu getMenu() {
		return menu;
	}

	public void setMenu(ParamMenu menu) {
		this.menu = menu;
	}

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}
}
