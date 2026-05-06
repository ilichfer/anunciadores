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
/*    */ @Table(name = "estudio_persona")
/*    */ public class EstudioPersona
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
/*    */   @Column(name = "fecha_realizacion")
/*    */   private String fechaRealizacion;
/*    */   @Column(name = "nombre_estudio")
/*    */   private String nombreEstudio;
/*    */   
/*    */   public int getId() {
/* 43 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 47 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdPersona() {
/* 51 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(int idPersona) {
/* 55 */     this.idPersona = idPersona;
/*    */   }
/*    */   
/*    */   public String getFechaRealizacion() {
/* 59 */     return this.fechaRealizacion;
/*    */   }
/*    */   
/*    */   public void setFechaRealizacion(String fechaRealizacion) {
/* 63 */     this.fechaRealizacion = fechaRealizacion;
/*    */   }
/*    */   
/*    */   public String getNombreEstudio() {
/* 67 */     return this.nombreEstudio;
/*    */   }
/*    */   
/*    */   public void setNombreEstudio(String nombreEstudio) {
/* 71 */     this.nombreEstudio = nombreEstudio;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\EstudioPersona.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */