package com.anunciadores.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioReactDto {

    private List<Map<String, List<MinistryMember>> > ministries ;

    public ServicioReactDto() {}

    public ServicioReactDto(List<Map<String, List<MinistryMember>>> ministries) {
        this.ministries = ministries;
    }

    public List<Map<String, List<MinistryMember>>>
    getMinistries() {
        return ministries;
    }

    public void setMinistries(List<Map<String, List<MinistryMember>>> ministries) {
        this.ministries = ministries;
    }
}
