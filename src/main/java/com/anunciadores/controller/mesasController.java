/*    */ package com.anunciadores.controller;
/*    */ 
/*    */ import com.anunciadores.dto.ActividadDto;
/*    */ import com.anunciadores.model.Actividad;
/*    */ import com.anunciadores.model.Mesa;
/*    */ import com.anunciadores.model.Persona;
/*    */ import com.anunciadores.service.interfaces.IActividadService;
/*    */ import com.anunciadores.service.interfaces.IBibliaService;
/*    */ import com.anunciadores.service.interfaces.IMesasService;
/*    */ import com.anunciadores.service.interfaces.IPersonaService;
/*    */ import com.fasterxml.jackson.core.JsonProcessingException;
/*    */ import com.fasterxml.jackson.databind.JsonMappingException;
/*    */ import java.text.ParseException;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.http.ResponseEntity;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.ModelAttribute;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping
/*    */ public class mesasController
/*    */ {
/*    */   @Autowired
/*    */   private IActividadService actividadService;
/*    */   @Autowired
/*    */   private IMesasService mesasService;
/*    */   @Autowired
/*    */   private IBibliaService bibliaService;
/*    */   @Autowired
/*    */   private IPersonaService personaService;
/*    */   List<Actividad> ActividadesList;
/*    */   List<Persona> personasList;
/*    */   List<Mesa> mesasList;
/*    */   
/*    */   @GetMapping({"/buscarMesa/{id}"})
/*    */   public ResponseEntity<Object> getProductoById(@PathVariable Integer id) {
/* 58 */     return ResponseEntity.ok(this.actividadService.findActividadById(id));
/*    */   }
/*    */ 
/*    */   
/*    */   @PostMapping({"/saveMesa"})
/*    */   public String save(@ModelAttribute ActividadDto actividadDto, HttpServletResponse response, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
/* 64 */     String url = "redirect:/404.html";
/* 65 */     if (actividadDto != null) {
/* 66 */       Actividad actividad = this.actividadService.save(actividadDto);
/* 67 */       this.ActividadesList = this.actividadService.listarActiviades();
/* 68 */       model.addAttribute("actividades", this.ActividadesList);
/* 69 */       url = "actividades";
/*    */     } 
/* 71 */     return url;
/*    */   }
/*    */ 
/*    */   
/*    */   @GetMapping({"/eliminarMesa"})
/*    */   public String deleteProductoById(@ModelAttribute Mesa mesa, HttpServletResponse response, Model model) {
/* 77 */     this.mesasService.delete(mesa);
/*    */     
/* 79 */     return "mesas";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @GetMapping({"/personasMesa"})
/*    */   public String personasMesa(@ModelAttribute Mesa mesa, HttpServletResponse response, Model model) {
/* 86 */     this.personasList = this.mesasService.BuscarPersonasSinMesa(mesa);
/* 87 */     model.addAttribute("personas", this.personasList);
/* 88 */     return "personasMesa";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @GetMapping({"/mesasActividad"})
/*    */   public String mesasActividad(@ModelAttribute ActividadDto actividadDto, Model model) {
/* 95 */     this.mesasList = this.actividadService.buscarMesasActividad(actividadDto);
/* 96 */     model.addAttribute("mesas", this.mesasList);
/* 97 */     return "mesas";
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\mesasController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */