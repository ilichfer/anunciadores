package  com.anunciadores.repository;

import com.anunciadores.model.Actividad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IActividadRepo extends JpaRepository<Actividad, Integer> {
  @Query(" SELECT ac from Actividad ac join InscripcionActividad ia on (ac.id = ia.idActividad)  WHERE ia.idPersona = ?1")
  List<Actividad> findActividadesByPersona(int paramInt);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\IActividadRepo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */