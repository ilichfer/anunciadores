package com.anunciadores.dto;

public class ChatDTO {
    public Long id;
    public String first_name;
    public String last_name;
    public String type;

    public ChatDTO() {
    }

    public ChatDTO(Long id, String first_name, String last_name, String type) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
