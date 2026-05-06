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
/*    */ @Table(name = "inscripciones")
/*    */ public class Inscripciones
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "id_persona")
/*    */   private int idPersona;
/*    */   @Column(name = "id_curso")
/*    */   private int idCurso;
/*    */   
/*    */   public int getIdPersona() {
/* 46 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(int idPersona) {
/* 50 */     this.idPersona = idPersona;
/*    */   }
/*    */   
/*    */   public int getIdCurso() {
/* 54 */     return this.idCurso;
/*    */   }
/*    */   
/*    */   public void setIdCurso(int idCurso) {
/* 58 */     this.idCurso = idCurso;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\Inscripciones.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */