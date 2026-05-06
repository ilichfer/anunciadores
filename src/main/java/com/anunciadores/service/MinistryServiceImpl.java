  package  com.anunciadores.service;
  import com.anunciadores.auth.dto.assignments;
  import com.anunciadores.auth.dto.updateServiceRequest;
  import com.anunciadores.dto.Ministry;
  import com.anunciadores.dto.PositionDto;
  import com.anunciadores.mapper.mapperParametros;
  import com.anunciadores.model.Ministerio;
  import com.anunciadores.model.PersonaMinisterio;
  import com.anunciadores.model.PosicionesMinisterio;
  import com.anunciadores.model.Servicio;
  import com.anunciadores.repository.ICoordinadorRepo;
  import com.anunciadores.repository.IMinisterioRepo;
  import com.anunciadores.repository.IParametrosRepo;
  import com.anunciadores.repository.IPersonaMinisterioRepo;
  import com.anunciadores.repository.IPersonaRepo;
  import com.anunciadores.repository.IPosicionesRepo;
  import com.anunciadores.repository.IServicioRepo;
  import com.anunciadores.service.interfaces.IMInisteryService;
  import com.anunciadores.util.UtilDate;
  import java.text.ParseException;
  import java.util.ArrayList;
  import java.util.Date;
  import java.util.List;
  import java.util.Optional;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Service;
  import org.springframework.transaction.annotation.Transactional;
  
  @Service
  public class MinistryServiceImpl implements IMInisteryService {
/*  33 */   private Logger LOGGER = LoggerFactory.getLogger(com.anunciadores.service.MinistryServiceImpl.class);
    
    @Autowired
    private IMinisterioRepo ministerioRepository;
    
    @Autowired
    private IPosicionesRepo posicionesRepository;
    
    @Autowired
    private IServicioRepo servicioRepository;
    
    @Autowired
    private ICoordinadorRepo coordinadorRepo;
    
    @Autowired
    private IPersonaMinisterioRepo personaMinisterioRepoSitory;
    
    @Autowired
    private IPersonaRepo personaRepository;
    
    @Autowired
    private IParametrosRepo parametrosRepo;
    
    @Autowired
    private mapperParametros mapperParametros;
    
    @Autowired
    private UtilDate utilDate;
    
    public List<Ministry> getAllministriesWithPositions() {
/*  63 */     List<Ministry> ministryList = new ArrayList<>();
      
/*  65 */     List<Ministerio> listMin = this.ministerioRepository.findAll();
  
      
/*  68 */     if (listMin != null && listMin.size() > 0) {
/*  69 */       listMin.forEach(m -> {
              Ministry min = new Ministry();
              
              List<PosicionesMinisterio> listpositions = this.posicionesRepository.findAllByIdMinisterio(m.getId());
              
              min = new Ministry();
              
              min.setId(String.valueOf(m.getId()));
              
              min.setName(m.getNombre());
              
              List<PositionDto> listP = new ArrayList<>();
              
              //listpositions.forEach(());
              
              min.setPositions(listP);
              ministryList.add(min);
            });
      }
/*  88 */     return ministryList;
    }
    
    private PositionDto createPosition(PosicionesMinisterio p) {
/*  92 */     PositionDto position = new PositionDto();
/*  93 */     position.setId(p.getId());
/*  94 */     position.setName(p.getNombrePosicion());
/*  95 */     return position;
    }
  
    
    public void agregarPersonasAMinisterio(int idPersona, int idMinisterio) {
      try {
/* 101 */       PersonaMinisterio personaSave = new PersonaMinisterio();
/* 102 */       personaSave.setIdMinisterio(idMinisterio);
/* 103 */       personaSave.setIdPersona(idPersona);
/* 104 */       this.personaMinisterioRepoSitory.save(personaSave);
/* 105 */     } catch (Exception e) {
/* 106 */       e.printStackTrace();
      } 
    }
  
    
    public Ministry getMinistryWithPositions(Integer idMinisterio) {
/* 112 */     List<Ministry> ministryList = new ArrayList<>();
      
/* 114 */     Optional<Ministerio> ministerio = this.ministerioRepository.findById(idMinisterio);
/* 115 */     Ministry min = new Ministry();
      
/* 117 */     if (ministerio.isPresent()) {
/* 118 */       List<PosicionesMinisterio> listpositions = this.posicionesRepository.findAllByIdMinisterio(((Ministerio)ministerio.get()).getId());
        
/* 120 */       min = new Ministry();
/* 121 */       min.setId(String.valueOf(((Ministerio)ministerio.get()).getId()));
/* 122 */       min.setName(((Ministerio)ministerio.get()).getNombre());
        
/* 124 */       List<PositionDto> listP = new ArrayList<>();
/* 125 */       listpositions.forEach(p -> listP.add(createPosition(p)));
/* 126 */       min.setPositions(listP);
      } 
/* 128 */     return min;
    }
  
    
    @Transactional
    public void updateService(updateServiceRequest updateRequest) throws ParseException {
/* 134 */     Date fechaaServicio = this.utilDate.convertStringToDate(updateRequest.getDate());
/* 135 */     this.servicioRepository.deleteByFechaServicioAndIdMinisterio(fechaaServicio, updateRequest.getMinistryId());
      
/* 137 */     List<Servicio> progServicio = new ArrayList<>();
      
/* 139 */     for (int i = 0; i < updateRequest.getAssignments().size(); i++) {
/* 140 */       if (((assignments)updateRequest.getAssignments().get(i)).getPersonId() != 0) {
/* 141 */         Servicio servicio = new Servicio();
/* 142 */         PosicionesMinisterio posicionEntity = this.posicionesRepository.findMinisterioByName(((assignments)updateRequest.getAssignments().get(i)).getIdPosicion(), updateRequest.getMinistryId());
/* 143 */         servicio.setFechaServicio(this.utilDate.convertStringToDate(updateRequest.getDate()));
/* 144 */         servicio.setIdMinisterio(updateRequest.getMinistryId());
/* 145 */         servicio.setIdPersona(((assignments)updateRequest.getAssignments().get(i)).getPersonId());
/* 146 */         servicio.setIdPosicion(((assignments)updateRequest.getAssignments().get(i)).getIdPosicion());
/* 147 */         progServicio.add(servicio);
        } 
/* 149 */       this.servicioRepository.saveAll(progServicio);
      } 
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\MinistryServiceImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */