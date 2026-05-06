package com.anunciadores.dto;

import java.time.LocalDate;

public class ServiceDTO     {
    private LocalDate fechaServicio;
    private String idPersona;
    private String idPosicion;
    private String idMinisterio;

    // Constructores
    public ServiceDTO() {}

    public ServiceDTO(LocalDate fechaServicio, String idPersona, String idPosicion, String idMinisterio) {
        this.fechaServicio = fechaServicio;
        this.idPersona = idPersona;
        this.idPosicion = idPosicion;
        this.idMinisterio = idMinisterio;
    }

    // Getters y Setters
    public LocalDate getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(LocalDate fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getIdPosicion() {
        return idPosicion;
    }

    public void setIdPosicion(String idPosicion) {
        this.idPosicion = idPosicion;
    }

    public String getIdMinisterio() {
        return idMinisterio;
    }

    public void setIdMinisterio(String idMinisterio) {
        this.idMinisterio = idMinisterio;
    }
}
