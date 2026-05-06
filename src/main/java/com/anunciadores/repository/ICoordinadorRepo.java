package  com.anunciadores.repository;

import com.anunciadores.model.Coordinador;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICoordinadorRepo extends JpaRepository<Coordinador, Integer> {
  Coordinador findByFechaServicio(@Param("fechaServicio") Date paramDate);
  
  @Query("select c from Coordinador c where c.persona.id = :idPersona  and c.fechaServicio = :fechaServicio ")
  Optional<Coordinador> findByIdPersonaAndIdPersona(@Param("fechaServicio") Date paramDate, @Param("idPersona") int paramInt);
  
  @Query("select c from Coordinador c WHERE c.fechaServicio  BETWEEN :fechaInicial AND :fechaFinal and c.persona.id =:idPersona order by c.fechaServicio asc")
  List<Coordinador> buscarServicioCoordinadorMes(@Param("fechaInicial") Date paramDate1, @Param("fechaFinal") Date paramDate2, @Param("idPersona") int paramInt);
  
  @Query("select c from Coordinador c WHERE c.fechaServicio  BETWEEN :fechaInicial AND :fechaFinal order by c.fechaServicio asc")
  List<Coordinador> buscarHisInformes(@Param("fechaInicial") Date paramDate1, @Param("fechaFinal") Date paramDate2);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\ICoordinadorRepo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */