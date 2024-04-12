package com.anunciadores.repository;

import com.anunciadores.model.Ministerio;
import com.anunciadores.model.Persona;
import com.anunciadores.model.Tdc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IMinisterioRepo extends JpaRepository<Ministerio, Integer>{

    @Modifying
    @Query("select p from Persona p " +
            "join PersonaMinisterio pm on p.id = pm.idPersona " +
            "where pm.idMinisterio = :idMinisterio")
    public List<Persona> findPersonasByIdMinisterio(@Param("idMinisterio") int idMinisterio);

    @Modifying
    @Query("select p from Persona p  WHERE p.id not in("+
            " select pm.idPersona  from PersonaMinisterio pm"+
            " join Ministerio m on pm.idMinisterio  = m.id"+
            " where m.id =:idMinisterio)")
    public List<Persona> findPeopleWithOutMinisterio(@Param("idMinisterio") int idMinisterio);
}
