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
/*    */ @Entity
/*    */ @Table(name = "posiciones_ministerios")
/*    */ public class PosicionesMinisterio
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "id_ministerio")
/*    */   private int idMinisterio;
/*    */   @Column(name = "nombre_posicion")
/*    */   private String nombrePosicion;
/*    */   
/*    */   public String toString() {
/* 38 */     return "PosicionesMinisterio{id=" + this.id + ", idMinisterio=" + this.idMinisterio + ", nombrePosicion='" + this.nombrePosicion + "'}";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getId() {
/* 46 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 50 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getNombrePosicion() {
/* 54 */     return this.nombrePosicion;
/*    */   }
/*    */   
/*    */   public void setNombrePosicion(String nombrePosicion) {
/* 58 */     this.nombrePosicion = nombrePosicion;
/*    */   }
/*    */   
/*    */   public int getIdMinisterio() {
/* 62 */     return this.idMinisterio;
/*    */   }
/*    */   
/*    */   public void setIdMinisterio(int idMinisterio) {
/* 66 */     this.idMinisterio = idMinisterio;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\PosicionesMinisterio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */