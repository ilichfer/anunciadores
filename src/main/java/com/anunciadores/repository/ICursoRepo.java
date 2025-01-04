package com.anunciadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.anunciadores.model.Curso;

import java.util.List;

@Repository
public interface ICursoRepo extends JpaRepository<Curso, Integer>{

    List<Curso> findTopByOrderByIdDesc();
    List<Curso> findByActivo(boolean activo);
    @Query(nativeQuery = true,value = "select * from curso c where c.id not in " +
            "(select ins.id_curso  from persona p " +
            "join inscripciones ins on p.id = ins.id_persona " +
            "WHERE p.id  = ?1 )")
    List<Curso> findByActivos(int idPersona);
    @Query(nativeQuery = true,value = "SELECT c.* FROM persona p  "
            + " join inscripciones ins on p.id = ins.id_persona"
            + " join curso c on ins.id_curso = c.id "
            + " where p.id= ?1 " )
    List<Curso> cursosByIdPersona(int idPersona);


    @Query(nativeQuery = true,value = "SELECT c.* FROM persona p " +
            " join curso c on p.id  = c.profesor " +
            " where p.id= ?1 " )
    List<Curso> cursosByIdProfesor(int idPersona);



}
