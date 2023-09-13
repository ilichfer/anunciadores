package com.anunciadores.repository;

import com.anunciadores.model.Ministerio;
import com.anunciadores.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServicioRepo extends JpaRepository<Servicio, Integer>{

}
