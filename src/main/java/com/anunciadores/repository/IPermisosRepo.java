package  com.anunciadores.repository;

import com.anunciadores.model.PermisosMenu;
import com.anunciadores.model.Persona;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPermisosRepo extends JpaRepository<PermisosMenu, Integer> {
  @Query
  List<PermisosMenu> findByIdPersona(int paramInt);
  
  Optional<PermisosMenu> findByIdPersonaAndNombreBotonMenu(int paramInt, String paramString);
  
  @Query(value = "select p.* from permisos_menu pm join persona p on pm.id_persona  = p.id  where p.id = :idPersona and id_menu  = 9", nativeQuery = true)
  Persona findByIdPersonaAndIdMenu(int paramInt);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\IPermisosRepo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */