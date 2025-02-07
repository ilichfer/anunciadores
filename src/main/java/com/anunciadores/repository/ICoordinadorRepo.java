package com.anunciadores.repository;

import com.anunciadores.model.Coordinador;
import com.anunciadores.model.Persona;
import com.anunciadores.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ICoordinadorRepo extends JpaRepository<Coordinador, Integer> {


    public Coordinador findByFechaServicio(@Param("fechaServicio") Date fechaServicio);

    @Query(value = "select c from Coordinador c " +
            "where c.persona.id = :idPersona " +
            " and c.fechaServicio = :fechaServicio ")
    public Optional<Coordinador> findByIdPersonaAndIdPersona(@Param("fechaServicio") Date fechaServicio, @Param("idPersona") int idPersona);

    @Query("select c from Coordinador c " +
            "WHERE c.fechaServicio  BETWEEN :fechaInicial AND :fechaFinal " +
            "and c.persona.id =:idPersona " +
            "order by c.fechaServicio asc")
    public List<Coordinador> buscarServicioCoordinadorMes(@Param("fechaInicial") Date fechaInicial, @Param("fechaFinal") Date fechaFinal, @Param("idPersona") int idPersona);

    @Query("select c from Coordinador c " +
            "WHERE c.fechaServicio  BETWEEN :fechaInicial AND :fechaFinal " +
            "order by c.fechaServicio asc")
    List <Coordinador> buscarHisInformes(@Param("fechaInicial")Date fechaInicial, @Param("fechaFinal")Date fechaFinal);

}
