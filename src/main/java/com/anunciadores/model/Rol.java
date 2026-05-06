/*    */ package  com.anunciadores.model;
/*    */ 
/*    */ import com.sun.istack.NotNull;
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Basic;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
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
/*    */ @Table(name = "rol")
/*    */ public class Rol
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @Basic(optional = false)
/*    */   @Column(name = "id")
/*    */   @NotNull
/*    */   private int id;
/*    */   @Column(name = "descripcion_rol")
/*    */   private String descripcion;
/*    */   
/*    */   public int getId() {
/* 37 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 41 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getDescripcion() {
/* 45 */     return this.descripcion;
/*    */   }
/*    */   
/*    */   public void setDescripcion(String descripcion) {
/* 49 */     this.descripcion = descripcion;
/*    */   }
/*    */   
/*    */   public Rol(int id, String descripcion) {
/* 53 */     this.id = id;
/* 54 */     this.descripcion = descripcion;
/*    */   }
/*    */   
/*    */   public Rol() {}
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\Rol.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */