package com.anunciadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.anunciadores.model.Consolidacion;
import com.anunciadores.model.Persona;

public interface IConsolidacionRepo extends JpaRepository<Consolidacion, Integer>{

//    @Query(nativeQuery = false,value = " SELECT c FROM Persona c WHERE Email = ?1")
//    Persona findPersonaByEmail(String doc);
}
