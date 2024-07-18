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
@Table(name = "param_menu")

public class ParamMenu implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre_boton_menu")
	public String nombreBotonMenu;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMenu")
	private List<ParamSubMenu> subMenu;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreBotonMenu() {
		return nombreBotonMenu;
	}

	public void setNombreBotonMenu(String nombreBotonMenu) {
		this.nombreBotonMenu = nombreBotonMenu;
	}

	public List<ParamSubMenu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<ParamSubMenu> subMenu) {
		this.subMenu = subMenu;
	}
}
