/*    */ package  com.anunciadores.model;
/*    */ 
/*    */ import com.sun.istack.NotNull;
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Basic;
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
/*    */ @Table(name = "inscripcion_actividades")
/*    */ public class InscripcionActividad
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @Basic(optional = false)
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   @NotNull
/*    */   private int id;
/*    */   @Column(name = "id_persona")
/*    */   private int idPersona;
/*    */   @Column(name = "id_actividad")
/*    */   private int idActividad;
/*    */   
/*    */   public int getId() {
/* 41 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 45 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdPersona() {
/* 49 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(int idPersona) {
/* 53 */     this.idPersona = idPersona;
/*    */   }
/*    */   
/*    */   public int getIdActividad() {
/* 57 */     return this.idActividad;
/*    */   }
/*    */   
/*    */   public void setIdActividad(int idActividad) {
/* 61 */     this.idActividad = idActividad;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\InscripcionActividad.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */