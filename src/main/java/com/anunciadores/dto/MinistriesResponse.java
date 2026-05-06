package com.anunciadores.dto;

import java.util.List;

public class MinistriesResponse {
    private List<Ministry> ministries;

    public MinistriesResponse() {}

    public MinistriesResponse(List<Ministry> ministries) {
        this.ministries = ministries;
    }

    public List<Ministry> getMinistries() {
        return ministries;
    }

    public void setMinistries(List<Ministry> ministries) {
        this.ministries = ministries;
    }
}

