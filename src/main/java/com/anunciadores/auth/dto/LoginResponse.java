/*    */ package  com.anunciadores.auth.dto;
/*    */ 
/*    */ public class LoginResponse {
/*    */   private String token;
/*    */   private String rol;
/*    */   private String nombre;
/*    */   private Long id;
/*    */   
/*    */   public LoginResponse(String token, String rol, String nombre, Long id) {
/* 10 */     this.token = token;
/* 11 */     this.rol = rol;
/* 12 */     this.nombre = nombre;
/* 13 */     this.id = id;
/*    */   }
/*    */   
/* 16 */   public String getToken() { return this.token; }
/* 17 */   public String getRol() { return this.rol; }
/* 18 */   public String getNombre() { return this.nombre; } public Long getId() {
/* 19 */     return this.id;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\auth\dto\LoginResponse.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */