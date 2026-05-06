  package  com.anunciadores.service;
  
  import com.anunciadores.client.TelegramClient;
  import com.anunciadores.dto.ContactDTO;
  import com.anunciadores.dto.MessageDTO;
  import com.anunciadores.dto.ResponseTelegram;
  import com.anunciadores.dto.ResultDTO;
  import com.anunciadores.dto.ServicioListResponseDto;
  import com.anunciadores.dto.ServicioResponseDto;
  import com.anunciadores.model.Coordinador;
  import com.anunciadores.model.Persona;
  import com.anunciadores.repository.IPersonaRepo;
  import com.anunciadores.service.interfaces.IServicioService;
  import com.anunciadores.service.interfaces.ITelegramService;
  import com.anunciadores.util.UtilDate;
  import com.fasterxml.jackson.core.JsonProcessingException;
  import com.fasterxml.jackson.databind.JsonMappingException;
  import java.sql.Date;
  import java.text.ParseException;
  import java.time.DayOfWeek;
  import java.time.LocalDate;
  import java.util.ArrayList;
  import java.util.Date;
  import java.util.HashMap;
  import java.util.HashSet;
  import java.util.List;
  import java.util.Map;
  import java.util.Optional;
  import java.util.Set;
  import java.util.stream.Collectors;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.scheduling.annotation.Scheduled;
  import org.springframework.stereotype.Service;
  
  @Service
  public class TelegramServiceImpl
    implements ITelegramService {
    @Autowired
    private IPersonaRepo personaRepo;
    @Autowired
    TelegramClient telegramClient;
/*  45 */   private final Logger log = LoggerFactory.getLogger(com.anunciadores.service.TelegramServiceImpl.class); @Autowired
    private IServicioService servicioService; @Autowired
    private UtilDate utilDate; @Value("${propiedad.telegram.token}")
    private String telegramToken; public List<Persona> getUpdatesWithContact() throws JsonMappingException, JsonProcessingException {
/*  49 */     List<Persona> peopleWithIdTelegram = new ArrayList<>();
/*  50 */     ResponseTelegram response = this.telegramClient.getUpdates(this.telegramToken);
/*  51 */     Set<Long> contactosUnicos = new HashSet<>();
  
  
  
  
  
  
      
/*  59 */     List<ResultDTO> actualizacionesConContacto = (List<ResultDTO>)response.getResult().stream().filter(result -> { MessageDTO message = result.getMessage(); return (message != null && message.getContact() != null); }).filter(result -> contactosUnicos.add(result.getMessage().getChat().getId())).collect(Collectors.toList());
      
/*  61 */     for (ResultDTO update : actualizacionesConContacto) {
/*  62 */       ContactDTO contact = update.getMessage().getContact();
/*  63 */       String telefonoOriginal = contact.getPhone_number();
  
        
/*  66 */       String telefonoRecortado = (telefonoOriginal.length() > 2) ? telefonoOriginal.substring(2) : telefonoOriginal;
        
/*  68 */       Long chatId = update.getMessage().getChat().getId();
/*  69 */       String nombre = update.getMessage().getFrom().getFirst_name();
        
/*  71 */       System.out.println("📞 Teléfono limpio: " + telefonoRecortado);
/*  72 */       System.out.println("👤 Nombre: " + nombre);
/*  73 */       System.out.println("💬 Chat ID: " + chatId);
/*  74 */       System.out.println("────────────────────────────");
        
/*  76 */       Optional<List<Persona>> personaTelegram = this.personaRepo.findByCelular(telefonoRecortado);
        
/*  78 */       if (personaTelegram.isPresent() && ((List)personaTelegram.get()).size() > 1) {
/*  79 */         for (Persona p : personaTelegram.get()) {
/*  80 */           System.out.println("   - Persona encontrada: ID=" + p.getId() + ", Nombre=" + p.getNombre() + ", Celular=" + p.getCelular() + ", TelegramID=" + p.getIdTelegram());
/*  81 */           p.setIdTelegram(chatId.toString());
/*  82 */           peopleWithIdTelegram.add((Persona)this.personaRepo.save(p));
          }  continue;
/*  84 */       }  if (personaTelegram.isPresent() && ((List)personaTelegram.get()).size() == 1) {
/*  85 */         ((Persona)((List<Persona>)personaTelegram.get()).get(0)).setIdTelegram(chatId.toString());
          
/*  87 */         peopleWithIdTelegram.add((Persona)this.personaRepo.save(((List<Persona>)personaTelegram.get()).get(0))); continue;
        } 
/*  89 */       System.out.println("❌ No existe");
      } 
  
      
/*  93 */     return peopleWithIdTelegram;
    }
  
    
    public List<Persona> getContactTelegram() throws JsonMappingException, JsonProcessingException {
/*  98 */     return (List<Persona>)this.personaRepo.findAll().stream().filter(p -> (p.getEstado().booleanValue() == true))
/*  99 */       .collect(Collectors.toList());
    }
  
    
    public int getUpdatesWithOutContact() throws JsonMappingException, JsonProcessingException {
/* 104 */     ResponseTelegram response = this.telegramClient.getUpdates(this.telegramToken);
/* 105 */     Set<Long> contactosUnicos = new HashSet<>();
  
  
  
  
  
  
      
/* 113 */     List<ResultDTO> actualizacionesSinDuplicados = (List<ResultDTO>)response.getResult().stream().filter(result -> { MessageDTO message = result.getMessage(); return (message != null && message.getContact() == null); }).filter(result -> contactosUnicos.add(result.getMessage().getChat().getId())).collect(Collectors.toList());
/* 114 */     int contador = 0;
  
      
/* 117 */     for (ResultDTO update : actualizacionesSinDuplicados) {
        
/* 119 */       Long idChatTelegram = update.getMessage().getChat().getId();
        
/* 121 */       Optional<List<Persona>> list = this.personaRepo.findByIdTelegram(idChatTelegram.toString());
        
/* 123 */       if (((List)list.get()).size() > 0) {
/* 124 */         System.out.println("❌ Ya tiene telegram asociado");
          continue;
        } 
/* 127 */       getContact(idChatTelegram.toString());
/* 128 */       System.out.println("✅ No tiene telegram asociado");
/* 129 */       contador++;
      } 
      
/* 132 */     return contador;
    }
  
    
    public List<ServicioResponseDto> sendNotification() throws JsonMappingException, JsonProcessingException, ParseException {
/* 137 */     List<ServicioListResponseDto> listProgramacionMinisterio = this.servicioService.findProgramacionByDateGroup(this.utilDate.cargarfechaActualBogotaDate());
/* 138 */     List<ServicioResponseDto> personasProgramandas = new ArrayList<>();
/* 139 */     if (listProgramacionMinisterio.size() > 0) {
/* 140 */       for (ServicioListResponseDto serv : listProgramacionMinisterio) {
/* 141 */         for (ServicioResponseDto s : serv.getServicioDTO()) {
/* 142 */           if (s.getIdNotificacionTelegram() != null) {
/* 143 */             personasProgramandas.add(s);
              
/* 145 */             Map<String, Object> body = new HashMap<>();
/* 146 */             body.put("chat_id", Long.valueOf(Long.parseLong(s.getIdNotificacionTelegram())));
/* 147 */             body.put("text", "Hola <b>" + s.getEncargado() + "</b>,\neste es un mensaje automático para recordarte que tienes servicio el día <b>" + serv.getFechaServcio() + "</b> en el ministerio de <b>" + serv.getNombreMinisterio() + "</b> en la posición <b>" + s.getPosicion() + "</b>.\n¡Gracias por tu servicio!");
/* 148 */             body.put("disable_notification", Boolean.valueOf(false));
              
/* 150 */             String respuesta = this.telegramClient.enviarMensajeTelegram(this.telegramToken, body, "HTML");
/* 151 */             System.out.println("📨 Telegram respondió: " + respuesta); continue;
            } 
/* 153 */           personasProgramandas.add(s);
/* 154 */           System.out.println(s.getEncargado() + "   ❌ No tiene telegram asociado");
          } 
        } 
        
/* 158 */       Coordinador cor = this.servicioService.findCoordinador(listProgramacionMinisterio);
/* 159 */       if (cor != null && cor.getPersona() != null && cor.getPersona().getIdTelegram() != null) {
/* 160 */         Map<String, Object> body = new HashMap<>();
/* 161 */         body.put("chat_id", Long.valueOf(Long.parseLong(cor.getPersona().getIdTelegram())));
/* 162 */         body.put("text", "Hola <b>" + cor.getPersona().getNombre() + "</b>,\neste es un mensaje automático para recordarte que tienes servicio el día <b>" + this.utilDate.convertDateToString(cor.getFechaServicio()) + "</b> en la posición de <b> Coordinador </b>.\n¡Gracias por tu servicio!");
/* 163 */         body.put("disable_notification", Boolean.valueOf(false));
          
/* 165 */         String respuesta = this.telegramClient.enviarMensajeTelegram(this.telegramToken, body, "HTML");
/* 166 */         System.out.println("📨 Telegram respondió: " + respuesta);
        } 
      } 
/* 169 */     return personasProgramandas;
    }
  
  
  
    
    public ResponseTelegram getContact(String idTelegram) throws JsonMappingException, JsonProcessingException {
/* 176 */     if (!this.personaRepo.findFirstByIdTelegram(idTelegram).isPresent() && !idTelegram.equals("7726706876")) {
/* 177 */       Map<String, Object> body = new HashMap<>();
/* 178 */       body.put("chat_id", idTelegram);
/* 179 */       body.put("text", "Hola, Dios te bendiga.\nnos encantaría poder estar en contacto contigo para compartir información y apoyarte en lo que necesites.\n👇 ¿Podrías compartirnos tu contacto telefonico dando click en el boton de abajo? 👇 ");
  
        
/* 182 */       body.put("disable_notification", Boolean.valueOf(false));
  
        
/* 185 */       Map<String, Object> botonContacto = new HashMap<>();
/* 186 */       botonContacto.put("text", "Click aqui para compartir tu telefono");
/* 187 */       botonContacto.put("request_contact", Boolean.valueOf(true));
  
        
/* 190 */       List<Map<String, Object>> filaBotones = new ArrayList<>();
/* 191 */       filaBotones.add(botonContacto);
  
        
/* 194 */       List<List<Map<String, Object>>> teclado = new ArrayList<>();
/* 195 */       teclado.add(filaBotones);
  
        
/* 198 */       Map<String, Object> replyMarkup = new HashMap<>();
/* 199 */       replyMarkup.put("keyboard", teclado);
/* 200 */       replyMarkup.put("resize_keyboard", Boolean.valueOf(true));
/* 201 */       replyMarkup.put("one_time_keyboard", Boolean.valueOf(true));
        
/* 203 */       body.put("reply_markup", replyMarkup);
        
/* 205 */       String respuesta = this.telegramClient.enviarMensajeTelegram(this.telegramToken, body, "HTML");
/* 206 */       System.out.println("📨 Telegram respondió: " + respuesta);
      } 
/* 208 */     return null;
    }
  
  
  
    
    @Scheduled(cron = "0 0 18 * * *", zone = "America/Bogota")
    public ResponseTelegram sendMessage() throws JsonMappingException, JsonProcessingException, ParseException {
/* 216 */     LocalDate fechaActual = LocalDate.now();
  
      
/* 219 */     DayOfWeek diaDeLaSemana = fechaActual.getDayOfWeek();
  
      
/* 222 */     System.out.println("El día actual es: " + diaDeLaSemana);
  
      
/* 225 */     if (diaDeLaSemana == DayOfWeek.THURSDAY || diaDeLaSemana == DayOfWeek.SATURDAY) {
/* 226 */       fechaActual = fechaActual.plusDays(1L);
/* 227 */       Date fechaAumentada = Date.valueOf(fechaActual);
/* 228 */       List<ServicioListResponseDto> listProgramacionMinisterio = this.servicioService.findProgramacionByDateGroup(fechaAumentada);
        
/* 230 */       if (listProgramacionMinisterio.size() > 0) {
/* 231 */         for (ServicioListResponseDto serv : listProgramacionMinisterio) {
/* 232 */           for (ServicioResponseDto s : serv.getServicioDTO()) {
/* 233 */             if (s.getIdNotificacionTelegram() != null) {
/* 234 */               Map<String, Object> body = new HashMap<>();
/* 235 */               body.put("chat_id", Long.valueOf(Long.parseLong(s.getIdNotificacionTelegram())));
/* 236 */               body.put("text", "Hola <b>" + s.getEncargado() + "</b>,\n este es un mensaje automático para recordarte que tienes servicio el día <b>" + serv.getFechaServcio() + "</b> en el ministerio de <b>" + serv.getNombreMinisterio() + "</b> en la posición <b>" + s.getPosicion() + "</b>. \n ¡Gracias por tu servicio!");
/* 237 */               body.put("disable_notification", Boolean.valueOf(false));
                
/* 239 */               String respuesta = this.telegramClient.enviarMensajeTelegram(this.telegramToken, body, "HTML");
/* 240 */               System.out.println("📨 Telegram respondió: " + respuesta); continue;
              } 
/* 242 */             System.out.println(s.getEncargado() + "   ❌ No tiene telegram asociado");
            } 
          } 
          
/* 246 */         Coordinador cor = this.servicioService.findCoordinador(listProgramacionMinisterio);
/* 247 */         if (cor != null && cor.getPersona() != null && cor.getPersona().getIdTelegram() != null) {
/* 248 */           Map<String, Object> body = new HashMap<>();
/* 249 */           body.put("chat_id", Long.valueOf(Long.parseLong(cor.getPersona().getIdTelegram())));
/* 250 */           body.put("text", "Hola <b>" + cor.getPersona().getNombre() + "</b>,\n este es un mensaje automático para recordarte que tienes servicio el día <b>" + this.utilDate.convertDateToString(cor.getFechaServicio()) + "</b> en la posición de <b> Coordinador </b>. \n¡Gracias por tu servicio!");
/* 251 */           body.put("disable_notification", Boolean.valueOf(false));
            
/* 253 */           String respuesta = this.telegramClient.enviarMensajeTelegram(this.telegramToken, body, "HTML");
/* 254 */           System.out.println("📨 Telegram respondió: " + respuesta);
          } 
        } 
      } else {
/* 258 */       System.out.println("Hoy no ejecutar ña tarea.");
      } 
  
  
      
/* 263 */     return null;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\TelegramServiceImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */