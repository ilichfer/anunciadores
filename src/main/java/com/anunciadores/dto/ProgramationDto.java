package com.anunciadores.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramationDto {


    private String id;
    private LocalDate date;
    private String time;

    private CordinatorDto coordinator;
    private List<MinistryDto> ministries;

    public ProgramationDto() {
    }

    public ProgramationDto(String id, LocalDate date, String time, CordinatorDto coordinator, List<MinistryDto> ministries) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.coordinator = coordinator;
        this.ministries = ministries;
    }

    public CordinatorDto getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(CordinatorDto coordinator) {
        this.coordinator = coordinator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<MinistryDto> getMinistries() {
        return ministries;
    }

    public void setMinistries(List<MinistryDto> ministries) {
        this.ministries = ministries;
    }
}
