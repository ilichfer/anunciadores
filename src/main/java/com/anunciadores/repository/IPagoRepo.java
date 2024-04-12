package com.anunciadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anunciadores.model.Pago;

public interface IPagoRepo extends JpaRepository<Pago, Integer>{

}
