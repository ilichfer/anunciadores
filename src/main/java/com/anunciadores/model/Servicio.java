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
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "servicio")
/*    */ public class Servicio
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "id_persona")
/*    */   private int idPersona;
/*    */   @Column(name = "id_posicion")
/*    */   private int idPosicion;
/*    */   @Column(name = "id_ministerios")
/*    */   private int idMinisterio;
/*    */   @Column(name = "fecha_servicio")
/*    */   Date fechaServicio;
/*    */   @Column(name = "asistencia")
/*    */   String asistencia;
/*    */   
/*    */   public int getId() {
/* 47 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 51 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdPersona() {
/* 55 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(int idPersona) {
/* 59 */     this.idPersona = idPersona;
/*    */   }
/*    */   
/*    */   public int getIdPosicion() {
/* 63 */     return this.idPosicion;
/*    */   }
/*    */   
/*    */   public void setIdPosicion(int idPosicion) {
/* 67 */     this.idPosicion = idPosicion;
/*    */   }
/*    */   
/*    */   public int getIdMinisterio() {
/* 71 */     return this.idMinisterio;
/*    */   }
/*    */   
/*    */   public void setIdMinisterio(int idMinisterio) {
/* 75 */     this.idMinisterio = idMinisterio;
/*    */   }
/*    */   
/*    */   public Date getFechaServicio() {
/* 79 */     return this.fechaServicio;
/*    */   }
/*    */   
/*    */   public void setFechaServicio(Date fechaServicio) {
/* 83 */     this.fechaServicio = fechaServicio;
/*    */   }
/*    */   
/*    */   public String getAsistencia() {
/* 87 */     return this.asistencia;
/*    */   }
/*    */   
/*    */   public void setAsistencia(String asistencia) {
/* 91 */     this.asistencia = asistencia;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\Servicio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */