package com.anunciadores.repository;

import com.anunciadores.model.Consolidacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsolidacionRepo extends JpaRepository<Consolidacion, Integer>{

//    @Query(nativeQuery = false,value = " SELECT c FROM Persona c WHERE Email = ?1")
//    Persona findPersonaByEmail(String doc);
    Consolidacion findByIdPersona(int idPersona);
}
