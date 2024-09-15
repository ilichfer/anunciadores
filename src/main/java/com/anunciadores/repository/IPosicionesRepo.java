package com.anunciadores.repository;

import com.anunciadores.model.Ministerio;
import com.anunciadores.model.PosicionesMinisterio;
import com.anunciadores.model.Tdc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IPosicionesRepo extends JpaRepository<PosicionesMinisterio, Integer>{

    @Modifying
    @Query("select p from PosicionesMinisterio p  where p.idMinisterio = :idMinisterio")
    public List<PosicionesMinisterio> findAllByIdMinisterio(@Param("idMinisterio") int idMinisterio);

    @Modifying
    @Query("select p from PosicionesMinisterio p  where p.nombrePosicion = :nombrePosicion" +
            " and p.idMinisterio =:idMinisterio")
    public List<PosicionesMinisterio> findMinisterioByName(@Param("nombrePosicion") String nombrePosicion, @Param("idMinisterio") int idMinisterio);


}
