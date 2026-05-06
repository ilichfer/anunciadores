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
/*    */ public class ConsolidacionDto
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int id;
/*    */   private int idPadreEspiritual;
/*    */   private int idPersonaConsolidar;
/*    */   
/*    */   public int getId() {
/* 27 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 31 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdPadreEspiritual() {
/* 35 */     return this.idPadreEspiritual;
/*    */   }
/*    */   
/*    */   public void setIdPadreEspiritual(int idPadreEspiritual) {
/* 39 */     this.idPadreEspiritual = idPadreEspiritual;
/*    */   }
/*    */   
/*    */   public int getIdPersonaConsolidar() {
/* 43 */     return this.idPersonaConsolidar;
/*    */   }
/*    */   
/*    */   public void setIdPersonaConsolidar(int idPersonaConsolidar) {
/* 47 */     this.idPersonaConsolidar = idPersonaConsolidar;
/*    */   }
/*    */ 
/*    */   
/*    */   public ConsolidacionDto(int id, int idPadreEspiritual, int idPersonaConsolidar) {
/* 52 */     this.id = id;
/* 53 */     this.idPadreEspiritual = idPadreEspiritual;
/* 54 */     this.idPersonaConsolidar = idPersonaConsolidar;
/*    */   }
/*    */   
/*    */   public ConsolidacionDto() {}
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\ConsolidacionDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */