/*    */ package  com.anunciadores.service;
/*    */ 
/*    */ import com.anunciadores.auth.dto.LoginRequest;
/*    */ import com.anunciadores.auth.dto.LoginResponse;
/*    */ import com.anunciadores.model.Persona;
/*    */ import com.anunciadores.model.RolPersona;
/*    */ import com.anunciadores.repository.IPersonaRepo;
/*    */ import com.anunciadores.repository.IRolesPersonaRepo;
/*    */ import com.anunciadores.service.JwtService;
/*    */ import java.math.BigInteger;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class AuthService
/*    */ {
/*    */   @Autowired
/*    */   private IPersonaRepo personaRepo;
/*    */   @Autowired
/*    */   private JwtService jwtService;
/*    */   @Autowired
/*    */   private IRolesPersonaRepo rolesPersonaRepo;
/*    */   
/*    */   public LoginResponse login(LoginRequest request) {
/*    */     Integer cedula;
/*    */     try {
/* 34 */       cedula = Integer.valueOf(Integer.parseInt(request.getCedula()));
/* 35 */     } catch (NumberFormatException e) {
/* 36 */       throw new RuntimeException("La cédula debe ser un número válido");
/*    */     } 
/*    */ 
/*    */     
/* 40 */     Persona persona = this.personaRepo.findByDocumento(cedula);
/* 41 */     if (persona == null) {
/* 42 */       throw new RuntimeException("Cédula o contraseña incorrectos");
/*    */     }
/*    */ 
/*    */     
/* 46 */     if (persona.getEstado() == null || !persona.getEstado().booleanValue()) {
/* 47 */       throw new RuntimeException("Tu cuenta está inactiva. Contacta al administrador.");
/*    */     }
/*    */ 
/*    */     
/* 51 */     String passwordMd5 = toMd5(request.getPassword());
/* 52 */     if (!passwordMd5.equals(persona.getPassword())) {
/* 53 */       throw new RuntimeException("Cédula o contraseña incorrectos");
/*    */     }
/*    */ 
/*    */     
/* 57 */     List<RolPersona> roldb = this.rolesPersonaRepo.findRolByidPersona(persona.getId().intValue());
/* 58 */     RolPersona rolP = roldb.get(0);
/* 59 */     String rol = (rolP.getIdRol() == 1) ? "ADMIN" : "USER";
/*    */ 
/*    */     
/* 62 */     String nombreCompleto = persona.getNombre() + " " + persona.getNombre();
/* 63 */     String token = this.jwtService.generarToken(
/* 64 */         String.valueOf(persona.getDocumento()), rol, nombreCompleto);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 70 */     return new LoginResponse(token, rol, nombreCompleto, Long.valueOf(persona.getId().longValue()));
/*    */   }
/*    */ 
/*    */   
/*    */   private String toMd5(String input) {
/*    */     try {
/* 76 */       MessageDigest md = MessageDigest.getInstance("MD5");
/* 77 */       byte[] messageDigest = md.digest(input.getBytes());
/* 78 */       BigInteger number = new BigInteger(1, messageDigest);
/* 79 */       String hash = number.toString(16);
/*    */       
/* 81 */       while (hash.length() < 32) {
/* 82 */         hash = "0" + hash;
/*    */       }
/* 84 */       return hash;
/* 85 */     } catch (NoSuchAlgorithmException e) {
/* 86 */       throw new RuntimeException("Error al procesar la contraseña");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\AuthService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */