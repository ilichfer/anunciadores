package  com.anunciadores.service.interfaces;

import com.anunciadores.dto.ActividadDto;
import com.anunciadores.model.Actividad;
import com.anunciadores.model.Mesa;
import com.anunciadores.model.Persona;
import java.text.ParseException;
import java.util.List;

public interface IActividadService {
  List<Actividad> listarActiviades();
  
  Actividad save(ActividadDto paramActividadDto) throws ParseException;
  
  Actividad findActividadById(Integer paramInteger);
  
  void delete(ActividadDto paramActividadDto);
  
  List<Persona> buscarTodosSinActividad(int paramInt);
  
  List<Persona> buscarPersonasActividad(int paramInt);
  
  List<Mesa> buscarMesasActividad(ActividadDto paramActividadDto);
  
  List<Actividad> listarActiviadesByPersona(int paramInt);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\interfaces\IActividadService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */