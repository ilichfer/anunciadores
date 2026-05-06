package  com.anunciadores.service.interfaces;

import com.anunciadores.model.Actividad;
import com.anunciadores.model.Mesa;
import com.anunciadores.model.Persona;
import java.util.List;

public interface IMesasService {
  List<Actividad> listarActiviades();
  
  List<Persona> BuscarPersonasSinMesa(Mesa paramMesa);
  
  void delete(Mesa paramMesa);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\interfaces\IMesasService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */