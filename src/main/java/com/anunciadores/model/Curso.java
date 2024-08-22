package com.anunciadores.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

import javax.persistence.*;

import com.sun.istack.NotNull;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "curso")

public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "idpago")
    private int idPago;

    @Column(name = "nombrecurso")
    private String nombreCurso;
    
    @Column(name = "fechainicio")
    private String fechaInicio;
    
    @Column(name = "fechafin")
    private String fechaFin;
        
    @Column(name = "valortotal")
    private int valorTotal; 
    
    @Column(name = "comentario")
    private Boolean comentario;
    
    @Column(name = "activo")
    private boolean activo;

	@OneToOne
	@JoinColumn(
			name = "profesor",
			referencedColumnName = "id") // "id_other_class" is the foreign key in "permisos_menu" table that references "id" in "param_menu" table
	private Persona profesor;
    
      public Curso() {
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public int getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public boolean isComentario() {
		return comentario;
	}

	public void setComentario(boolean comentario) {
		this.comentario = comentario;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Curso(int id, int idPago, String nombreCurso, String fechaInicio, String fechaFin, int valorTotal,
			boolean comentario, boolean activo) {
		super();
		this.id = id;
		this.idPago = idPago;
		this.nombreCurso = nombreCurso;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.valorTotal = valorTotal;
		this.comentario = comentario;
		this.activo = activo;
	}

	public Persona getProfesor() {
		return profesor;
	}

	public void setProfesor(Persona profesor) {
		this.profesor = profesor;
	}
}
