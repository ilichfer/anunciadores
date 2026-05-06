package  com.anunciadores.repository;

import com.anunciadores.model.Persona;
import com.anunciadores.model.inscripcionConsolidacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IInscripcionConsolidacionRepo extends JpaRepository<inscripcionConsolidacion, Integer> {
  @Query(" SELECT p FROM Persona p join inscripcionConsolidacion ic on p.id = ic.idPersonaConsolidar WHERE ic.idPadreEspiritual = ?1")
  List<Persona> findConsolidacionesByServidor(int paramInt);
  
  List<inscripcionConsolidacion> findByIdPadreEspiritual(int paramInt);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\IInscripcionConsolidacionRepo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */