package  com.anunciadores.repository;

import com.anunciadores.model.Servicio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IServicioRepo extends JpaRepository<Servicio, Integer> {
  @Modifying
  @Query("select s.fechaServicio, concat(p.nombre,' ',p.apellido), pm.nombrePosicion, m.id, m.nombre, p.idTelegram, p.celular from Servicio s join Persona p on s.idPersona = p.id join PosicionesMinisterio pm on s.idPosicion = pm.id join Ministerio m on pm.idMinisterio = m.id WHERE s.fechaServicio = :fechaServicio  order by m.id asc")
  List<Object> findProgramacionServicio(@Param("fechaServicio") Date paramDate);
  
  @Query("select s from Servicio s where s.idPersona = :idPersona and s.fechaServicio = :fechaServicio")
  Optional<Servicio> findProgramacionServidor(@Param("idPersona") int paramInt, @Param("fechaServicio") Date paramDate);
  
  @Query("select s from Servicio s join Persona p on (s.idPersona = p.id) join PosicionesMinisterio pm on(s.idPosicion = pm.id) where p.nombre = :persona and s.fechaServicio = :fechaServicio and pm.nombrePosicion =:nombrePosicion")
  Optional<Servicio> findProgramacionServidorAndMinisterio(@Param("persona") String paramString1, @Param("fechaServicio") Date paramDate, @Param("nombrePosicion") String paramString2);
  
  @Query("select s from Servicio s where s.idPersona = :idPersona and s.fechaServicio = :fechaServicio")
  Optional<List<Servicio>> findAllServiceByDateAndPerson(@Param("idPersona") int paramInt, @Param("fechaServicio") Date paramDate);
  
  @Query("select p.id  , p.nombre,pm.id , pm.nombrePosicion from Servicio s             join Persona p on s.idPersona = p.id             join PosicionesMinisterio pm on s.idPosicion = pm.id             join Ministerio m on pm.idMinisterio = m.id             WHERE s.fechaServicio = :fechaServicio            and m.id = :idMinisterio")
  List<Object> findProgramacionByDateAndMinistery(@Param("fechaServicio") Date paramDate, @Param("idMinisterio") int paramInt);
  
  @Query("select s.fechaServicio, concat(p.nombre,' ',p.apellido), pm.nombrePosicion, m.id, m.nombre, p.idTelegram, p.celular from Servicio s             join Persona p on s.idPersona = p.id             join PosicionesMinisterio pm on s.idPosicion = pm.id             join Ministerio m on pm.idMinisterio = m.id             WHERE s.fechaServicio = :fechaServicio            and m.id = :idMinisterio")
  List<Object> findProgramacionByDateAndidMinistery(@Param("fechaServicio") Date paramDate, @Param("idMinisterio") int paramInt);
  
  @Modifying
  void deleteByFechaServicioAndIdMinisterio(@Param("fechaServicio") Date paramDate, int paramInt);
  
  @Modifying
  List<Servicio> findByFechaServicioAndIdMinisterio(@Param("fechaServicio") Date paramDate, int paramInt);
  
  @Query("select s from Servicio s WHERE s.fechaServicio  BETWEEN :fechaInicial AND :fechaFinal and s.idPersona =:idPersona order by s.fechaServicio asc")
  List<Servicio> BuscarServicioMes(@Param("fechaInicial") Date paramDate1, @Param("fechaFinal") Date paramDate2, @Param("idPersona") int paramInt);
  
  @Query(value = "SELECT fecha_servicio FROM servicio ORDER BY ABS(TIMESTAMPDIFF(SECOND, fecha_servicio, NOW())) ASC LIMIT 1", nativeQuery = true)
  Optional<Date> findNextDateService();
  
  @Query("SELECT DISTINCT s.idMinisterio FROM Servicio s where s.fechaServicio = :fecha ")
  Optional<List<Integer>> findDistinctIdMinisterio(@Param("fecha") Date paramDate);
  
  @Query(value = "select s.fecha_servicio,m.nombre ,pm.id ,pm.nombre_posicion, concat(p.nombre,' ',p.apellido), p.id as id_persona     from servicio s     \n    join persona p on s.id_Persona = p.id     \n    join posiciones_ministerios  pm on s.id_posicion = pm.id     \n    join ministerios  m on pm.id_Ministerio = m.id     \n    WHERE s.fecha_servicio = :fecha    \n    and m.id = :idMinisterio", nativeQuery = true)
  List<Object> findMInisteriesAndpositions(@Param("fecha") Date paramDate, @Param("idMinisterio") int paramInt);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\IServicioRepo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */