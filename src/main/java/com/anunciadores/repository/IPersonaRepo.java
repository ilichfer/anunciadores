package  com.anunciadores.repository;

import com.anunciadores.model.Persona;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPersonaRepo extends JpaRepository<Persona, Integer> {
  Persona findByEmail(String paramString);
  
  Persona findByNombre(String paramString);
  
  @Query(value = "SELECT * FROM persona WHERE id_telegram = :idTelegram LIMIT 1", nativeQuery = true)
  Optional<Persona> findFirstByIdTelegram(@Param("idTelegram") String paramString);
  
  @Query(nativeQuery = true, value = "select p.* from persona p WHERE p.celular = :celular  and p.id_telegram is null ")
  Optional<List<Persona>> findByCelular(@Param("celular") String paramString);
  
  Optional<List<Persona>> findByIdTelegram(String paramString);
  
  Persona findByDocumento(Integer paramInteger);
  
  @Query(nativeQuery = false, value = "select distinct p from Persona p join Inscripciones i on p.id  = i.idPersona join Curso c on i.idCurso = c.id where c.id =:idcurso")
  List<Persona> findPersonaByCurso(@Param("idcurso") Integer paramInteger);
  
  @Query(nativeQuery = true, value = "select p.* from persona p where id not in (select i.id_persona from inscripciones i  join curso c on i.id_curso = c.id  where i.id_curso =:idcurso )and p.id in( select pr.id_persona from persona_rol pr)  order by p.nombre asc")
  List<Persona> findPersonaSinCurso(@Param("idcurso") Integer paramInteger);
  
  @Modifying
  @Query(nativeQuery = false, value = "delete from Inscripciones i where i.idPersona =:idPersona  and i.idCurso =:idCurso")
  void deletePersonaConCurso(@Param("idPersona") int paramInt1, @Param("idCurso") int paramInt2);
  
  @Modifying
  @Query(nativeQuery = false, value = "delete from PersonaMinisterio pm where pm.idPersona =:idPersona  and pm.idMinisterio =:idMinisterio")
  void deletePersonaMinisterio(@Param("idPersona") int paramInt1, @Param("idMinisterio") int paramInt2);
  
  @Query(nativeQuery = true, value = "select p.* from persona p WHERE p.estado = true  order by p.nombre asc")
  List<Persona> findUsuarios();
  
  @Query(nativeQuery = true, value = "select p.* from persona p WHERE p.id not in(select pr.id_persona from persona_rol pr) and  p.id not in(select c.id_persona_consolidar from inscripcion_consolidacion c) ")
  List<Persona> listarConsolidacion();
  
  @Query(nativeQuery = true, value = "select p.* from persona p where id not in (select i.id_persona from inscripcion_actividades i  join actividades a on i.id_actividad = a.id where i.id_actividad =:idActividad )")
  List<Persona> buscarPersonaSinActividad(@Param("idActividad") Integer paramInteger);
  
  @Query(nativeQuery = true, value = "select distinct p.* from persona p join inscripcion_actividades i on p.id  = i.id_persona join actividades a on i.id_actividad = a.id where a.id =:idActividad ")
  List<Persona> buscarPersonaByActividad(@Param("idActividad") Integer paramInteger);
  
  @Query(nativeQuery = true, value = "select p.* from persona p WHERE p.id not in( select pr.id_persona from persona_rol pr) ")
  List<Persona> buscarAsistentes();
  
  @Query(nativeQuery = true, value = "select p.* from persona p where id not in (select pm.id_persona from mesa  m  join persona_mesa pm on m.id = pm.id_mesa where m.id =:idMesa)")
  List<Persona> buscarPersonaSinMesas(@Param("idMesa") Integer paramInteger);
  
  @Query(nativeQuery = true, value = "SELECT p.nombre, p.apellido, DAY(p.fechanacimiento) from persona p WHERE MONTH(fechanacimiento) =:mes and p.estado = 1 order by day( p.fechanacimiento) asc ")
  List<Object> buscarCumpleaños(@Param("mes") int paramInt);
  
  @Query(value = "select p.* from permisos_menu pm  join persona p on pm.id_persona  = p.id  where p.id = :idPersona and pm.id_menu  = :idMenu", nativeQuery = true)
  Persona findPersonaAndIdMenu(@Param("idPersona") int paramInt1, @Param("idMenu") int paramInt2);
  
  @Query(nativeQuery = true, value = "select p.* from persona p WHERE p.estado = false  order by p.nombre asc")
  List<Persona> findPeopleDisabled();
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\IPersonaRepo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */