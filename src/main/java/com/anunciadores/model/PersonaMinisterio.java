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
/*    */ @Entity
/*    */ @Table(name = "persona_ministerio")
/*    */ public class PersonaMinisterio
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "id_persona")
/*    */   private int idPersona;
/*    */   @Column(name = "id_ministerio")
/*    */   private int idMinisterio;
/*    */   
/*    */   public int getIdPersona() {
/* 39 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(int idPersona) {
/* 43 */     this.idPersona = idPersona;
/*    */   }
/*    */   
/*    */   public int getIdMinisterio() {
/* 47 */     return this.idMinisterio;
/*    */   }
/*    */   
/*    */   public void setIdMinisterio(int idMinisterio) {
/* 51 */     this.idMinisterio = idMinisterio;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 55 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 59 */     this.id = id;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\PersonaMinisterio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */