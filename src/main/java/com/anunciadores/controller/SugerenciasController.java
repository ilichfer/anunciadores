/*    */ package com.anunciadores.controller;
/*    */ 
/*    */ import com.anunciadores.model.Sugerencia;
/*    */ import com.anunciadores.service.interfaces.ISugerenciaService;
/*    */ import com.fasterxml.jackson.core.JsonProcessingException;
/*    */ import java.text.ParseException;
/*    */ import java.time.ZoneId;
/*    */ import java.time.ZonedDateTime;
/*    */ import java.time.format.DateTimeFormatter;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.ModelAttribute;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestParam;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping
/*    */ public class SugerenciasController
/*    */ {
/*    */   private ISugerenciaService sugerenciaService;
/*    */   
/*    */   public SugerenciasController(ISugerenciaService sugerenciaService) {
/* 34 */     this.sugerenciaService = sugerenciaService;
/*    */   }
/*    */ 
/*    */   
/*    */   @PostMapping({"/sugerencia"})
/*    */   public String createSugerencia(@RequestParam Integer idPersonaSugerencia, Model model) throws ParseException {
/* 40 */     model.addAttribute("idPersona", idPersonaSugerencia);
/* 41 */     model.addAttribute("fechaRegistro", cargarfechaActualBogota());
/* 42 */     return "register-sugerencia";
/*    */   }
/*    */ 
/*    */   
/*    */   @PostMapping({"/saveSugerencia"})
/*    */   public String save(@ModelAttribute Sugerencia sugerencia, @RequestParam Integer idPersona, Model model) throws ParseException, JsonProcessingException {
/* 48 */     String url = "redirect:/404.html";
/* 49 */     if (sugerencia != null) {
/* 50 */       Sugerencia sugSave = this.sugerenciaService.saveSugerencia(sugerencia, idPersona);
/* 51 */       url = "redirect:/redirectDashboard";
/*    */     } 
/* 53 */     return url;
/*    */   }
/*    */ 
/*    */   
/*    */   private String cargarfechaActualBogota() throws ParseException {
/* 58 */     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
/* 59 */     ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
/* 60 */     String fechaActualStr = nowInBogota.format(formatter);
/* 61 */     return fechaActualStr;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\SugerenciasController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */