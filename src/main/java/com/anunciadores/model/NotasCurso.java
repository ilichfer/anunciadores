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
@Table(name = "notas_curso")

public class NotasCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
	@Basic(optional = false)
	@NotNull

    @Column(name = "id")
    private double id;

	@OneToOne
	@JoinColumn(
			name = "id_curso",
			referencedColumnName = "id")
	private Curso curso;

	@OneToOne
	@JoinColumn(
			name = "id_persona",
			referencedColumnName = "id")
    private Persona persona;
	@Column(name = "nota_maestro")
    private double notaMaestro;
	@Column(name = "nota_asistencia")
    private double notaAsistencia;
	@Column(name = "nota_practica")
    private double notaPractica;
	@Column(name = "nota_examen_final")
	private double notaExamenFinal;

	@Column(name = "nota_final")
	private double notaFinal;

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public double getNotaMaestro() {
		return notaMaestro;
	}

	public void setNotaMaestro(double notaMaestro) {
		this.notaMaestro = notaMaestro;
	}

	public double getNotaAsistencia() {
		return notaAsistencia;
	}

	public void setNotaAsistencia(double notaAsistencia) {
		this.notaAsistencia = notaAsistencia;
	}

	public double getNotaPractica() {
		return notaPractica;
	}

	public void setNotaPractica(double notaPractica) {
		this.notaPractica = notaPractica;
	}

	public double getNotaExamenFinal() {
		return notaExamenFinal;
	}

	public void setNotaExamenFinal(double notaExamenFinal) {
		this.notaExamenFinal = notaExamenFinal;
	}

	public double getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(double notaFinal) {
		this.notaFinal = notaFinal;
	}
}
