package com.anunciadores.dto;

import java.util.List;
import java.util.Map;

public class MinistryDto {
    private Integer id;
    private String name;
    private List<MinistryMember>  positions;

    // Constructor vacío (necesario para frameworks como Jackson)
    public MinistryDto() {}

    // Constructor completo
    public MinistryDto(Integer id, String name, List<MinistryMember> positions) {
        this.id = id;
        this.name = name;
        this.positions = positions;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MinistryMember> getPositions() {
        return positions;
    }

    public void setPositions(List<MinistryMember> positions) {
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

