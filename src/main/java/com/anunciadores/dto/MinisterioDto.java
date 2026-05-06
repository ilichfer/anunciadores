/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.dto.PosicionDto;
/*    */ import java.io.Serializable;
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
/*    */ public class MinisterioDto
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int id;
/*    */   String nombreMinisterio;
/*    */   PosicionDto posicionDto;
/*    */   
/*    */   public int getId() {
/* 26 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 30 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getNombreMinisterio() {
/* 34 */     return this.nombreMinisterio;
/*    */   }
/*    */   
/*    */   public void setNombreMinisterio(String nombreMinisterio) {
/* 38 */     this.nombreMinisterio = nombreMinisterio;
/*    */   }
/*    */   
/*    */   public PosicionDto getPosicionDto() {
/* 42 */     return this.posicionDto;
/*    */   }
/*    */   
/*    */   public void setPosicionDto(PosicionDto posicionDto) {
/* 46 */     this.posicionDto = posicionDto;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\MinisterioDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */