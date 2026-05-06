package  com.anunciadores.service.interfaces;

import com.anunciadores.dto.PersonaDto;
import com.anunciadores.dto.PersonaReactDto;
import com.anunciadores.dto.UserResponseDto;
import com.anunciadores.model.Consolidacion;
import com.anunciadores.model.EstudioPersona;
import com.anunciadores.model.Persona;
import java.util.List;

public interface IPersonaService {
  List<Persona> findAllUsuarios();
  
  Persona save(Persona paramPersona);
  
  Persona update(Persona paramPersona);
  
  Persona saveAsistente(Persona paramPersona);
  
  Persona saveAsistenteConsolidacion(Persona paramPersona, Consolidacion paramConsolidacion);
  
  Persona findPersonaById(Integer paramInteger);
  
  Persona findPersonaByNombre(String paramString);
  
  String delete(Persona paramPersona);
  
  Persona toggleActive(Integer paramInteger, Boolean paramBoolean);
  
  String habilitar(Persona paramPersona);
  
  PersonaDto buscarByDocumento(Integer paramInteger);
  
  PersonaDto buscarEmail(String paramString);
  
  List<Persona> findAllByCurso(int paramInt);
  
  List<Persona> buscarTodosSinCurso(int paramInt);
  
  void agregarPersonaCurso(int paramInt1, int paramInt2);
  
  void eliminarPersonaCurso(int paramInt1, int paramInt2);
  
  void eliminarPersonaMinisterio(int paramInt1, int paramInt2);
  
  void agregarPersonaActividad(int paramInt1, int paramInt2);
  
  List<PersonaDto> buscarConsolidacion(List<Persona> paramList, int paramInt);
  
  String encriptar(String paramString);
  
  Persona personaDtoToEntity(PersonaDto paramPersonaDto);
  
  Persona savePassword(Persona paramPersona);
  
  List<PersonaDto> findAllUsuariosRol();
  
  List<PersonaDto> findBirthdayByMonth();
  
  void findUsuariosRol(int paramInt1, int paramInt2);
  
  List<EstudioPersona> findEstudiosPersona(int paramInt);
  
  EstudioPersona saveEstudio(EstudioPersona paramEstudioPersona);
  
  List<PersonaDto> getBirthDay(List<PersonaDto> paramList);
  
  List<PersonaReactDto> findAllUsers();
  
  UserResponseDto getUsuarioDesdeToken(String paramString);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\interfaces\IPersonaService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */