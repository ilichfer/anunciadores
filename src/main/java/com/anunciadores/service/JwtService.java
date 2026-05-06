/*    */ package  com.anunciadores.service;
/*    */ 
/*    */ import io.jsonwebtoken.Claims;
/*    */ import io.jsonwebtoken.JwtException;
/*    */ import io.jsonwebtoken.Jwts;
/*    */ import io.jsonwebtoken.SignatureAlgorithm;
/*    */ import java.util.Date;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class JwtService
/*    */ {
/*    */   private static final String SECRET = "adcIglesiaDigitalSecretKey2026!!";
/*    */   private static final long EXPIRATION_MS = 28800000L;
/*    */   
/*    */   public String generarToken(String cedula, String rol, String nombre) {
/* 22 */     return Jwts.builder()
/* 23 */       .setSubject(cedula)
/* 24 */       .claim("rol", rol)
/* 25 */       .claim("nombre", nombre)
/* 26 */       .setIssuedAt(new Date())
/* 27 */       .setExpiration(new Date(System.currentTimeMillis() + 28800000L))
/* 28 */       .signWith(SignatureAlgorithm.HS256, "adcIglesiaDigitalSecretKey2026!!".getBytes())
/* 29 */       .compact();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCedulaDesdeToken(String token) {
/* 34 */     return getClaims(token).getSubject();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean esTokenValido(String token) {
/*    */     try {
/* 40 */       Claims claims = getClaims(token); return 
/* 41 */         !claims.getExpiration().before(new Date());
/* 42 */     } catch (JwtException|IllegalArgumentException e) {
/* 43 */       return false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private Claims getClaims(String token) {
/* 49 */     return (Claims)Jwts.parser()
/* 50 */       .setSigningKey("adcIglesiaDigitalSecretKey2026!!".getBytes())
/* 51 */       .parseClaimsJws(token)
/* 52 */       .getBody();
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\JwtService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */