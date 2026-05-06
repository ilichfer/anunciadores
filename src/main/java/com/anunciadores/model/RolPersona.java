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
/*    */ @Table(name = "persona_rol")
/*    */ public class RolPersona
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "id_persona")
/*    */   private int idPersona;
/*    */   @Column(name = "id_rol")
/*    */   private int idRol;
/*    */   
/*    */   public int getId() {
/* 39 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 43 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdPersona() {
/* 47 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(int idPersona) {
/* 51 */     this.idPersona = idPersona;
/*    */   }
/*    */   
/*    */   public int getIdRol() {
/* 55 */     return this.idRol;
/*    */   }
/*    */   
/*    */   public void setIdRol(int idRol) {
/* 59 */     this.idRol = idRol;
/*    */   }
/*    */ 
/*    */   
/*    */   public RolPersona(int id, int idPersona, int idRol) {
/* 64 */     this.id = id;
/* 65 */     this.idPersona = idPersona;
/* 66 */     this.idRol = idRol;
/*    */   }
/*    */   
/*    */   public RolPersona() {}
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\RolPersona.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */