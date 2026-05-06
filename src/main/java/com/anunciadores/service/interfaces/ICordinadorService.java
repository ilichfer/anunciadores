package  com.anunciadores.service.interfaces;

import com.anunciadores.dto.CoordinadorDTO;
import java.util.Date;
import java.util.List;

public interface ICordinadorService {
  List<CoordinadorDTO> findAllBetweenDates(Date paramDate1, Date paramDate2);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\interfaces\ICordinadorService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */