/*    */ package com.anunciadores.controller;
/*    */ 
/*    */ import com.anunciadores.dto.CapitulosDto;
/*    */ import com.anunciadores.dto.CoordinadorDTO;
/*    */ import com.anunciadores.model.Pago;
/*    */ import com.anunciadores.service.interfaces.IBibliaService;
/*    */ import com.anunciadores.util.UtilDate;
/*    */ import com.fasterxml.jackson.core.JsonProcessingException;
/*    */ import com.fasterxml.jackson.databind.JsonMappingException;
/*    */ import java.sql.Date;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.http.HttpStatus;
/*    */ import org.springframework.http.ResponseEntity;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.CrossOrigin;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestParam;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
/*    */ @Controller
/*    */ @RequestMapping
/*    */ public class VersiculoController
/*    */ {
/*    */   @Autowired
/*    */   private IBibliaService bibliaService;
/*    */   @Autowired
/*    */   private UtilDate utilDate;
/*    */   List<Pago> pagoList;
/* 39 */   private final Logger log = LoggerFactory.getLogger(com.anunciadores.controller.VersiculoController.class);
/*    */ 
/*    */   
/*    */   @PostMapping({"/guardarVersiculo"})
/*    */   public String guardarVersiculo(@RequestParam Date fechaInicio, @RequestParam Date fechaFin, Model model) {
/* 44 */     if (fechaInicio != null && fechaFin != null) {
/* 45 */       List<CoordinadorDTO> reportes = new ArrayList<>();
/* 46 */       model.addAttribute("reportes", reportes);
/* 47 */       model.addAttribute("fechaInicio", fechaInicio);
/* 48 */       model.addAttribute("fechaFin", fechaFin);
/*    */     } 
/* 50 */     return "register-versiculo";
/*    */   }
/*    */   
/*    */   @GetMapping({"/capitulosrest/"})
/*    */   public ResponseEntity<CapitulosDto> capitulos(@RequestParam String lib) throws JsonMappingException, JsonProcessingException {
/* 55 */     CapitulosDto caps = this.bibliaService.findChapters("592420522e16049f-01", lib);
/* 56 */     return new ResponseEntity(caps, null, HttpStatus.ACCEPTED);
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\VersiculoController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */