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
/*    */ @Entity
/*    */ @Table(name = "param_submenu")
/*    */ public class ParamSubMenu
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "idMenu")
/*    */   private int idMenu;
/*    */   @Column(name = "etiqueta")
/*    */   public String etiqueta;
/*    */   
/*    */   public int getId() {
/* 37 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 41 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdMenu() {
/* 45 */     return this.idMenu;
/*    */   }
/*    */   
/*    */   public void setIdMenu(int idMenu) {
/* 49 */     this.idMenu = idMenu;
/*    */   }
/*    */   
/*    */   public String getEtiqueta() {
/* 53 */     return this.etiqueta;
/*    */   }
/*    */   
/*    */   public void setEtiqueta(String etiqueta) {
/* 57 */     this.etiqueta = etiqueta;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\ParamSubMenu.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */