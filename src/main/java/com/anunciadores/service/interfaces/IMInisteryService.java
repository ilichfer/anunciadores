package  com.anunciadores.service.interfaces;

import com.anunciadores.auth.dto.updateServiceRequest;
import com.anunciadores.dto.Ministry;
import java.text.ParseException;
import java.util.List;

public interface IMInisteryService {
  List<Ministry> getAllministriesWithPositions();
  
  void agregarPersonasAMinisterio(int paramInt1, int paramInt2);
  
  Ministry getMinistryWithPositions(Integer paramInteger);
  
  void updateService(updateServiceRequest paramupdateServiceRequest) throws ParseException;
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\interfaces\IMInisteryService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */