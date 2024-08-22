package com.anunciadores.repository;

import com.anunciadores.model.Coordinador;
import com.anunciadores.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ICoordinadorRepo extends JpaRepository<Coordinador, Integer>{


    public Coordinador findByFechaServicio(@Param("fechaServicio") Date fechaServicio);
}