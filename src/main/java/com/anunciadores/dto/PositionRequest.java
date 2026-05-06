package com.anunciadores.dto;

public class PositionRequest {
    private Long idMinisterio;
    private String name;

    // Getters y Setters
    public Long getIdMinisterio() { return idMinisterio; }
    public void setIdMinisterio(Long idMinisterio) { this.idMinisterio = idMinisterio; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
