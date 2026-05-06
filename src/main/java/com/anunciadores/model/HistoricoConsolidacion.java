/*    */ package  com.anunciadores.model;
/*    */ 
/*    */ import com.sun.istack.NotNull;
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
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
/*    */ @Table(name = "his_consolidacion")
/*    */ public class HistoricoConsolidacion
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @Basic(optional = false)
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   @NotNull
/*    */   private int id;
/*    */   @Column(name = "id_consolidacion")
/*    */   private int idConsolidacion;
/*    */   @Column(name = "fecha_registro_consolidacion")
/*    */   private Date fechaRegistroConsolidacion;
/*    */   @Column(name = "descripcion_consolidacion")
/*    */   private String descripcionConsolidacion;
/*    */   
/*    */   public int getId() {
/* 44 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 48 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdConsolidacion() {
/* 52 */     return this.idConsolidacion;
/*    */   }
/*    */   
/*    */   public void setIdConsolidacion(int idConsolidacion) {
/* 56 */     this.idConsolidacion = idConsolidacion;
/*    */   }
/*    */   
/*    */   public Date getFechaRegistroConsolidacion() {
/* 60 */     return this.fechaRegistroConsolidacion;
/*    */   }
/*    */   
/*    */   public void setFechaRegistroConsolidacion(Date fechaRegistroConsolidacion) {
/* 64 */     this.fechaRegistroConsolidacion = fechaRegistroConsolidacion;
/*    */   }
/*    */   
/*    */   public String getDescripcionConsolidacion() {
/* 68 */     return this.descripcionConsolidacion;
/*    */   }
/*    */   
/*    */   public void setDescripcionConsolidacion(String descripcionConsolidacion) {
/* 72 */     this.descripcionConsolidacion = descripcionConsolidacion;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\HistoricoConsolidacion.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */