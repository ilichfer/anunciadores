/*    */ package com.anunciadores.controller;
/*    */ import com.anunciadores.dto.CapitulosDto;
/*    */ import com.anunciadores.dto.VersiculoDto;
/*    */ import com.anunciadores.dto.VersiculoResponseDto;
/*    */ import com.anunciadores.dto.VersiculoSaveDto;
/*    */ import com.anunciadores.dto.VersiculosDto;
/*    */ import com.anunciadores.service.interfaces.IBibliaService;
/*    */ import com.fasterxml.jackson.core.JsonProcessingException;
/*    */ import com.fasterxml.jackson.databind.JsonMappingException;
/*    */ import java.text.ParseException;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.http.HttpStatus;
/*    */ import org.springframework.http.ResponseEntity;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.ModelAttribute;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestParam;
/*    */ 
/*    */ @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
/*    */ @Controller
/*    */ @RequestMapping
/*    */ public class BibliaRestController {
/*    */   @GetMapping({"/buscarVersiculo"})
/*    */   public String buscarVersiculo(Model model) throws JsonProcessingException {
/* 30 */     model.addAttribute("libros", this.bibliaService.findAllBooks().getData());
/* 31 */     model.addAttribute("capitulos", null);
/* 32 */     model.addAttribute("versiculos", null);
/* 33 */     return "register-versiculo";
/*    */   } @Autowired
/*    */   private IBibliaService bibliaService;
/*    */   @GetMapping({"/capitulosFind"})
/*    */   public ResponseEntity<List<CapituloDto>> capitulos(@RequestParam String lib) throws JsonMappingException, JsonProcessingException {
/* 38 */     CapitulosDto caps = this.bibliaService.findChapters("592420522e16049f-01", lib);
/* 39 */     return new ResponseEntity(caps.getData(), null, HttpStatus.ACCEPTED);
/*    */   }
/*    */   
/*    */   @GetMapping({"/versiculosrests"})
/*    */   public ResponseEntity<List<VersiculoDto>> versiculos(@RequestParam String capSelecionado) throws JsonMappingException, JsonProcessingException {
/* 44 */     VersiculosDto versiculosEncontrado = this.bibliaService.findIdVerses("592420522e16049f-01", capSelecionado);
/* 45 */     return new ResponseEntity(versiculosEncontrado.getData(), null, HttpStatus.ACCEPTED);
/*    */   }
/*    */   
/*    */   @GetMapping({"/versiculorests"})
/*    */   public ResponseEntity<VersiculoDto> versiculo(@RequestParam String verSelecionado) throws JsonMappingException, JsonProcessingException {
/* 50 */     VersiculoResponseDto versiculosEncontrado = this.bibliaService.findVerse("592420522e16049f-01", verSelecionado);
/* 51 */     return new ResponseEntity(versiculosEncontrado.getData(), null, HttpStatus.ACCEPTED);
/*    */   } @GetMapping({"/capitulosWeb"})
/*    */   public String capitulosWeb(@RequestParam String lib, Model model) throws JsonProcessingException {
/* 54 */     model.addAttribute("libros", this.bibliaService.findAllBooks().getData());
/* 55 */     CapitulosDto caps = this.bibliaService.findChapters("592420522e16049f-01", lib);
/* 56 */     model.addAttribute("capitulos", caps.getData());
/* 57 */     model.addAttribute("versiculos", null);
/* 58 */     return "register-versiculo";
/*    */   }
/*    */   
/*    */   @PostMapping({"/saveVersiculo"})
/*    */   public String save(@ModelAttribute VersiculoSaveDto versiculoSave, HttpServletResponse response, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
/* 63 */     String url = "register-versiculo";
/* 64 */     model.addAttribute("msj", "su versiculo ha sido guardado exitosamente ");
/* 65 */     if (versiculoSave != null) {
/* 66 */       VersiculoSaveDto ver = this.bibliaService.saveVerseWeek(versiculoSave);
/* 67 */       if (ver != null && ver.getTitle() != null)
/*    */       {
/* 69 */         model.addAttribute("libros", this.bibliaService.findAllBooks().getData());
/*    */       }
/*    */     } 
/* 72 */     return url;
/*    */   }
/*    */   
/*    */   @GetMapping({"/versiculoSemanal"})
/*    */   public String versiculoSemanal(@RequestParam String lib, Model model) throws JsonProcessingException, ParseException {
/* 77 */     this.bibliaService.buscarVersiculoSemanal();
/* 78 */     model.addAttribute("libros", this.bibliaService.findAllBooks().getData());
/* 79 */     CapitulosDto caps = this.bibliaService.findChapters("592420522e16049f-01", lib);
/* 80 */     model.addAttribute("capitulos", caps.getData());
/* 81 */     model.addAttribute("versiculos", null);
/* 82 */     return "register-versiculo";
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\BibliaRestController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */