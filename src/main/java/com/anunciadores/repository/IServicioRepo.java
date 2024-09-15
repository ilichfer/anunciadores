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
import java.util.Optional;

public interface IServicioRepo extends JpaRepository<Servicio, Integer>{

    @Modifying
    @Query("select s.fechaServicio, concat(p.nombre,' ',p.apellido), pm.nombrePosicion, m.id, m.nombre from Servicio s " +
            "join Persona p on s.idPersona = p.id " +
            "join PosicionesMinisterio pm on s.idPosicion = pm.id " +
            "join Ministerio m on pm.idMinisterio = m.id " +
            "WHERE s.fechaServicio = :fechaServicio "
            +" order by m.id asc")
    public List<Object> findProgramacionServicio(@Param("fechaServicio") Date fechaServicio);

    @Query("select s from Servicio s " +
            "where s.idPersona = :idPersona " +
            "and s.fechaServicio = :fechaServicio")
    public Optional<Servicio> findProgramacionServidor(@Param("idPersona") int idPersona, @Param("fechaServicio") Date fechaServicio);

    @Query("select s from Servicio s " +
            "join Persona p on (s.idPersona = p.id) " +
            "join PosicionesMinisterio pm on(s.idPosicion = pm.id) " +
            "where p.nombre = :persona " +
            "and s.fechaServicio = :fechaServicio " +
            "and pm.nombrePosicion =:nombrePosicion")
    public Optional<Servicio> findProgramacionServidorAndMinisterio(@Param("persona") String persona, @Param("fechaServicio") Date fechaServicio,@Param("nombrePosicion") String nombrePosicion);


    @Query("select s from Servicio s " +
            "where s.idPersona = :idPersona " +
            "and s.fechaServicio = :fechaServicio")
    public Optional<Servicio> findProgramacionPosition(@Param("idPersona") int idPersona, @Param("fechaServicio") Date fechaServicio);

    @Query("select p.id  , p.nombre,pm.id , pm.nombrePosicion from Servicio s " +
            "            join Persona p on s.idPersona = p.id " +
            "            join PosicionesMinisterio pm on s.idPosicion = pm.id " +
            "            join Ministerio m on pm.idMinisterio = m.id " +
            "            WHERE s.fechaServicio = :fechaServicio" +
            "            and m.id = :idMinisterio")
    public List<Object> findProgramacionByDateAndMinistery(@Param("fechaServicio") Date fechaServicio, @Param("idMinisterio") int idMinisterio);

    @Query("select s.fechaServicio, concat(p.nombre,' ',p.apellido), pm.nombrePosicion, m.id, m.nombre from Servicio s " +
            "            join Persona p on s.idPersona = p.id " +
            "            join PosicionesMinisterio pm on s.idPosicion = pm.id " +
            "            join Ministerio m on pm.idMinisterio = m.id " +
            "            WHERE s.fechaServicio = :fechaServicio" +
            "            and m.id = :idMinisterio")
    public List<Object> findProgramacionByDateAndidMinistery(@Param("fechaServicio") Date fechaServicio, @Param("idMinisterio") int idMinisterio);



    @Modifying
    public void deleteByFechaServicioAndIdMinisterio(@Param("fechaServicio") Date fechaServicio,int idMinisterio);

    public List<Servicio> findByFechaServicioAndIdMinisterio(@Param("fechaServicio") Date fechaServicio,int idMinisterio);

    @Query("select s from Servicio s " +
            "WHERE s.fechaServicio  BETWEEN :fechaInicial AND :fechaFinal " +
            "and s.idPersona =:idPersona " +
            "order by s.fechaServicio asc")
    public List<Servicio> BuscarServicioMes(@Param("fechaInicial")Date fechaInicial, @Param("fechaFinal")Date fechaFinal,@Param("idPersona")int idPersona);

}
