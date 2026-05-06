package  com.anunciadores.service.interfaces;

import com.anunciadores.dto.MensajesDTO;
import java.util.List;

public interface IMensajeService {
  List<MensajesDTO> buscarMensaje(Integer paramInteger);
  
  List<MensajesDTO> buscarTodosMensaje(Integer paramInteger);
  
  MensajesDTO buscarMensajeXId(Integer paramInteger);
  
  MensajesDTO guardarMensaje(MensajesDTO paramMensajesDTO);
  
  void enviarTodosMensajes(MensajesDTO paramMensajesDTO);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\interfaces\IMensajeService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */