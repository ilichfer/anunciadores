/*    */ package  com.anunciadores.util;
/*    */ 
/*    */ import io.jsonwebtoken.Claims;
/*    */ import io.jsonwebtoken.JwtBuilder;
/*    */ import io.jsonwebtoken.Jwts;
/*    */ import io.jsonwebtoken.SignatureAlgorithm;
/*    */ import java.security.Key;
/*    */ import java.util.Date;
/*    */ import javax.crypto.spec.SecretKeySpec;
/*    */ import javax.xml.bind.DatatypeConverter;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Value;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class JWTUtil
/*    */ {
/*    */   @Value("${security.jwt.secret}")
/*    */   private String key;
/*    */   @Value("${security.jwt.issuer}")
/*    */   private String issuer;
/*    */   @Value("${security.jwt.ttlMillis}")
/*    */   private long ttlMillis;
/* 32 */   private final Logger log = LoggerFactory.getLogger(com.anunciadores.util.JWTUtil.class);
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
/*    */   public String create(String id, String subject) {
/* 44 */     SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
/*    */     
/* 46 */     long nowMillis = System.currentTimeMillis();
/* 47 */     Date now = new Date(nowMillis);
/*    */ 
/*    */     
/* 50 */     byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(this.key);
/* 51 */     Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
/*    */ 
/*    */ 
/*    */     
/* 55 */     JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(this.issuer).signWith(signatureAlgorithm, signingKey);
/*    */     
/* 57 */     if (this.ttlMillis >= 0L) {
/* 58 */       long expMillis = nowMillis + this.ttlMillis;
/* 59 */       Date exp = new Date(expMillis);
/* 60 */       builder.setExpiration(exp);
/*    */     } 
/*    */ 
/*    */     
/* 64 */     return builder.compact();
/*    */   }
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
/*    */   public String getValue(String jwt) {
/* 77 */     Claims claims = (Claims)Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(this.key)).parseClaimsJws(jwt).getBody();
/*    */     
/* 79 */     return claims.getSubject();
/*    */   }
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
/*    */   public String getKey(String jwt) {
/* 92 */     Claims claims = (Claims)Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(this.key)).parseClaimsJws(jwt).getBody();
/*    */     
/* 94 */     return claims.getId();
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadore\\util\JWTUtil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */