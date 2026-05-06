/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ public class inscripcionConsolidacionDTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int id;
/*    */   private int idPadreEspiritual;
/*    */   private int idPersonaConsolidar;
/*    */   
/*    */   public int getId() {
/* 15 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 19 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdPadreEspiritual() {
/* 23 */     return this.idPadreEspiritual;
/*    */   }
/*    */   
/*    */   public void setIdPadreEspiritual(int idPadreEspiritual) {
/* 27 */     this.idPadreEspiritual = idPadreEspiritual;
/*    */   }
/*    */   
/*    */   public int getIdPersonaConsolidar() {
/* 31 */     return this.idPersonaConsolidar;
/*    */   }
/*    */   
/*    */   public void setIdPersonaConsolidar(int idPersonaConsolidar) {
/* 35 */     this.idPersonaConsolidar = idPersonaConsolidar;
/*    */   }
/*    */ 
/*    */   
/*    */   public inscripcionConsolidacionDTO(int id, int idPadreEspiritual, int idPersonaConsolidar) {
/* 40 */     this.id = id;
/* 41 */     this.idPadreEspiritual = idPadreEspiritual;
/* 42 */     this.idPersonaConsolidar = idPersonaConsolidar;
/*    */   }
/*    */   
/*    */   public inscripcionConsolidacionDTO() {}
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\inscripcionConsolidacionDTO.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */