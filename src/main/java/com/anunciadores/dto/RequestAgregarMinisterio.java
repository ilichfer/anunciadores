/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.dto.PersonaMinisterio;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RequestAgregarMinisterio
/*    */ {
/*    */   public List<PersonaMinisterio> getListaSeleccionados() {
/*  9 */     return this.listaSeleccionados;
/*    */   }
/*    */   private List<PersonaMinisterio> listaSeleccionados;
/*    */   public void setListaSeleccionados(List<PersonaMinisterio> listaSeleccionados) {
/* 13 */     this.listaSeleccionados = listaSeleccionados;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\RequestAgregarMinisterio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */