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
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "mesa")
/*    */ public class Mesa
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @Basic(optional = false)
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   @NotNull
/*    */   private int id;
/*    */   @Column(name = "id_actividad")
/*    */   private int idActividad;
/*    */   @Column(name = "nombre_mesa")
/*    */   private String nombreMesa;
/*    */   
/*    */   public int getId() {
/* 43 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 47 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdActividad() {
/* 51 */     return this.idActividad;
/*    */   }
/*    */   
/*    */   public void setIdActividad(int idActividad) {
/* 55 */     this.idActividad = idActividad;
/*    */   }
/*    */   
/*    */   public String getNombreMesa() {
/* 59 */     return this.nombreMesa;
/*    */   }
/*    */   
/*    */   public void setNombreMesa(String nombreMesa) {
/* 63 */     this.nombreMesa = nombreMesa;
/*    */   }
/*    */ 
/*    */   
/*    */   public Mesa(int id, int idActividad, String nombreMesa) {
/* 68 */     this.id = id;
/* 69 */     this.idActividad = idActividad;
/* 70 */     this.nombreMesa = nombreMesa;
/*    */   }
/*    */   
/*    */   public Mesa() {}
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\Mesa.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */