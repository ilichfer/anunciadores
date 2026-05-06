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
            "where pm.idMinisterio = :idMinisterio " +
            " and p.estado = true " +
            " order by p.nombre asc ")
    public List<Persona> findPersonasByIdMinisterio(@Param("idMinisterio") int idMinisterio);

    @Modifying
    @Query("select p from Persona p  WHERE p.id not in("+
            " select pm.idPersona  from PersonaMinisterio pm"+
            " join Ministerio m on pm.idMinisterio  = m.id"+
            " where m.id =:idMinisterio)" +
            " and  p.estado = true" +
            " order by p.nombre asc ")
    public List<Persona> findPeopleWithOutMinisterio(@Param("idMinisterio") int idMinisterio);

    @Query("select  m.nombre, pm .nombrePosicion from Ministerio m " +
            "join PosicionesMinisterio pm on m.id = pm.idMinisterio " +
            "where m.id = :idMinisterio " +
            "and pm.id = :posiciones ")
    public Object findMnisteryAndPosition(@Param("idMinisterio") int idMinisterio, @Param("posiciones") int posiciones);

    @Query("select m.nombre, pm from Ministerio m " +
          " join PosicionesMinisterio pm on m.id = pm.idMinisterio ")
    public List<Object> findMnisteryWithPositionS();

    @Query("select m.nombre from Ministerio m  " +
            "join PersonaMinisterio pm on m.id = pm.idMinisterio " +
            "where pm.idPersona =:idPersona")
    List<String> findNameMinisterio(@Param("idPersona") int idPersona);



}
