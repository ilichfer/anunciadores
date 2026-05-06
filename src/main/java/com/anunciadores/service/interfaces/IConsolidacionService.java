package  com.anunciadores.service.interfaces;

import com.anunciadores.dto.AsignacionConsolidacionDto;
import com.anunciadores.dto.PersonaConsolidacionDto;
import java.util.List;

public interface IConsolidacionService {
  Boolean asignarPersonaAPadreEspiritual(int paramInt1, int paramInt2);
  
  List<PersonaConsolidacionDto> listarservidoresConsolidacion(String paramString);
  
  List<AsignacionConsolidacionDto> listarConsolidacionByServidor(int paramInt);
  
  void saveDescripcionConsolidacion(int paramInt, String paramString);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\interfaces\IConsolidacionService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */