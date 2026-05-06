/*    */ package com.anunciadores.controller;
/*    */ 
/*    */ import com.anunciadores.client.PruebaFeingClient;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
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
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping
/*    */ public class PruebaController
/*    */ {
/*    */   @Autowired
/*    */   PruebaFeingClient pruebaFeingClient;
/*    */   
/*    */   @GetMapping({"/consultar"})
/*    */   public String getProductoById(@RequestParam int idTdc, Model model) {
/* 44 */     Object[] pru = this.pruebaFeingClient.buscarUrl("Bearer 00be24a7-f16c-36b2-bc3a-c5a2661b3c43", "2021-01-16T15:00:00", "Dispensacion", "SIE000000618");
/*    */     
/* 46 */     System.out.println(pru);
/* 47 */     return "visualizarTDC";
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\PruebaController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */