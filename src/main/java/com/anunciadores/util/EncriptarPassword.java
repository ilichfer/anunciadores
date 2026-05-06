/*   */ package  com.anunciadores.util;
/*   */ 
/*   */ import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*   */ 
/*   */ public class EncriptarPassword
/*   */ {
/*   */   public String encriptarPassword(String password) {
/* 8 */     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
/* 9 */     return encoder.encode(password);
/*   */   }
/*   */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadore\\util\EncriptarPassword.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */