/*    */ package  com.anunciadores.model;
/*    */ 
/*    */ import com.anunciadores.model.ParamSubMenu;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import javax.persistence.CascadeType;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.OneToMany;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "param_menu")
/*    */ public class ParamMenu
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "nombre_boton_menu")
/*    */   public String nombreBotonMenu;
/*    */   @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "idMenu")
/*    */   private List<ParamSubMenu> subMenu;
/*    */   
/*    */   public int getId() {
/* 38 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 42 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getNombreBotonMenu() {
/* 46 */     return this.nombreBotonMenu;
/*    */   }
/*    */   
/*    */   public void setNombreBotonMenu(String nombreBotonMenu) {
/* 50 */     this.nombreBotonMenu = nombreBotonMenu;
/*    */   }
/*    */   
/*    */   public List<ParamSubMenu> getSubMenu() {
/* 54 */     return this.subMenu;
/*    */   }
/*    */   
/*    */   public void setSubMenu(List<ParamSubMenu> subMenu) {
/* 58 */     this.subMenu = subMenu;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\ParamMenu.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */