package com.anunciadores.repository;

import com.anunciadores.model.Pago;
import com.anunciadores.model.Tdc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ITdcRepo extends JpaRepository<Tdc, Integer>{

    @Modifying
    @Query("select u from Tdc u  where u.fechaCreacion = :date")
    public List<Tdc> findAllByDate(@Param("date") Date date);

    @Modifying
    @Query("select u from Tdc u  where u.fechaCreacion = :date and u.idPersona = :idPersona")
    public List<Tdc> findAllByDateAndPersona(@Param("date") Date date, @Param("idPersona") int idPersona);

    @Modifying
    @Query("select p.nombre , COUNT(t.idPersona), t.idPersona from Tdc t " +
            "join Persona p on t.idPersona  = p.id " +
            "where t.fechaCreacion BETWEEN :dateStart and :dateEnd " +
            "GROUP BY t.idPersona")
    public List<Object> findAllBetweenDates(@Param("dateStart") Date dateStart,@Param("dateEnd") Date dateEnd);

    @Modifying
    @Query("select t from Tdc t " +
            "join Persona p on t.idPersona  = p.id " +
            "where t.fechaCreacion BETWEEN :dateStart and :dateEnd " +
            "and p.id = :idPersona order by t.fechaCreacion desc")
    public List<Tdc> findAllBetweenDatesByPersona(@Param("dateStart") Date dateStart,@Param("dateEnd") Date dateEnd,@Param("idPersona") int idPersona);

}
