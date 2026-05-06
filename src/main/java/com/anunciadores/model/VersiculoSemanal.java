/*    */ package  com.anunciadores.model;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
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
/*    */ @Entity
/*    */ @Table(name = "versiculo_semanal")
/*    */ public class VersiculoSemanal
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "fecha_inicio")
/*    */   public Date fechaInicio;
/*    */   @Column(name = "fecha_fin")
/*    */   public Date fechaFin;
/*    */   @Column(name = "versiculo_texto")
/*    */   public String versiculoTexto;
/*    */   @Column(name = "cita_biblica")
/*    */   public String citaBiblica;
/*    */   
/*    */   public int getId() {
/* 43 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 47 */     this.id = id;
/*    */   }
/*    */   
/*    */   public Date getFechaInicio() {
/* 51 */     return this.fechaInicio;
/*    */   }
/*    */   
/*    */   public void setFechaInicio(Date fechaInicio) {
/* 55 */     this.fechaInicio = fechaInicio;
/*    */   }
/*    */   
/*    */   public Date getFechaFin() {
/* 59 */     return this.fechaFin;
/*    */   }
/*    */   
/*    */   public void setFechaFin(Date fechaFin) {
/* 63 */     this.fechaFin = fechaFin;
/*    */   }
/*    */   
/*    */   public String getVersiculoTexto() {
/* 67 */     return this.versiculoTexto;
/*    */   }
/*    */   
/*    */   public void setVersiculoTexto(String versiculoTexto) {
/* 71 */     this.versiculoTexto = versiculoTexto;
/*    */   }
/*    */   
/*    */   public String getCitaBiblica() {
/* 75 */     return this.citaBiblica;
/*    */   }
/*    */   
/*    */   public void setCitaBiblica(String citaBiblica) {
/* 79 */     this.citaBiblica = citaBiblica;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\VersiculoSemanal.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */