package  com.anunciadores.service;

import com.anunciadores.repository.RolesRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userDetailService")
public class UsuarioService {
  @Autowired
  private RolesRepoImpl rolesDao;
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\UsuarioService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */