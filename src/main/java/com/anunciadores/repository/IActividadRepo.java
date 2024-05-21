package com.anunciadores.repository;

import com.anunciadores.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.anunciadores.model.Actividad;

import java.util.List;

@Repository
public interface IActividadRepo extends JpaRepository<Actividad, Integer>{

    @Query(value = " SELECT ac from Actividad ac " +
            "join InscripcionActividad ia on (ac.id = ia.idActividad) " +
            " WHERE ia.idPersona = ?1")
    List<Actividad> findActividadesByPersona(int idPersona);


}
