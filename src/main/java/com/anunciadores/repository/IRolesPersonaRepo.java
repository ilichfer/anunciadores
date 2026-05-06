package  com.anunciadores.repository;

import com.anunciadores.model.RolPersona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRolesPersonaRepo extends JpaRepository<RolPersona, Integer> {
  @Modifying
  @Query("select u from RolPersona u  where u.idPersona = :idPersona")
  List<RolPersona> findRolByidPersona(@Param("idPersona") int paramInt);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\IRolesPersonaRepo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */