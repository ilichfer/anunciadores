  package  com.anunciadores.service;
  
  import com.anunciadores.dto.AsignacionConsolidacionDto;
  import com.anunciadores.dto.PersonaConsolidacionDto;
  import com.anunciadores.mapper.mapperConsolidacion;
  import com.anunciadores.model.Consolidacion;
  import com.anunciadores.model.HistoricoConsolidacion;
  import com.anunciadores.model.Ministerio;
  import com.anunciadores.model.Persona;
  import com.anunciadores.model.inscripcionConsolidacion;
  import com.anunciadores.repository.IConsolidacionRepo;
  import com.anunciadores.repository.IHisConsolidacionRepo;
  import com.anunciadores.repository.IInscripcionConsolidacionRepo;
  import com.anunciadores.repository.IMinisterioRepo;
  import com.anunciadores.repository.IPersonaRepo;
  import com.anunciadores.service.interfaces.IConsolidacionService;
  import java.util.ArrayList;
  import java.util.Date;
  import java.util.List;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Service;
  
  
  
  
  
  @Service
  public class ConsolidacionServiceImpl
    implements IConsolidacionService
  {
    @Autowired
    private IInscripcionConsolidacionRepo cosolidacionRepository;
    @Autowired
    private IMinisterioRepo ministerioRepo;
    @Autowired
    private mapperConsolidacion mapperConsolidacion;
    
    public Boolean asignarPersonaAPadreEspiritual(int idPadreEspiritual, int idPersonaConsolidar) {
/*  39 */     Boolean asignacion = Boolean.FALSE;
/*  40 */     List<inscripcionConsolidacion> insConsolidacion = this.inscripcionConsolidacionRepo.findByIdPadreEspiritual(idPadreEspiritual);
/*  41 */     if (insConsolidacion.isEmpty()) {
        
/*  43 */       inscripcionConsolidacion inscripcionConsolidacion = new inscripcionConsolidacion();
/*  44 */       inscripcionConsolidacion.setIdPadreEspiritual(idPadreEspiritual);
/*  45 */       inscripcionConsolidacion.setIdPersonaConsolidar(idPersonaConsolidar);
        
/*  47 */       this.cosolidacionRepository.save(inscripcionConsolidacion);
/*  48 */       asignacion = Boolean.TRUE;
      } 
/*  50 */     return asignacion; } @Autowired
    private IInscripcionConsolidacionRepo inscripcionConsolidacionRepo; @Autowired
    private IPersonaRepo personaRepo; @Autowired
    private IConsolidacionRepo consolidacionRepo; @Autowired
    private IHisConsolidacionRepo hisConsolidacionRepo; public List<PersonaConsolidacionDto> listarservidoresConsolidacion(String nombreMInisterio) {
/*  55 */     List<PersonaConsolidacionDto> servidoresConsolidacion = new ArrayList<>();
/*  56 */     List<Ministerio> ministerios = this.ministerioRepo.findAll();
/*  57 */     for (Ministerio min : ministerios) {
/*  58 */       if (min.getNombre().equalsIgnoreCase(nombreMInisterio))
        {
/*  60 */         servidoresConsolidacion = buscarAsignacion(this.mapperConsolidacion.liitEntitytoConsolidacionDto(this.ministerioRepo.findPersonasByIdMinisterio(min.getId())));
        }
      } 
/*  63 */     return servidoresConsolidacion;
    }
  
  
    
    public List<AsignacionConsolidacionDto> listarConsolidacionByServidor(int idServidor) {
/*  69 */     List<AsignacionConsolidacionDto> asignacionlist = new ArrayList<>();
      
/*  71 */     List<Persona> personasList = this.inscripcionConsolidacionRepo.findConsolidacionesByServidor(idServidor);
/*  72 */     if (!personasList.isEmpty()) {
/*  73 */       for (Persona per : personasList) {
/*  74 */         AsignacionConsolidacionDto dto = new AsignacionConsolidacionDto();
/*  75 */         Consolidacion con = this.consolidacionRepo.findByIdPersona(per.getId().intValue());
/*  76 */         dto.setNombre(per.getNombre());
/*  77 */         dto.setApellido(per.getApellido());
/*  78 */         dto.setTelefono(per.getTelefono());
/*  79 */         dto.setHorarioConsolidacionSugerido(con.getHorarioConsolidacionSugerido());
/*  80 */         dto.setHorarioConsolidacionPersona(con.getHorarioConsolidacionPersona());
/*  81 */         dto.setIdConsolidacion(Integer.valueOf(con.getId()));
/*  82 */         dto.setIdPersona(idServidor);
/*  83 */         asignacionlist.add(dto);
        } 
/*  85 */       return asignacionlist;
      } 
/*  87 */     return null;
    }
  
    
    public void saveDescripcionConsolidacion(int idConsolidacion, String DescConsolidacion) {
/*  92 */     HistoricoConsolidacion his = new HistoricoConsolidacion();
/*  93 */     his.setFechaRegistroConsolidacion(new Date());
/*  94 */     his.setIdConsolidacion(idConsolidacion);
/*  95 */     his.setDescripcionConsolidacion(DescConsolidacion);
/*  96 */     this.hisConsolidacionRepo.save(his);
    }
  
    
    private List<PersonaConsolidacionDto> buscarAsignacion(List<PersonaConsolidacionDto> servidoresConsolidacion) {
/* 101 */     for (PersonaConsolidacionDto per : servidoresConsolidacion) {
/* 102 */       List<AsignacionConsolidacionDto> asignacionlist = new ArrayList<>();
/* 103 */       List<inscripcionConsolidacion> insConsolidacion = this.inscripcionConsolidacionRepo.findByIdPadreEspiritual(per.getId());
/* 104 */       if (insConsolidacion.isEmpty()) {
/* 105 */         AsignacionConsolidacionDto asignacionConsolidacionDto = new AsignacionConsolidacionDto();
/* 106 */         asignacionConsolidacionDto.setNombre("N/A");
/* 107 */         asignacionConsolidacionDto.setApellido("");
/* 108 */         asignacionConsolidacionDto.setDocumento(Integer.valueOf(0));
/* 109 */         asignacionConsolidacionDto.setHorarioConsolidacionPersona("N/A");
/* 110 */         asignacionConsolidacionDto.setTelefono("N/A");
/* 111 */         asignacionConsolidacionDto.setHorarioConsolidacionPersona("N/A");
/* 112 */         asignacionConsolidacionDto.setHorarioConsolidacionSugerido("N/A");
/* 113 */         asignacionlist.add(asignacionConsolidacionDto);
/* 114 */         per.setAsignacion(asignacionlist);
/* 115 */         per.setTamanoLista(Integer.valueOf(asignacionlist.size() + 1));
          continue;
        } 
/* 118 */       AsignacionConsolidacionDto dto = new AsignacionConsolidacionDto();
/* 119 */       Persona perAsignacion = this.personaRepo.findById(Integer.valueOf(((inscripcionConsolidacion)insConsolidacion.get(0)).getIdPersonaConsolidar())).get();
/* 120 */       Consolidacion con = this.consolidacionRepo.findByIdPersona(((inscripcionConsolidacion)insConsolidacion.get(0)).getIdPersonaConsolidar());
/* 121 */       dto.setNombre(perAsignacion.getNombre());
/* 122 */       dto.setApellido(perAsignacion.getApellido());
/* 123 */       dto.setDocumento(perAsignacion.getDocumento());
/* 124 */       dto.setTelefono(perAsignacion.getTelefono());
/* 125 */       dto.setHorarioConsolidacionPersona(con.getHorarioConsolidacionPersona());
/* 126 */       dto.setHorarioConsolidacionSugerido(con.getHorarioConsolidacionSugerido());
/* 127 */       asignacionlist.add(dto);
/* 128 */       per.setAsignacion(asignacionlist);
/* 129 */       per.setTamanoLista(Integer.valueOf(asignacionlist.size() + 1));
      } 
  
      
/* 133 */     return servidoresConsolidacion;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\ConsolidacionServiceImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */