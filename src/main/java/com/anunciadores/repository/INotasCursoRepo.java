package com.anunciadores.repository;

import com.anunciadores.model.Curso;
import com.anunciadores.model.NotasCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface INotasCursoRepo extends JpaRepository<NotasCurso, Integer>{


    @Query(nativeQuery = true,value = "select nc.* from curso c "
            +"join notas_curso nc on c.id  = nc.id_curso "
            +"where nc.id_curso = :idCurso " +
            "and nc.id_persona = :idPersona ")
    NotasCurso findNotasByCurso(int idCurso,int idPersona);
    @Query(nativeQuery = true,value = "select nc.* from notas_curso nc "
            +"where nc.id_persona = :idPersona ")
    Optional<List<NotasCurso> >findHistoricoNotas(int idPersona);




}
