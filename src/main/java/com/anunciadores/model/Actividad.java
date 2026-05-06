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
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "actividades")
/*    */ public class Actividad
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "nombre")
/*    */   private String nombreActividad;
/*    */   @Column(name = "fecha")
/*    */   private String fecha;
/*    */   @Column(name = "cantidad_mesas")
/*    */   private int cantidadMesas;
/*    */   
/*    */   public int getId() {
/* 50 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 54 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getNombreActividad() {
/* 58 */     return this.nombreActividad;
/*    */   }
/*    */   
/*    */   public void setNombreActividad(String nombreActividad) {
/* 62 */     this.nombreActividad = nombreActividad;
/*    */   }
/*    */   
/*    */   public String getFecha() {
/* 66 */     return this.fecha;
/*    */   }
/*    */   
/*    */   public void setFecha(String fecha) {
/* 70 */     this.fecha = fecha;
/*    */   }
/*    */   
/*    */   public int getCantidadMesas() {
/* 74 */     return this.cantidadMesas;
/*    */   }
/*    */   
/*    */   public void setCantidadMesas(int cantidadMesas) {
/* 78 */     this.cantidadMesas = cantidadMesas;
/*    */   }
/*    */   
/*    */   public static long getSerialversionuid() {
/* 82 */     return 1L;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\Actividad.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */