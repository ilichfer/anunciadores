package com.anunciadores.repository;

import com.anunciadores.model.Rol;
import com.anunciadores.model.VersiculoSemanal;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface IVersiculoRepo extends JpaRepository<VersiculoSemanal, Integer>{

    public Optional<VersiculoSemanal> findByFechaFin(Date fecha);

    public Optional<VersiculoSemanal> findByFechaFinBetween(Date fechaIni,Date fechaFin);;


}
