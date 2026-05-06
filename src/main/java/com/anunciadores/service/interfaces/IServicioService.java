package  com.anunciadores.service.interfaces;

import com.anunciadores.dto.CoordinadorDTO;
import com.anunciadores.dto.ItemCombo;
import com.anunciadores.dto.MinisterioDto;
import com.anunciadores.dto.PersonaDto;
import com.anunciadores.dto.PosicionDto;
import com.anunciadores.dto.ProgramationDto;
import com.anunciadores.dto.ServiceDTO;
import com.anunciadores.dto.ServicioDto;
import com.anunciadores.dto.ServicioListResponseDto;
import com.anunciadores.dto.ServicioResponseDto;
import com.anunciadores.model.Coordinador;
import com.anunciadores.model.Ministerio;
import com.anunciadores.model.Persona;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

public interface IServicioService {
  List<Ministerio> getAll();
  
  Ministerio saveMinisterio(String paramString);
  
  Ministerio saveMinisterio(MinisterioDto paramMinisterioDto);
  
  void deleteMinisterio(int paramInt);
  
  Ministerio findByidMnisterio(int paramInt);
  
  List<PersonaDto> findPersonaByidMnisterio(int paramInt);
  
  List<PersonaDto> findPersonaByidMnisterioAsistencia(int paramInt);
  
  List<MinisterioDto> getPositionByidMinisterio(int paramInt);
  
  List<MinisterioDto> getPositionByidMinisterioAndPerson(Date paramDate, int paramInt);
  
  List<MinisterioDto> limpiarListaPosiciones(List<MinisterioDto> paramList, Date paramDate, int paramInt);
  
  List<PersonaDto> getPeopleWithoutMinisterio(int paramInt);
  
  void saveProgramacion(ServicioDto paramServicioDto, Date paramDate, int paramInt);
  
  Boolean saveCoordinado(CoordinadorDTO paramCoordinadorDTO) throws ParseException;
  
  Boolean saveCoordinadorEntity(CoordinadorDTO paramCoordinadorDTO);
  
  Coordinador findCoordinador(List<ServicioListResponseDto> paramList);
  
  Coordinador findCoordinadorAdministrator(HttpServletRequest paramHttpServletRequest);
  
  Coordinador findCoordinadorByFecha(Date paramDate);
  
  Coordinador findCoordinadorByFechaAndIdPersona(String paramString, int paramInt);
  
  Boolean validateCoordinadorByFechaAndIdPersona(String paramString, int paramInt);
  
  void updateProgramacion(ServicioDto paramServicioDto, Date paramDate, int paramInt);
  
  List<Persona> saveProgram(List<ServiceDTO> paramList);
  
  void deleteProgramacion(Date paramDate);
  
  Optional<Persona> validarProgramacionByFecha(ServicioDto paramServicioDto, Date paramDate);
  
  Optional<Persona> validarActualizarProgramacionByFecha(ServicioDto paramServicioDto, Date paramDate, int paramInt);
  
  boolean validarActualizarProgramacionByFechaAndName(ServicioDto paramServicioDto, Date paramDate, int paramInt);
  
  List<ServicioListResponseDto> findProgramacionByDate(Date paramDate);
  
  List<ServicioResponseDto> findProgramacionByDateAndMinisterio(Date paramDate, int paramInt);
  
  List<ServicioListResponseDto> findProgramacionByDateGroup(Date paramDate) throws ParseException;
  
  List<ItemCombo> findItemsCombo();
  
  void agregarPersonaAMinisterio(int paramInt1, int paramInt2);
  
  void agregarPersonasAMinisterio(List<Long> paramList, int paramInt);
  
  List<ServicioResponseDto> buscarProgramacionMes(int paramInt) throws ParseException;
  
  void savePosicion(PosicionDto paramPosicionDto);
  
  void editPosicion(PosicionDto paramPosicionDto);
  
  PosicionDto findPosicion(int paramInt);
  
  Boolean validarDuplicados(ServicioDto paramServicioDto);
  
  Persona identificarDuplicados(ServicioDto paramServicioDto);
  
  List<MinisterioDto> poblarPosiciones(List<MinisterioDto> paramList, ServicioDto paramServicioDto);
  
  List<MinisterioDto> getPositionInitial(List<MinisterioDto> paramList);
  
  Persona getPersonDuplicate(ServicioDto paramServicioDto);
  
  ProgramationDto findNextServices(Date paramDate) throws ParseException;
  
  ProgramationDto findServices(Date paramDate) throws ParseException;
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\interfaces\IServicioService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */