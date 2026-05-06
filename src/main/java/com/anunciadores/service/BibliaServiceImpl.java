  package  com.anunciadores.service;
  
  import com.anunciadores.client.BibliaFeingClient;
  import com.anunciadores.dto.BibleFeingDto;
  import com.anunciadores.dto.BibliaDto;
  import com.anunciadores.dto.CapitulosDto;
  import com.anunciadores.dto.LibrosDto;
  import com.anunciadores.dto.VersiculoDto;
  import com.anunciadores.dto.VersiculoResponseDto;
  import com.anunciadores.dto.VersiculoSaveDto;
  import com.anunciadores.dto.VersiculosDto;
  import com.anunciadores.dto.VersionBiblesDto;
  import com.anunciadores.model.VersiculoSemanal;
  import com.anunciadores.repository.IVersiculoRepo;
  import com.anunciadores.service.interfaces.IBibliaService;
  import com.anunciadores.util.UtilDate;
  import com.fasterxml.jackson.core.JsonProcessingException;
  import com.fasterxml.jackson.databind.JsonMappingException;
  import com.fasterxml.jackson.databind.JsonNode;
  import com.fasterxml.jackson.databind.ObjectMapper;
  import com.fasterxml.jackson.databind.SerializationFeature;
  import java.text.ParseException;
  import java.text.SimpleDateFormat;
  import java.time.LocalDate;
  import java.time.format.DateTimeFormatter;
  import java.util.Collections;
  import java.util.Date;
  import java.util.Optional;
  import java.util.Random;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.http.HttpEntity;
  import org.springframework.http.HttpHeaders;
  import org.springframework.http.HttpMethod;
  import org.springframework.http.MediaType;
  import org.springframework.http.ResponseEntity;
  import org.springframework.stereotype.Service;
  import org.springframework.util.MultiValueMap;
  import org.springframework.web.client.RestTemplate;
  
  
  @Service
  public class BibliaServiceImpl
    implements IBibliaService
  {
    @Value("${token}")
    private String token;
    @Autowired
    BibliaFeingClient bibliaFeingClient;
/*  52 */   private Logger LOGGER = LoggerFactory.getLogger(com.anunciadores.service.BibliaServiceImpl.class); @Autowired
    IVersiculoRepo versiculoRepo; @Autowired
    UtilDate utilDate;
    public BibliaDto findBible(String idioma) throws JsonMappingException, JsonProcessingException {
/*  56 */     BibliaDto biblia = new BibliaDto();
/*  57 */     StringBuilder request = new StringBuilder();
/*  58 */     request.append("");
/*  59 */     request.append("?language=" + idioma);
/*  60 */     ResponseEntity<String> respuesta = consumirUrl(request.toString());
      
/*  62 */     ObjectMapper mapper = new ObjectMapper();
/*  63 */     mapper.enable(SerializationFeature.INDENT_OUTPUT);
/*  64 */     JsonNode root = mapper.readTree((String)respuesta.getBody());
/*  65 */     JsonNode data = root.path("data");
/*  66 */     JsonNode bible = data.path(0);
/*  67 */     JsonNode nombre = bible.path("name");
/*  68 */     biblia.setName(nombre.asText());
/*  69 */     JsonNode description = bible.path("description");
/*  70 */     biblia.setDescription(description.asText());
/*  71 */     JsonNode id = bible.path("id");
/*  72 */     biblia.setId(id.asText());
/*  73 */     return biblia;
    }
  
    
    public LibrosDto findBook(String idBible) throws JsonMappingException, JsonProcessingException {
/*  78 */     LibrosDto librosDto = this.bibliaFeingClient.buscarLibro(idBible, this.token);
/*  79 */     return librosDto;
    }
  
  
    
    public CapitulosDto findChapters(String idBible, String idBook) throws JsonMappingException, JsonProcessingException {
/*  85 */     CapitulosDto capitulos = this.bibliaFeingClient.buscarCapitulos(idBible, idBook, this.token);
/*  86 */     return capitulos;
    }
  
  
  
    
    public VersiculosDto findIdVerses(String idBible, String idChapter) throws JsonMappingException, JsonProcessingException {
/*  93 */     VersiculosDto versiculos = this.bibliaFeingClient.buscarVersiculos(idBible, idChapter, this.token);
/*  94 */     return versiculos;
    }
  
  
  
  
    
    public VersiculoResponseDto findVerse(String idBible, String idVerse) throws JsonMappingException, JsonProcessingException {
/* 102 */     VersiculoResponseDto versiculo = this.bibliaFeingClient.buscarVersiculo(idBible, idVerse, this.token);
/* 103 */     return versiculo;
    }
  
    
    private ResponseEntity<String> consumirUrl(String request) {
/* 108 */     RestTemplate restTemplate = new RestTemplate();
/* 109 */     HttpHeaders headers = new HttpHeaders();
/* 110 */     headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
      
/* 112 */     headers.set("api-key", this.token);
/* 113 */     HttpEntity<String> entity = new HttpEntity("body", (MultiValueMap)headers);
/* 114 */     return restTemplate.exchange(request, HttpMethod.GET, entity, String.class, new Object[0]);
    }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
    
    public VersiculoDto findVerseDay() throws JsonMappingException, JsonProcessingException {
      try {
/* 139 */       VersiculoDto pasajeError = new VersiculoDto();
/* 140 */       pasajeError.setContent("[27] Yo soy el Señor, Dios de toda la humanidad. ¿Hay algo imposible para mí?");
/* 141 */       pasajeError.setReference("Jeremías 32:27");
  
  
        
/* 145 */       return pasajeError;
/* 146 */     } catch (Exception e) {
/* 147 */       System.out.println("error de servicio de biblia");
/* 148 */       this.LOGGER.error("[findVerseDay] " + e.getMessage());
/* 149 */       e.printStackTrace();
/* 150 */       VersiculoDto pasajeError = new VersiculoDto();
/* 151 */       pasajeError.setContent("[27] Yo soy el Señor, Dios de toda la humanidad. ¿Hay algo imposible para mí?");
/* 152 */       pasajeError.setReference("Jeremías 32:27");
/* 153 */       throw new RuntimeException("[findVerseDay]" + e.getMessage());
      } 
    }
  
    
    public LibrosDto findAllBooks() throws JsonMappingException, JsonProcessingException {
/* 159 */     VersionBiblesDto versionesbiblia = this.bibliaFeingClient.buscarBiblia(this.token);
/* 160 */     LibrosDto librosDto = findBook(((BibleFeingDto)versionesbiblia.getData().get(0)).getId());
/* 161 */     return librosDto;
    }
  
    
    public VersiculoSaveDto saveVerseWeek(VersiculoSaveDto VersiculoSave) throws JsonProcessingException, ParseException {
/* 166 */     VersiculoSemanal verSemanal = new VersiculoSemanal();
/* 167 */     verSemanal.setCitaBiblica(VersiculoSave.getTitle());
/* 168 */     verSemanal.setVersiculoTexto(VersiculoSave.getMessage());
/* 169 */     verSemanal.setFechaInicio(this.utilDate.convertStringToDate(VersiculoSave.getFechaInicio()));
/* 170 */     verSemanal.setFechaFin(this.utilDate.convertStringToDate(VersiculoSave.getFechaFin()));
/* 171 */     Optional<VersiculoSemanal> verSave = Optional.of((VersiculoSemanal)this.versiculoRepo.save(verSemanal));
/* 172 */     if (verSave.isPresent()) {
/* 173 */       return VersiculoSave;
      }
/* 175 */     throw new RuntimeException();
    }
  
  
    
    public VersiculoSaveDto buscarVersiculoSemanal() throws ParseException {
/* 181 */     VersiculoSaveDto response = new VersiculoSaveDto();
/* 182 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 183 */     String fechaActual = sdf.format(this.utilDate.cargarfechaActualBogotaDate());
/* 184 */     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
/* 185 */     LocalDate ld = LocalDate.parse(fechaActual, dtf);
      
/* 187 */     StringBuilder fechaIni = new StringBuilder();
/* 188 */     StringBuilder fechaFin = new StringBuilder();
      
/* 190 */     fechaIni.append("" + ld.getYear() + "-");
/* 191 */     String mes = String.valueOf(ld.getMonthValue());
      
/* 193 */     fechaIni.append((mes.length() < 2) ? ("0" + ld.getMonthValue() + "-") : ("" + ld.getMonthValue() + "-"));
/* 194 */     String dia = String.valueOf(ld.getDayOfMonth());
/* 195 */     fechaIni.append((dia.length() < 2) ? ("0" + ld.getDayOfMonth()) : Integer.valueOf(ld.getDayOfMonth()));
      
/* 197 */     fechaFin.append("" + ld.getYear() + "-");
/* 198 */     fechaFin.append((mes.length() < 2) ? ("0" + ld.getMonthValue() + "-") : ("" + ld.getMonthValue() + "-"));
/* 199 */     String diafin = String.valueOf(ld.lengthOfMonth());
/* 200 */     fechaFin.append((diafin.length() < 2) ? ("0" + ld.lengthOfMonth()) : Integer.valueOf(ld.lengthOfMonth()));
      
/* 202 */     Date ini = this.utilDate.convertStringToDate(fechaIni.toString());
/* 203 */     Date fin = this.utilDate.convertStringToDate(fechaFin.toString());
      
      try {
/* 206 */       Optional<VersiculoSemanal> vSemanal = this.versiculoRepo.findByFechaFinBetween(ini, fin);
/* 207 */       if (vSemanal.isPresent())
        {
/* 209 */         response.setTitle(((VersiculoSemanal)vSemanal.get()).getCitaBiblica());
/* 210 */         response.setMessage(((VersiculoSemanal)vSemanal.get()).getVersiculoTexto());
/* 211 */         response.setFechaInicio(this.utilDate.convertDateToString(((VersiculoSemanal)vSemanal.get()).getFechaInicio()));
/* 212 */         response.setFechaFin(this.utilDate.convertDateToString(((VersiculoSemanal)vSemanal.get()).getFechaFin()));
        }
      
/* 215 */     } catch (Exception e) {
/* 216 */       throw e;
      } 
/* 218 */     return response;
    }
    
    public int libRamdom() {
/* 222 */     int min = 1;
/* 223 */     int max = 66;
      
/* 225 */     Random random = new Random();
      
/* 227 */     int value = random.nextInt(max + min) + min;
/* 228 */     return value;
    }
  
    
    public int Ramdom(int max) {
/* 233 */     int min = 1;
      
/* 235 */     Random random = new Random();
      
/* 237 */     int value = random.nextInt(max + min) + min;
/* 238 */     return value;
    }
  
  
    
    public VersiculoDto constuirVersiculo() {
/* 244 */     return null;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\BibliaServiceImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */