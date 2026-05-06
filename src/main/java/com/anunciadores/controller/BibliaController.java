/*    */ package com.anunciadores.controller;
/*    */ 
/*    */ import com.anunciadores.dto.BibliaDto;
/*    */ import com.anunciadores.dto.LibrosDto;
/*    */ import com.anunciadores.dto.VersiculoDto;
/*    */ import com.anunciadores.service.interfaces.IBibliaService;
/*    */ import com.fasterxml.jackson.core.JsonProcessingException;
/*    */ import com.fasterxml.jackson.databind.JsonMappingException;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @RestController
/*    */ @RequestMapping
/*    */ public class BibliaController
/*    */ {
/*    */   @Autowired
/*    */   private IBibliaService bibliaService;
/*    */   
/*    */   @GetMapping({"/biblia/{idioma}"})
/*    */   public String biblia(HttpServletResponse response, @PathVariable String idioma, Model model) throws JsonMappingException, JsonProcessingException {
/* 34 */     BibliaDto biblia = this.bibliaService.findBible(idioma);
/* 35 */     model.addAttribute("biblia", biblia);
/* 36 */     return "personas";
/*    */   }
/*    */ 
/*    */   
/*    */   @GetMapping({"/libros/{lib}"})
/*    */   public String libros(HttpServletResponse response, @PathVariable String lib, Model model) throws JsonMappingException, JsonProcessingException {
/* 42 */     LibrosDto libros = this.bibliaService.findBook(lib);
/* 43 */     model.addAttribute("libros", libros);
/* 44 */     return "personas";
/*    */   }
/*    */ 
/*    */   
/*    */   @GetMapping({"/capitulos/{cap}"})
/*    */   public String capitulos(HttpServletResponse response, @PathVariable String lib, Model model) throws JsonMappingException, JsonProcessingException {
/* 50 */     LibrosDto capitulos = this.bibliaService.findBook(lib);
/* 51 */     model.addAttribute("capitulos", capitulos.getData());
/* 52 */     return "personas";
/*    */   }
/*    */ 
/*    */   
/*    */   @GetMapping({"/versiculos/{ver}"})
/*    */   public String versiculos(HttpServletResponse response, @PathVariable String ver, Model model) throws JsonMappingException, JsonProcessingException {
/* 58 */     BibliaDto biblia = this.bibliaService.findBible(ver);
/* 59 */     model.addAttribute("biblia", biblia);
/* 60 */     return "personas";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @GetMapping({"/versiculoDia"})
/*    */   public void versiculoDia(HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
/* 67 */     VersiculoDto versiculo = this.bibliaService.findVerseDay();
/* 68 */     model.addAttribute("versiculo", versiculo);
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\BibliaController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */