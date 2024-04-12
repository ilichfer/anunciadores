package com.anunciadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.anunciadores.model.Persona;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPersonaRepo extends JpaRepository<Persona, Integer>{

    Persona findByEmail(String email);
    Persona findByNombre(String nombre);

   // @Query(nativeQuery = false,value = "select p from Persona p "
    //        + "where p.documento =:doc")
   // Persona findByDocumento(@Param("doc")Integer doc);

    Persona findByDocumento(Integer doc);

@Query(nativeQuery = false,value = "select distinct p from Persona p "
        + "join Inscripciones i on p.id  = i.idPersona "
        + "join Curso c on i.idCurso = c.id "
        + "where c.id =:idcurso")
List<Persona> findPersonaByCurso(@Param("idcurso") Integer idcurso);
@Query(nativeQuery = true,value = "select p.* from persona p "
        + "where id not in ("
        + "select i.id_persona from inscripciones i " +
        "join curso c on i.id_curso = c.id " +
        "where i.id_curso =:idcurso )"
        + "and p.id in( select pr.id_persona from persona_rol pr)")
List<Persona> findPersonaSinCurso(@Param("idcurso") Integer idcurso);

    @Modifying
@Query(nativeQuery = false,value = "delete from Inscripciones i where i.idPersona =:idPersona " +
        " and i.idCurso =:idCurso")
void deletePersonaConCurso(@Param("idPersona") int idPersona,@Param("idCurso")  int idCurso);
    @Modifying
@Query(nativeQuery = false,value = "delete from PersonaMinisterio pm where pm.idPersona =:idPersona  and pm.idMinisterio =:idMinisterio")
void deletePersonaMinisterio(@Param("idPersona") int idPersona,@Param("idMinisterio")  int idMinisterio);
@Query(nativeQuery = true,value = "select p.* from persona p "
        + "WHERE p.id  in( "
        + "select pr.id_persona from persona_rol pr) ")
List<Persona> findUsuarios();
@Query(nativeQuery = true,value = "select p.* from persona p " +
        "WHERE p.id not in(select pr.id_persona from persona_rol pr) " +
        "and  p.id not in(select c.id_persona_consolidar from inscripcion_consolidacion c) ")
List<Persona> listarConsolidacion();
@Query(nativeQuery = true,value = "select p.* from persona p "
        + "where id not in ("
        + "select i.id_persona from inscripcion_actividades i " +
        " join actividades a on i.id_actividad = a.id" +
        " where i.id_actividad =:idActividad )")
List<Persona> buscarPersonaSinActividad(@Param("idActividad")Integer idActividad);

    @Query(nativeQuery = true,value = "select distinct p.* from persona p "
            + "join inscripcion_actividades i on p.id  = i.id_persona "
            + "join actividades a on i.id_actividad = a.id "
            + "where a.id =:idActividad ")
    List<Persona> buscarPersonaByActividad(@Param("idActividad")Integer idActividad);
    @Query(nativeQuery = true,value = "select p.* from persona p "
            + "WHERE p.id not in( "
            + "select pr.id_persona from persona_rol pr) ")
    List<Persona> buscarAsistentes();

    @Query(nativeQuery = true,value = "select p.* from persona p "
            + "where id not in ("
            + "select pm.id_persona from mesa  m " +
            " join persona_mesa pm on m.id = pm.id_mesa" +
            " where m.id =:idMesa)")
    public List<Persona> buscarPersonaSinMesas(@Param("idMesa")Integer idMesa);
}
