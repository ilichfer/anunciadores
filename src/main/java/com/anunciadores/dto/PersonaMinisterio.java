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
/*    */ 
/*    */ public class PersonaMinisterio
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int id;
/*    */   private List<Integer> idPersona;
/*    */   private int idMinisterio;
/*    */   private List<String> selecionado;
/*    */   private boolean aceptarTerminos;
/*    */   private List<Long> categoriasSeleccionadas;
/*    */   
/*    */   public List<Long> getCategoriasSeleccionadas() {
/* 35 */     return this.categoriasSeleccionadas;
/*    */   }
/*    */   
/*    */   public void setCategoriasSeleccionadas(List<Long> categoriasSeleccionadas) {
/* 39 */     this.categoriasSeleccionadas = categoriasSeleccionadas;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAceptarTerminos() {
/* 44 */     return this.aceptarTerminos;
/*    */   }
/*    */   
/*    */   public void setAceptarTerminos(boolean aceptarTerminos) {
/* 48 */     this.aceptarTerminos = aceptarTerminos;
/*    */   }
/*    */   
/*    */   public List<Integer> getIdPersona() {
/* 52 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(List<Integer> idPersona) {
/* 56 */     this.idPersona = idPersona;
/*    */   }
/*    */   
/*    */   public int getIdMinisterio() {
/* 60 */     return this.idMinisterio;
/*    */   }
/*    */   
/*    */   public void setIdMinisterio(int idMinisterio) {
/* 64 */     this.idMinisterio = idMinisterio;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 68 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 72 */     this.id = id;
/*    */   }
/*    */   
/*    */   public List<String> getSelecionado() {
/* 76 */     return this.selecionado;
/*    */   }
/*    */   
/*    */   public void setSelecionado(List<String> selecionado) {
/* 80 */     this.selecionado = selecionado;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\PersonaMinisterio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */