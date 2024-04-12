package com.anunciadores.repository;

import com.anunciadores.model.EstudioPersona;
import com.anunciadores.model.Persona;
import com.anunciadores.model.RolPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEstudiosPersonaRepo extends JpaRepository<EstudioPersona, Integer>{


    List<EstudioPersona> findByIdPersona(int idPersona);

}
