package  com.anunciadores.repository;

import com.anunciadores.model.VersiculoSemanal;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVersiculoRepo extends JpaRepository<VersiculoSemanal, Integer> {
  Optional<VersiculoSemanal> findByFechaFin(Date paramDate);
  
  Optional<VersiculoSemanal> findByFechaFinBetween(Date paramDate1, Date paramDate2);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\IVersiculoRepo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */