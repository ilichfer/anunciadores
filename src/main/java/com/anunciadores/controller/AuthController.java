/*    */ package com.anunciadores.controller;
/*    */ 
/*    */ import com.anunciadores.auth.dto.LoginRequest;
/*    */ import com.anunciadores.auth.dto.LoginResponse;
/*    */ import com.anunciadores.service.AuthService;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.http.ResponseEntity;
/*    */ import org.springframework.web.bind.annotation.CrossOrigin;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestBody;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ @RestController
/*    */ @RequestMapping({"/api/auth"})
/*    */ @CrossOrigin(origins = {"*"})
/*    */ public class AuthController {
/*    */   @Autowired
/*    */   private AuthService authService;
/*    */   
/*    */   @PostMapping({"/loginReact"})
/*    */   public ResponseEntity<?> login(@RequestBody LoginRequest request) {
/*    */     try {
/* 25 */       LoginResponse response = this.authService.login(request);
/* 26 */       return ResponseEntity.ok(response);
/*    */     }
/* 28 */     catch (RuntimeException e) {
/* 29 */       return 
/* 30 */         ResponseEntity.status(401)
/* 31 */         .body(Map.of("message", e.getMessage()));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\AuthController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */