package com.anunciadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anunciadores.model.Actividad;

@Repository
public interface IActividadRepo extends JpaRepository<Actividad, Integer>{


}
