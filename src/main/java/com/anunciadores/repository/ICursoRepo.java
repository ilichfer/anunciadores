package com.anunciadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anunciadores.model.Curso;

@Repository
public interface ICursoRepo extends JpaRepository<Curso, Integer>{


}
