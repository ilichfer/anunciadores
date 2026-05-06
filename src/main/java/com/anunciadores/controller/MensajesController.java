  package com.anunciadores.controller;
  
  import com.anunciadores.dto.MensajesDTO;
  import com.anunciadores.service.interfaces.IMensajeService;
  import com.anunciadores.service.interfaces.IPersonaService;
  import com.anunciadores.util.UtilDate;
  import com.fasterxml.jackson.core.JsonProcessingException;
  import com.fasterxml.jackson.databind.JsonMappingException;
  import java.text.ParseException;
  import java.time.ZoneId;
  import java.time.ZonedDateTime;
  import java.time.format.DateTimeFormatter;
  import java.util.List;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import javax.servlet.http.HttpSession;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.ModelAttribute;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  
  
  
  
  @Controller
  @RequestMapping
  public class MensajesController
  {
    private IMensajeService mensajeService;
    @Autowired
    private IPersonaService personaService;
    @Autowired
    private UtilDate utilDate;
    
    public MensajesController(IMensajeService mensajeService) {
/*  40 */     this.mensajeService = mensajeService;
    }
  
    
    @GetMapping({"/cargarMensajes"})
    public String cargarMensajes(@RequestParam int idPersonaMensajes, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/*  46 */     String url = "bandeja-mensajes";
      
/*  48 */     List<MensajesDTO> mensajes = this.mensajeService.buscarMensaje(Integer.valueOf(idPersonaMensajes));
/*  49 */     model.addAttribute("mensajes", mensajes);
/*  50 */     model.addAttribute("idPersona", Integer.valueOf(idPersonaMensajes));
/*  51 */     return url;
    }
    
    @GetMapping({"/cargarMensajesP"})
    public String cargarMensajesP(HttpServletRequest request, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/*  56 */     String url = "bandeja-mensajes";
/*  57 */     HttpSession misession = request.getSession();
      
      try {
/*  60 */       int idpersona = ((Integer)misession.getAttribute("idPersona")).intValue();
/*  61 */       List<MensajesDTO> mensajes = this.mensajeService.buscarTodosMensaje(Integer.valueOf(idpersona));
/*  62 */       model.addAttribute("mensajes", mensajes);
/*  63 */       model.addAttribute("idPersona", Integer.valueOf(idpersona));
/*  64 */     } catch (Exception e) {
/*  65 */       return "sesionFinalizada";
      } 
      
/*  68 */     return url;
    }
    
    @GetMapping({"/leerMensaje"})
    public String leerMensaje(@RequestParam Integer idMensaje, Model model) throws ParseException {
/*  73 */     model.addAttribute("mensaje", this.mensajeService.buscarMensajeXId(idMensaje));
/*  74 */     return "lector-mensaje";
    }
  
  
    
    private String cargarfechaActualBogota() throws ParseException {
/*  80 */     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
/*  81 */     ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
/*  82 */     String fechaActualStr = nowInBogota.format(formatter);
/*  83 */     return fechaActualStr;
    }
    
    @GetMapping({"/nuevoMensaje"})
    public String nuevoMensaje(HttpServletRequest request, Model model) {
/*  88 */     HttpSession misession = request.getSession();
      try {
/*  90 */       int idpersona = ((Integer)misession.getAttribute("idPersona")).intValue();
/*  91 */       model.addAttribute("persona", this.personaService.findPersonaById(Integer.valueOf(idpersona)));
/*  92 */       model.addAttribute("personas", this.personaService.findAllUsuarios());
/*  93 */       return "nuevo-mensaje";
/*  94 */     } catch (Exception e) {
/*  95 */       return "sesionFinalizada";
      } 
    }
    
    @PostMapping({"/enviarMensaje"})
    public String enviarMensaje(@ModelAttribute MensajesDTO mensaje, @RequestParam Integer remitente, @RequestParam Integer destinatario, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
/* 101 */     String url = "bandeja-mensajes";
      try {
/* 103 */       MensajesDTO mensajeSave = null;
/* 104 */       mensaje.setActivo(true);
/* 105 */       mensaje.setFechaRegistro(this.utilDate.cargarfechaActualBogotaDate());
/* 106 */       if (destinatario.intValue() == 0) {
/* 107 */         this.mensajeService.enviarTodosMensajes(mensaje);
        } else {
/* 109 */         mensajeSave = this.mensajeService.guardarMensaje(mensaje);
        } 
        
/* 112 */       List<MensajesDTO> mensajes = this.mensajeService.buscarTodosMensaje(remitente);
/* 113 */       model.addAttribute("mensajes", mensajes);
/* 114 */       model.addAttribute("idPersona", remitente);
/* 115 */     } catch (Exception e) {
/* 116 */       return "sesionFinalizada";
      } 
/* 118 */     return url;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\MensajesController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */