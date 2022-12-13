package com.anunciadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anunciadores.model.InscripcionActividad;
import com.anunciadores.model.Rol;

public interface IRolesRepo extends JpaRepository<Rol, Integer>{


}
