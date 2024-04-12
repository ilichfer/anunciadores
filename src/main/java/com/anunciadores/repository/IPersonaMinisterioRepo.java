package com.anunciadores.repository;

import com.anunciadores.model.PersonaMinisterio;
import com.anunciadores.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IPersonaMinisterioRepo extends JpaRepository<PersonaMinisterio, Integer>{

}
