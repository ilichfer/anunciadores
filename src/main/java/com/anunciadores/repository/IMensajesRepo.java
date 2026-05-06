package  com.anunciadores.repository;

import com.anunciadores.model.Mensajes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMensajesRepo extends JpaRepository<Mensajes, Integer> {
  @Query("select m from Mensajes m where m.remitente.id = :idPersona and m.activo = true ")
  List<Mensajes> findByIdPersona(int paramInt);
  
  @Query("select m from Mensajes m where m.destinatario.id = :idPersona")
  List<Mensajes> findMensajesByIdPersona(int paramInt);
  
  @Query("select m from Mensajes m where m.destinatario.id = :idPersona and m.activo = true ")
  List<Mensajes> mesajesSinLeerByIdPersona(int paramInt);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\IMensajesRepo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */