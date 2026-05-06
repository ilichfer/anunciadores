/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class RolPersonaDto
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int id;
/*    */   private int idPersona;
/*    */   private int idRol;
/*    */   
/*    */   public int getId() {
/* 31 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 35 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdPersona() {
/* 39 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(int idPersona) {
/* 43 */     this.idPersona = idPersona;
/*    */   }
/*    */   
/*    */   public int getIdRol() {
/* 47 */     return this.idRol;
/*    */   }
/*    */   
/*    */   public void setIdRol(int idRol) {
/* 51 */     this.idRol = idRol;
/*    */   }
/*    */ 
/*    */   
/*    */   public RolPersonaDto(int id, int idPersona, int idRol) {
/* 56 */     this.id = id;
/* 57 */     this.idPersona = idPersona;
/* 58 */     this.idRol = idRol;
/*    */   }
/*    */   
/*    */   public RolPersonaDto() {}
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\RolPersonaDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */