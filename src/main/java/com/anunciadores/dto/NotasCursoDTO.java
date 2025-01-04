package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.anunciadores.model.Curso;
import com.anunciadores.model.Persona;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;


/**
 *
 * @author valbuena
 */



public class NotasCursoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private double id;
	private Curso curso;
    private double notaMaestro;
    private double notaAsistencia;
    private double notaPractica;
	private double notaExamenFinal;

	private double notaFinal;
	private int colorCelda;

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

	public int getColorCelda() {
		return colorCelda;
	}

	public void setColorCelda(int colorCelda) {
		this.colorCelda = colorCelda;
	}
}
