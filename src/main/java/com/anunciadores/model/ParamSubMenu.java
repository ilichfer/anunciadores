package com.anunciadores.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.persistence.*;
import java.io.Serializable;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "param_submenu")

public class ParamSubMenu implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "idMenu")
	private int idMenu;

	@Column(name = "etiqueta")
	public String etiqueta;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

}
