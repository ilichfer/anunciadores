  package  com.anunciadores.service;
  import com.anunciadores.dto.TdcDto;
  import com.anunciadores.dto.TdcReporteDto;
  import com.anunciadores.model.Persona;
  import com.anunciadores.model.Tdc;
  import com.anunciadores.repository.IPersonaRepo;
  import com.anunciadores.repository.ITdcRepo;
  import com.anunciadores.service.interfaces.ITdcService;
  import com.anunciadores.util.UtilDate;
  import java.awt.Graphics2D;
  import java.awt.image.BufferedImage;
  import java.io.IOException;
  import java.sql.Date;
  import java.text.ParseException;
  import java.text.SimpleDateFormat;
  import java.time.LocalDate;
  import java.time.format.DateTimeFormatter;
  import java.util.ArrayList;
  import java.util.Date;
  import java.util.List;
  import java.util.Optional;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.scheduling.annotation.EnableScheduling;
  import org.springframework.stereotype.Service;
  import org.springframework.transaction.annotation.Transactional;
  
  @Configuration
  @EnableScheduling
  @Service
  public class TdcServiceImpl implements ITdcService {
/*  34 */   private final Logger log = LoggerFactory.getLogger(com.anunciadores.service.TdcServiceImpl.class);
    
    @Autowired
    private ITdcRepo TdcRepository;
    
    @Autowired
    private IPersonaRepo personaRepository;
    
    @Autowired
    private UtilDate utilDate;
  
    
    @Transactional
    public Tdc save(Date fechaCreacion, Tdc tdc) {
      try {
/*  49 */       List<Tdc> tdcDto = this.TdcRepository.findAllByDateAndPersona(fechaCreacion, tdc.getIdPersona());
/*  50 */       if (tdcDto.isEmpty() || tdcDto.size() <= 0) {
/*  51 */         Tdc tdc1 = (Tdc)this.TdcRepository.save(tdc);
        } else {
/*  53 */         throw new RuntimeException();
        }
      
/*  56 */     } catch (Exception e) {
/*  57 */       e.printStackTrace();
/*  58 */       throw new RuntimeException();
      } 
      
/*  61 */     return tdc;
    }
  
    
    public Tdc saveTcdImage(String urlCloudflare, Integer idPersona) {
      try {
/*  67 */       Tdc saveTcd = new Tdc();
/*  68 */       Date sqlDate = Date.valueOf(this.utilDate.cargarFechaBogotaConParametro("yyyy-MM-dd"));
/*  69 */       saveTcd.setFechaCreacion(sqlDate);
/*  70 */       saveTcd.setIdPersona(idPersona.intValue());
/*  71 */       saveTcd.setUrlImage(urlCloudflare);
        
/*  73 */       return (Tdc)this.TdcRepository.save(saveTcd);
      }
/*  75 */     catch (Exception e) {
/*  76 */       e.printStackTrace();
/*  77 */       throw new RuntimeException(e);
      } 
    }
  
    
    public Tdc getById(int id) {
/*  83 */     Optional<Tdc> tdsDto = this.TdcRepository.findById(Integer.valueOf(id));
/*  84 */     if (tdsDto.isPresent()) {
/*  85 */       return tdsDto.get();
      }
/*  87 */     return new Tdc();
    }
  
    
    public Tdc getTdcById(int id) {
/*  92 */     Tdc tcdDto = (Tdc)this.TdcRepository.getById(Integer.valueOf(1));
/*  93 */     return tcdDto;
    }
  
    
    public List<TdcDto> getAll() {
/*  98 */     List<TdcDto> listaDto = new ArrayList<>();
/*  99 */     List<Tdc> lisTdc = this.TdcRepository.findAll();
/* 100 */     lisTdc.forEach(tdc -> listaDto.add(mapTdcDto(tdc)));
/* 101 */     return listaDto;
    }
  
    
    public List<TdcDto> getTdcByFecha(Date fecha) {
/* 106 */     List<TdcDto> listaDto = new ArrayList<>();
/* 107 */     List<Tdc> lisTdc = this.TdcRepository.findAllByDate(fecha);
/* 108 */     lisTdc.forEach(tdc -> listaDto.add(mapTdcDto(tdc)));
/* 109 */     return listaDto;
    }
  
    
    public boolean getTdcByFechaAndPersona(Date fecha, int idPersona) {
      try {
/* 115 */       this.log.info("fecha a buscar: " + fecha);
/* 116 */       this.log.info("idPersona a buscar: " + idPersona);
/* 117 */       List<Tdc> cantidadTdc = this.TdcRepository.findAllByDateAndPersona(fecha, idPersona);
/* 118 */       this.log.info("cantidadTdc: " + cantidadTdc);
/* 119 */       if (cantidadTdc.size() <= 0) {
/* 120 */         return true;
        }
/* 122 */     } catch (Exception e) {
/* 123 */       e.printStackTrace();
      } 
/* 125 */     return false;
    }
  
    
    public List<TdcReporteDto> findAllBetweenDates(Date fechaStart, Date fechaEnd) throws ParseException {
/* 130 */     List<TdcReporteDto> listareporte = new ArrayList<>();
  
      
/* 133 */     List<Object> objects = this.TdcRepository.findAllBetweenDates(fechaStart, fechaEnd);
/* 134 */     for (int j = 0; j < objects.size(); j++) {
/* 135 */       Object[] object = (Object[])objects.get(j);
/* 136 */       TdcReporteDto dto = new TdcReporteDto();
/* 137 */       dto.setNombre(object[0].toString());
/* 138 */       dto.setCantidadEntregados(Integer.parseInt(object[1].toString()));
/* 139 */       dto.setIdPersona(Integer.parseInt(object[2].toString()));
/* 140 */       dto.setPorcentajeCumplimiento(calcularPorcentajeCumplimiento(Integer.parseInt(object[1].toString())));
/* 141 */       listareporte.add(dto);
      } 
      
/* 144 */     return listareporte;
    }
  
    
    public TdcReporteDto findAllBetweenDatesAndPerson(Integer idPersona) throws ParseException {
/* 149 */     TdcReporteDto dto = new TdcReporteDto();
      
/* 151 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 152 */     String dateBog = this.utilDate.cargarFechaBogotaConParametro("yyyy-MM-dd");
/* 153 */     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
/* 154 */     LocalDate ld = LocalDate.parse(dateBog, dtf);
      
/* 156 */     int monthDays = ld.lengthOfMonth();
/* 157 */     int year = ld.getYear();
/* 158 */     int month = ld.getMonthValue();
      
/* 160 */     String fechainicial = "" + year + "-" + year + "-1";
/* 161 */     String fechaFinal = "" + year + "-" + year + "-" + month;
      
/* 163 */     Date date1 = sdf.parse(fechainicial);
/* 164 */     Date date2 = sdf.parse(fechaFinal);
      
/* 166 */     Optional<Object> objects = this.TdcRepository.findAllBetweenDatesAndPerson(date1, date2, idPersona);
      
/* 168 */     Object[] object = (Object[])objects.get();
/* 169 */     if (object[0] != null) {
        
/* 171 */       dto.setNombre(object[0].toString());
/* 172 */       dto.setCantidadEntregados(Integer.parseInt(object[1].toString()));
/* 173 */       dto.setIdPersona(Integer.parseInt(object[2].toString()));
/* 174 */       dto.setPorcentajeCumplimiento(calcularPorcentajeCumplimiento(Integer.parseInt(object[1].toString())));
      } 
      
/* 177 */     return dto;
    }
  
  
    
    private Double calcularPorcentajeCumplimiento(int cantidadRegistros) throws ParseException {
/* 183 */     String dateBog = this.utilDate.cargarFechaBogotaConParametro("yyyy-MM-dd");
/* 184 */     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
/* 185 */     LocalDate ld = LocalDate.parse(dateBog, dtf);
      
/* 187 */     int diaDelMes = ld.getDayOfMonth();
  
  
  
  
  
      
/* 194 */     return Double.valueOf(cantidadRegistros / diaDelMes * 100.0D);
    }
  
  
  
  
    
    private TdcDto mapTdcDto(Tdc tdc) {
/* 202 */     TdcDto dto = new TdcDto();
/* 203 */     dto.setId(tdc.getId());
/* 204 */     dto.setTdc(tdc.getTdc());
/* 205 */     dto.setFechaCreacion(tdc.getFechaCreacion());
/* 206 */     dto.setNombredocumento(tdc.getNombredocumento());
/* 207 */     dto.setUrlImage(tdc.getUrlImage());
      try {
/* 209 */       dto.setPersona(this.personaRepository.findById(Integer.valueOf(tdc.getIdPersona())).get());
/* 210 */     } catch (Exception e) {
/* 211 */       e.printStackTrace();
      } 
  
      
/* 215 */     return dto;
    }
  
    
    public List<TdcDto> findAllBetweenDatesByPersona(Date fechaStart, Date fechaEnd, int idPersona) {
/* 220 */     List<TdcDto> listaDto = new ArrayList<>();
  
      
/* 223 */     List<Tdc> listaTdcPersona = this.TdcRepository.findAllBetweenDatesByPersona(fechaStart, fechaEnd, idPersona);
/* 224 */     listaTdcPersona.forEach(tdc -> listaDto.add(mapTdcDto(tdc)));
/* 225 */     return listaDto;
    }
  
    
    public List<TdcDto> findAlltcdByPersona(int idPersona) throws ParseException {
/* 230 */     List<TdcDto> listaDto = new ArrayList<>();
      
/* 232 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 233 */     String dateBog = this.utilDate.cargarFechaBogotaConParametro("yyyy-MM-dd");
/* 234 */     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
/* 235 */     LocalDate ld = LocalDate.parse(dateBog, dtf);
      
/* 237 */     int monthDays = ld.lengthOfMonth();
/* 238 */     int year = ld.getYear();
/* 239 */     int month = ld.getMonthValue();
      
/* 241 */     String fechainicial = "" + year + "-" + year + "-1";
/* 242 */     String fechaFinal = "" + year + "-" + year + "-" + month;
      
/* 244 */     Date date1 = sdf.parse(fechainicial);
/* 245 */     Date date2 = sdf.parse(fechaFinal);
  
  
  
      
/* 250 */     List<Tdc> listaTdcPersona = this.TdcRepository.findAllBetweenDatesByPersona(date1, date2, idPersona);
/* 251 */     listaTdcPersona.forEach(tdc -> listaDto.add(mapTdcDto(tdc)));
/* 252 */     return listaDto;
    }
  
    
    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
/* 257 */     BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, 1);
/* 258 */     Graphics2D graphics2D = resizedImage.createGraphics();
/* 259 */     graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
/* 260 */     graphics2D.dispose();
/* 261 */     return resizedImage;
    }
  
  
    
    @Scheduled(fixedRate = 21600000L)
    public void buscarTDCRepetidos() throws ParseException {
/* 268 */     Date fechaactual = this.utilDate.cargarfechaActualBogotaDate();
      
/* 270 */     List<Persona> listP = this.personaRepository.findUsuarios();
/* 271 */     for (Persona p : listP) {
/* 272 */       List<Tdc> tdcPersona = this.TdcRepository.findAllByDateAndPersona(fechaactual, p.getId().intValue());
/* 273 */       if (tdcPersona.size() > 1)
/* 274 */         for (int i = 1; i < tdcPersona.size(); i++)
/* 275 */           this.TdcRepository.delete(tdcPersona.get(i));  
      } 
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\TdcServiceImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */