package  com.anunciadores.service.interfaces;

import com.anunciadores.dto.CursoDto;
import com.anunciadores.dto.PersonaDto;
import com.anunciadores.model.Curso;
import com.anunciadores.model.NotasCurso;
import com.anunciadores.model.Persona;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ICursoService {
  List<Curso> findAll();
  
  List<Curso> findAllActive();
  
  List<Curso> findAllActiveByPerson(int paramInt);
  
  Curso save(CursoDto paramCursoDto) throws ParseException;
  
  Curso desactivarCurso(Curso paramCurso) throws ParseException;
  
  Curso findCursoById(Integer paramInteger);
  
  List<CursoDto> findCursosDtoByIdPersona(Integer paramInteger);
  
  List<Curso> findCursosByIdPersona(Integer paramInteger);
  
  Curso delete(Curso paramCurso);
  
  NotasCurso findNotasByCurso(int paramInt1, int paramInt2);
  
  List<NotasCurso> findHistoricoNotas(int paramInt);
  
  NotasCurso saveNotasCurso(NotasCurso paramNotasCurso) throws ParseException;
  
  Date ParseFecha(String paramString) throws ParseException;
  
  String formatFecha(String paramString) throws ParseException;
  
  List<PersonaDto> buscarNotasXPersonas(int paramInt, List<Persona> paramList);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\interfaces\ICursoService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */