/*    */ package com.anunciadores.controller;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.springframework.http.HttpEntity;
/*    */ import org.springframework.http.HttpHeaders;
/*    */ import org.springframework.http.HttpStatus;
/*    */ import org.springframework.http.MediaType;
/*    */ import org.springframework.http.ResponseEntity;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.util.MultiValueMap;
/*    */ import org.springframework.web.bind.annotation.CrossOrigin;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestBody;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.client.RestTemplate;
/*    */ 
/*    */ 
/*    */ 
/*    */ @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
/*    */ @Controller
/*    */ @RequestMapping
/*    */ public class WhatsappController
/*    */ {
/*    */   private static final String ACCESS_TOKEN = "TU_ACCESS_TOKEN";
/*    */   private static final String PHONE_NUMBER_ID = "102796769513879";
/*    */   private static final String API_URL = "https://graph.facebook.com/v18.0/102796769513879/messages";
/*    */   
/*    */   @PostMapping({"/send"})
/*    */   public ResponseEntity<String> sendNotification(@RequestBody Map<String, String> request) {
/* 31 */     String phoneNumber = request.get("phone");
/*    */ 
/*    */     
/* 34 */     RestTemplate restTemplate = new RestTemplate();
/*    */ 
/*    */     
/* 37 */     HttpHeaders headers = new HttpHeaders();
/* 38 */     headers.setContentType(MediaType.APPLICATION_JSON);
/* 39 */     headers.set("Authorization", "Bearer TU_ACCESS_TOKEN");
/*    */ 
/*    */     
/* 42 */     Map<String, Object> body = new HashMap<>();
/* 43 */     body.put("messaging_product", "whatsapp");
/* 44 */     body.put("to", phoneNumber);
/* 45 */     body.put("type", "template");
/*    */     
/* 47 */     Map<String, Object> template = new HashMap<>();
/* 48 */     template.put("name", "hello_world");
/*    */     
/* 50 */     Map<String, String> language = new HashMap<>();
/* 51 */     language.put("code", "en_us");
/* 52 */     template.put("language", language);
/*    */     
/* 54 */     body.put("template", template);
/*    */ 
/*    */     
/*    */     try {
/* 58 */       HttpEntity<Map<String, Object>> entity = new HttpEntity(body, (MultiValueMap)headers);
/* 59 */       ResponseEntity<String> response = restTemplate.postForEntity("https://graph.facebook.com/v18.0/102796769513879/messages", entity, String.class, new Object[0]);
/*    */       
/* 61 */       return ResponseEntity.ok("Enviado con éxito: " + (String)response.getBody());
/* 62 */     } catch (Exception e) {
/* 63 */       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
/* 64 */         .body("Error al conectar con Meta: " + e.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\WhatsappController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */