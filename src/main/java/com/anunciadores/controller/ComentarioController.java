/*    */ package com.anunciadores.controller;
/*    */ 
/*    */ import com.anunciadores.model.Curso;
/*    */ import com.anunciadores.model.Pago;
/*    */ import com.anunciadores.service.interfaces.ICursoService;
/*    */ import com.anunciadores.service.interfaces.IPagoService;
/*    */ import java.math.BigDecimal;
/*    */ import java.text.ParseException;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
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
/*    */ @Controller
/*    */ @RequestMapping
/*    */ public class ComentarioController
/*    */ {
/*    */   @Autowired
/*    */   private IPagoService pagoService;
/*    */   @Autowired
/*    */   private ICursoService cursoService;
/*    */   List<Pago> pagoList;
/*    */   
/*    */   @GetMapping({"/listarComentarios"})
/*    */   public String listarComentarios(HttpServletResponse response, Model model) {
/* 37 */     this.pagoList = this.pagoService.findAll();
/* 38 */     model.addAttribute("cursos", this.pagoList);
/* 39 */     return "cursos";
/*    */   }
/*    */ 
/*    */   
/*    */   @GetMapping({"/buscarComentarios"})
/*    */   public String buscarComentarios(@RequestParam int idPersona, @RequestParam int idCurso, Model model) {
/* 45 */     Curso curso = this.cursoService.findCursoById(Integer.valueOf(idCurso));
/* 46 */     this.pagoList = this.pagoService.findPagosByIdCurso(idPersona, idCurso);
/* 47 */     int pagoTotal = 0;
/* 48 */     BigDecimal big = new BigDecimal(0);
/* 49 */     if (big.compareTo(BigDecimal.ZERO) == 1) {
/* 50 */       System.out.println("==================== entro a metodo zero");
/*    */     }
/*    */     
/* 53 */     for (Pago pago : this.pagoList) {
/* 54 */       pagoTotal += pago.getValor();
/*    */     }
/* 56 */     model.addAttribute("pagos", this.pagoList);
/* 57 */     model.addAttribute("curso", curso);
/* 58 */     model.addAttribute("pagoTotal", Integer.valueOf(pagoTotal));
/* 59 */     return "pagos";
/*    */   }
/*    */ 
/*    */   
/*    */   @PostMapping({"/saveComentario"})
/*    */   public String saveComentario(@ModelAttribute Pago pago) throws ParseException {
/* 65 */     String url = "redirect:/404.html";
/* 66 */     if (pago != null) {
/* 67 */       Pago pagoSave = this.pagoService.save(pago);
/* 68 */       url = "index";
/*    */     } 
/* 70 */     return url;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\ComentarioController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */