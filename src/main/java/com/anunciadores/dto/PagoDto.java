/*    */ package  com.anunciadores.dto;
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
/*    */ public class PagoDto
/*    */ {
/*    */   private int idPersona;
/*    */   private int idCurso;
/*    */   private int valorTotal;
/*    */   private int valorPagado;
/*    */   
/*    */   public int getIdCurso() {
/* 24 */     return this.idCurso;
/*    */   }
/*    */   
/*    */   public void setIdCurso(int idCurso) {
/* 28 */     this.idCurso = idCurso;
/*    */   }
/*    */   
/*    */   public int getValorTotal() {
/* 32 */     return this.valorTotal;
/*    */   }
/*    */   
/*    */   public void setValorTotal(int valorTotal) {
/* 36 */     this.valorTotal = valorTotal;
/*    */   }
/*    */   
/*    */   public int getValorPagado() {
/* 40 */     return this.valorPagado;
/*    */   }
/*    */   
/*    */   public void setValorPagado(int valorPagado) {
/* 44 */     this.valorPagado = valorPagado;
/*    */   }
/*    */   
/*    */   public int getIdPersona() {
/* 48 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(int idPersona) {
/* 52 */     this.idPersona = idPersona;
/*    */   }
/*    */ 
/*    */   
/*    */   public PagoDto(int idPersona, int idCurso, int valorTotal, int valorPagado) {
/* 57 */     this.idPersona = idPersona;
/* 58 */     this.idCurso = idCurso;
/* 59 */     this.valorTotal = valorTotal;
/* 60 */     this.valorPagado = valorPagado;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\PagoDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */