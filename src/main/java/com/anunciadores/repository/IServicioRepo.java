package com.anunciadores.repository;

import com.anunciadores.model.Ministerio;
import com.anunciadores.model.PosicionesMinisterio;
import com.anunciadores.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IServicioRepo extends JpaRepository<Servicio, Integer>{

    @Modifying
    @Query("select s.fechaServicio, p.nombre, pm.nombrePosicion, m.id, m.nombre from Servicio s " +
            "join Persona p on s.idPersona = p.id " +
            "join PosicionesMinisterio pm on s.idPosicion = pm.id " +
            "join Ministerio m on pm.idMinisterio = m.id " +
            "WHERE s.fechaServicio = :fechaServicio")
    public List<Object> findProgramacionServicio(@Param("fechaServicio") Date fechaServicio);


}
