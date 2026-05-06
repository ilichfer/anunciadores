package  com.anunciadores.repository;

import com.anunciadores.model.ParametrosCombos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface IParametrosRepo extends JpaRepository<ParametrosCombos, Integer> {
  List<ParametrosCombos> findByGrupo(@Param("grupo") String paramString);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\IParametrosRepo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */