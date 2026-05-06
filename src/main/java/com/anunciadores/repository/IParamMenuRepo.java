package  com.anunciadores.repository;

import com.anunciadores.model.ParamMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IParamMenuRepo extends JpaRepository<ParamMenu, Integer> {
  ParamMenu findByNombreBotonMenu(String paramString);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\IParamMenuRepo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */