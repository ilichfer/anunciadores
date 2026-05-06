/*    */ package com.anunciadores.controller;
/*    */ 
/*    */ import com.anunciadores.dto.ResponseTelegram;
/*    */ import com.anunciadores.dto.ServicioResponseDto;
/*    */ import com.anunciadores.model.Persona;
/*    */ import com.anunciadores.service.interfaces.ITelegramService;
/*    */ import com.fasterxml.jackson.core.JsonProcessingException;
/*    */ import com.fasterxml.jackson.databind.JsonMappingException;
/*    */ import java.text.ParseException;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.http.ResponseEntity;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.CrossOrigin;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestParam;
/*    */ 
/*    */ 
/*    */ 
/*    */ @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
/*    */ @Controller
/*    */ @RequestMapping
/*    */ public class TelegramController
/*    */ {
/*    */   @Autowired
/*    */   private ITelegramService telegramService;
/*    */   
/*    */   @GetMapping({"/actualizarIdNotificacionesTelegram"})
/*    */   public String actualizarIdNotificacionesTelegram(Model model) throws JsonMappingException, JsonProcessingException {
/* 32 */     List<Persona> personasActualizadas = this.telegramService.getUpdatesWithContact();
/* 33 */     model.addAttribute("personas", (personasActualizadas.size() > 0) ? personasActualizadas : null);
/* 34 */     model.addAttribute("notificaciones", null);
/* 35 */     model.addAttribute("msj", (personasActualizadas.size() > 0) ? null : "no se encontraron datos para actualizar en Telegram");
/* 36 */     return "personasTelegram";
/*    */   }
/*    */   
/*    */   @GetMapping({"/enviarMensajeTelegram"})
/*    */   public ResponseEntity<ResponseTelegram> enviarMensajeTelegram(@RequestParam String token, @RequestParam String mensaje) throws JsonMappingException, JsonProcessingException {
/* 41 */     ResponseTelegram responsedata = this.telegramService.getContact(mensaje);
/* 42 */     return null;
/*    */   }
/*    */   
/*    */   @GetMapping({"/solicitarContactoTelegram"})
/*    */   public String buscardatosContactoServidoresTelegram(Model model) throws JsonMappingException, JsonProcessingException {
/* 47 */     int notifiaciones = this.telegramService.getUpdatesWithOutContact();
/* 48 */     model.addAttribute("personas", null);
/* 49 */     model.addAttribute("notificaciones", null);
/* 50 */     model.addAttribute("msj", "se han solicitado el contacto a " + notifiaciones + " servidores en Telegram");
/*    */     
/* 52 */     return "personasTelegram";
/*    */   }
/*    */   
/*    */   @GetMapping({"/notificacionProgramacion"})
/*    */   public String notificacionProgramacion(Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/* 57 */     List<ServicioResponseDto> personasNotificadas = this.telegramService.sendNotification();
/* 58 */     model.addAttribute("personas", null);
/* 59 */     model.addAttribute("notificaciones", personasNotificadas);
/* 60 */     model.addAttribute("msj", null);
/*    */     
/* 62 */     return "personasTelegram";
/*    */   }
/*    */   
/*    */   @GetMapping({"/getContactBdTelegram"})
/*    */   public String getContactBdTelegram(Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/* 67 */     List<Persona> personasTelegram = this.telegramService.getContactTelegram();
/* 68 */     model.addAttribute("personas", (personasTelegram.size() > 0) ? personasTelegram : null);
/* 69 */     model.addAttribute("notificaciones", null);
/* 70 */     model.addAttribute("msj", null);
/*    */     
/* 72 */     return "personasTelegram";
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\TelegramController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */