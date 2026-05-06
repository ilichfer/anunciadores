package com.anunciadores.dto;

import com.anunciadores.model.Persona;

import java.util.List;

public class UserResponseDto{

    private Long   id;
    private String name;
    private String role;
    private String email;
    private String phone;
    private String avatar;
    private List<String> ministry; // array — puede pertenecer a varios ministerios

    public UserResponseDto(Persona p) {
        this.id     = p.getId().longValue();
        this.name   = (p.getNombre() + " " + p.getApellido()).trim();
        this.role   = "ADMINISTRADOR"; // sin campo rol en la entidad por ahora
        this.email  = p.getEmail();
        this.phone  = p.getCelular() != null ? p.getCelular() : p.getTelefono();
        this.avatar = null;            // sin campo avatar en la entidad por ahora
        this.ministry = List.of();     // sin ministerios por ahora — ampliar después
}

public Long         getId()      { return id; }
public String       getName()    { return name; }
public String       getRole()    { return role; }
public String       getEmail()   { return email; }
public String       getPhone()   { return phone; }
public String       getAvatar()  { return avatar; }
public List<String> getMinistry(){ return ministry; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setMinistry(List<String> ministry) {
        this.ministry = ministry;
    }
}

