package com.anunciadores.dto;

import java.util.List;

public class Ministry {
    private String id;
    private String name;
    private List<PositionDto> positions;

    // Constructor vacío (necesario para frameworks como Jackson)
    public Ministry() {}

    // Constructor completo
    public Ministry(String id, String name, List<PositionDto> positions) {
        this.id = id;
        this.name = name;
        this.positions = positions;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PositionDto> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionDto> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Ministry{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", positions=" + positions +
                '}';
    }
}

