package  com.anunciadores.repository;

import com.anunciadores.model.Consolidacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsolidacionRepo extends JpaRepository<Consolidacion, Integer> {
  Consolidacion findByIdPersona(int paramInt);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\IConsolidacionRepo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */