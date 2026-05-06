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
/*    */ @Table(name = "parametros")
/*    */ public class ParametrosCombos
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "descripcion")
/*    */   private String descripcion;
/*    */   @Column(name = "grupo")
/*    */   private String grupo;
/*    */   
/*    */   public int getId() {
/* 38 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 42 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getDescripcion() {
/* 46 */     return this.descripcion;
/*    */   }
/*    */   
/*    */   public void setDescripcion(String descripcion) {
/* 50 */     this.descripcion = descripcion;
/*    */   }
/*    */   
/*    */   public String getGrupo() {
/* 54 */     return this.grupo;
/*    */   }
/*    */   
/*    */   public void setGrupo(String grupo) {
/* 58 */     this.grupo = grupo;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\ParametrosCombos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */