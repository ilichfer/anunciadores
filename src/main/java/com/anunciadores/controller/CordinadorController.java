/*    */ package com.anunciadores.controller;
/*    */ 
/*    */ import com.anunciadores.dto.CoordinadorDTO;
/*    */ import com.anunciadores.model.Pago;
/*    */ import com.anunciadores.service.interfaces.ICordinadorService;
/*    */ import com.anunciadores.service.interfaces.ICursoService;
/*    */ import com.anunciadores.service.interfaces.IPersonaService;
/*    */ import com.anunciadores.util.UtilDate;
/*    */ import java.sql.Date;
/*    */ import java.util.List;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
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
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping
/*    */ public class CordinadorController
/*    */ {
/*    */   @Autowired
/*    */   private ICordinadorService cordinadorService;
/*    */   @Autowired
/*    */   private ICursoService cursoService;
/*    */   @Autowired
/*    */   private IPersonaService personaService;
/*    */   @Autowired
/*    */   private UtilDate utilDate;
/*    */   List<Pago> pagoList;
/* 56 */   private final Logger log = LoggerFactory.getLogger(com.anunciadores.controller.CordinadorController.class);
/*    */   @PostMapping({"/buscarInformesByRangoFecha"})
/*    */   public String buscarTdcByRangoFecha(@RequestParam Date fechaInicio, @RequestParam Date fechaFin, Model model) {
/* 59 */     if (fechaInicio != null && fechaFin != null) {
/* 60 */       List<CoordinadorDTO> reportes = this.cordinadorService.findAllBetweenDates(fechaInicio, fechaFin);
/* 61 */       model.addAttribute("reportes", reportes);
/* 62 */       model.addAttribute("fechaInicio", fechaInicio);
/* 63 */       model.addAttribute("fechaFin", fechaFin);
/*    */     } 
/* 65 */     return "reporteHisCordinador";
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\CordinadorController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */