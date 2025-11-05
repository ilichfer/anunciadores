package com.anunciadores.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "tdc")

public class TimeSlot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate date;
	private LocalTime startTime;
	private boolean reserved;
	private boolean diaSiguiente;

	@ManyToOne
	private Persona reservedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public Persona getReservedBy() {
		return reservedBy;
	}

	public void setReservedBy(Persona reservedBy) {
		this.reservedBy = reservedBy;
	}

	public boolean isDiaSiguiente() {
		return diaSiguiente;
	}

	public void setDiaSiguiente(boolean diaSiguiente) {
		this.diaSiguiente = diaSiguiente;
	}
}

