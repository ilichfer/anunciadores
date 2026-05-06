/*    */ package  com.anunciadores.model;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
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
/*    */ @Entity
/*    */ @Table(name = "inscripcion_consolidacion")
/*    */ public class inscripcionConsolidacion
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "id_padre_espiritual")
/*    */   private int idPadreEspiritual;
/*    */   @Column(name = "id_persona_consolidar")
/*    */   private int idPersonaConsolidar;
/*    */   
/*    */   public int getId() {
/* 37 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 41 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdPadreEspiritual() {
/* 45 */     return this.idPadreEspiritual;
/*    */   }
/*    */   
/*    */   public void setIdPadreEspiritual(int idPadreEspiritual) {
/* 49 */     this.idPadreEspiritual = idPadreEspiritual;
/*    */   }
/*    */   
/*    */   public int getIdPersonaConsolidar() {
/* 53 */     return this.idPersonaConsolidar;
/*    */   }
/*    */   
/*    */   public void setIdPersonaConsolidar(int idPersonaConsolidar) {
/* 57 */     this.idPersonaConsolidar = idPersonaConsolidar;
/*    */   }
/*    */ 
/*    */   
/*    */   public inscripcionConsolidacion(int id, int idPadreEspiritual, int idPersonaConsolidar) {
/* 62 */     this.id = id;
/* 63 */     this.idPadreEspiritual = idPadreEspiritual;
/* 64 */     this.idPersonaConsolidar = idPersonaConsolidar;
/*    */   }
/*    */   
/*    */   public inscripcionConsolidacion() {}
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\inscripcionConsolidacion.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */