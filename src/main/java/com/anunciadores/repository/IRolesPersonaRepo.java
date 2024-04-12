package com.anunciadores.repository;

import com.anunciadores.model.Tdc;
import org.springframework.data.jpa.repository.JpaRepository;

import com.anunciadores.model.RolPersona;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IRolesPersonaRepo extends JpaRepository<RolPersona, Integer>{

    @Modifying
    @Query("select u from RolPersona u  where u.idPersona = :idPersona")
    public List<RolPersona> findRolByidPersona(@Param("idPersona") int idPersona);

}
