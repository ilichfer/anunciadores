/*    */ package com.anunciadores.controller;
/*    */ 
/*    */ import com.anunciadores.model.EstudioPersona;
/*    */ import com.anunciadores.model.Persona;
/*    */ import com.anunciadores.service.interfaces.IPersonaService;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.ModelAttribute;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
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
/*    */ public class EstudiosPersonaController
/*    */ {
/*    */   @Autowired
/*    */   private IPersonaService personaService;
/*    */   
/*    */   @GetMapping({"/estudioPersona"})
/*    */   public String estudioPersona(@ModelAttribute Persona persona, HttpServletResponse response, Model model) {
/* 32 */     Persona per = this.personaService.findPersonaById(persona.getId());
/* 33 */     List<EstudioPersona> estudios = this.personaService.findEstudiosPersona(per.getId().intValue());
/* 34 */     model.addAttribute("persona", per);
/* 35 */     model.addAttribute("estudios", estudios);
/* 36 */     return "estudios-personas";
/*    */   }
/*    */   
/*    */   @PostMapping({"/saveEstudio"})
/*    */   public String estudioPersona(@ModelAttribute EstudioPersona estudio, HttpServletResponse response, Model model) {
/* 41 */     this.personaService.saveEstudio(estudio);
/* 42 */     Persona per = this.personaService.findPersonaById(Integer.valueOf(estudio.getIdPersona()));
/* 43 */     List<EstudioPersona> estudios = this.personaService.findEstudiosPersona(per.getId().intValue());
/* 44 */     model.addAttribute("persona", per);
/* 45 */     model.addAttribute("estudios", estudios);
/* 46 */     return "estudios-personas";
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\EstudiosPersonaController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */