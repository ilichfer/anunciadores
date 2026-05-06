/*    */ package  com.anunciadores.model;
/*    */ 
/*    */ import com.anunciadores.model.ParamMenu;
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.CascadeType;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.OneToOne;
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
/*    */ @Table(name = "permisos_menu")
/*    */ public class PermisosMenu
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "id_persona")
/*    */   private int idPersona;
/*    */   @Column(name = "nombre_boton_menu")
/*    */   public String nombreBotonMenu;
/*    */   @Column(name = "estado")
/*    */   public String estado;
/*    */   @OneToOne(cascade = {CascadeType.ALL})
/*    */   @JoinColumn(name = "id_menu", referencedColumnName = "id")
/*    */   private ParamMenu menu;
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
/*    */   public String getNombreBotonMenu() {
/* 63 */     return this.nombreBotonMenu;
/*    */   }
/*    */   
/*    */   public void setNombreBotonMenu(String nombreBotonMenu) {
/* 67 */     this.nombreBotonMenu = nombreBotonMenu;
/*    */   }
/*    */   
/*    */   public String getEstado() {
/* 71 */     return this.estado;
/*    */   }
/*    */   
/*    */   public void setEstado(String estado) {
/* 75 */     this.estado = estado;
/*    */   }
/*    */   
/*    */   public ParamMenu getMenu() {
/* 79 */     return this.menu;
/*    */   }
/*    */   
/*    */   public void setMenu(ParamMenu menu) {
/* 83 */     this.menu = menu;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\PermisosMenu.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */