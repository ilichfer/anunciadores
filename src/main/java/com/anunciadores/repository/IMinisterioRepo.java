package  com.anunciadores.repository;

import com.anunciadores.model.Ministerio;
import com.anunciadores.model.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IMinisterioRepo extends JpaRepository<Ministerio, Integer> {
  @Modifying
  @Query("select p from Persona p join PersonaMinisterio pm on p.id = pm.idPersona where pm.idMinisterio = :idMinisterio  and p.estado = true  order by p.nombre asc ")
  List<Persona> findPersonasByIdMinisterio(@Param("idMinisterio") int paramInt);
  
  @Modifying
  @Query("select p from Persona p  WHERE p.id not in( select pm.idPersona  from PersonaMinisterio pm join Ministerio m on pm.idMinisterio  = m.id where m.id =:idMinisterio) and  p.estado = true order by p.nombre asc ")
  List<Persona> findPeopleWithOutMinisterio(@Param("idMinisterio") int paramInt);
  
  @Query("select  m.nombre, pm .nombrePosicion from Ministerio m join PosicionesMinisterio pm on m.id = pm.idMinisterio where m.id = :idMinisterio and pm.id = :posiciones ")
  Object findMnisteryAndPosition(@Param("idMinisterio") int paramInt1, @Param("posiciones") int paramInt2);
  
  @Query("select m.nombre, pm from Ministerio m  join PosicionesMinisterio pm on m.id = pm.idMinisterio ")
  List<Object> findMnisteryWithPositionS();
  
  @Query("select m.nombre from Ministerio m  join PersonaMinisterio pm on m.id = pm.idMinisterio where pm.idPersona =:idPersona")
  List<String> findNameMinisterio(@Param("idPersona") int paramInt);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\IMinisterioRepo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */