package  com.anunciadores.service.interfaces;

import com.anunciadores.dto.PagoDto;
import com.anunciadores.dto.ReportePagoDto;
import com.anunciadores.model.Pago;
import java.util.List;

public interface IPagoService {
  List<Pago> findAll();
  
  Pago save(Pago paramPago);
  
  List<Pago> findPagosByIdCurso(int paramInt1, int paramInt2);
  
  Pago delete(Pago paramPago);
  
  List<PagoDto> reportePagosCursos(int paramInt);
  
  List<ReportePagoDto> reportePagos();
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\interfaces\IPagoService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */