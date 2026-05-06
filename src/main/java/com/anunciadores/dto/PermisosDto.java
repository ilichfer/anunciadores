/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
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
/*    */ public class PermisosDto
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private List<Integer> id;
/*    */   public List<String> nombreBotonMenu;
/*    */   public List<Boolean> estado;
/*    */   
/*    */   public List<Integer> getId() {
/* 31 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(List<Integer> id) {
/* 35 */     this.id = id;
/*    */   }
/*    */   
/*    */   public List<String> getNombreBotonMenu() {
/* 39 */     return this.nombreBotonMenu;
/*    */   }
/*    */   
/*    */   public void setNombreBotonMenu(List<String> nombreBotonMenu) {
/* 43 */     this.nombreBotonMenu = nombreBotonMenu;
/*    */   }
/*    */   
/*    */   public List<Boolean> getEstado() {
/* 47 */     return this.estado;
/*    */   }
/*    */   
/*    */   public void setEstado(List<Boolean> estado) {
/* 51 */     this.estado = estado;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\PermisosDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */