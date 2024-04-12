package com.anunciadores.repository;

import com.anunciadores.model.Persona;
import com.anunciadores.model.inscripcionConsolidacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IInscripcionConsolidacionRepo extends JpaRepository<inscripcionConsolidacion, Integer>{

    @Query(value = " SELECT p FROM Persona p " +
            "join inscripcionConsolidacion ic on p.id = ic.idPersonaConsolidar" +
            " WHERE ic.idPadreEspiritual = ?1")
    List<Persona> findConsolidacionesByServidor(int idPadreEspiritual);
    List<inscripcionConsolidacion> findByIdPadreEspiritual(int idServidor);
}
