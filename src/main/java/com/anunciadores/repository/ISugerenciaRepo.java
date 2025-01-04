package com.anunciadores.repository;

import com.anunciadores.model.Actividad;
import com.anunciadores.model.Sugerencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISugerenciaRepo extends JpaRepository<Sugerencia, Integer>{


}
