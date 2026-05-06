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
/*    */ public class ReportePagoDto
/*    */ {
/*    */   private int id;
/*    */   private String nombreCurso;
/*    */   private String persona;
/*    */   private int valorTotal;
/*    */   private int pago;
/*    */   private int deuda;
/*    */   
/*    */   public int getIdCurso() {
/* 25 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 29 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getValorTotal() {
/* 33 */     return this.valorTotal;
/*    */   }
/*    */   
/*    */   public void setValorTotal(int valorTotal) {
/* 37 */     this.valorTotal = valorTotal;
/*    */   }
/*    */   
/*    */   public String getNombreCurso() {
/* 41 */     return this.nombreCurso;
/*    */   }
/*    */   
/*    */   public void setNombreCurso(String nombreCurso) {
/* 45 */     this.nombreCurso = nombreCurso;
/*    */   }
/*    */   
/*    */   public String getPersona() {
/* 49 */     return this.persona;
/*    */   }
/*    */   
/*    */   public void setPersona(String persona) {
/* 53 */     this.persona = persona;
/*    */   }
/*    */   
/*    */   public int getPago() {
/* 57 */     return this.pago;
/*    */   }
/*    */   
/*    */   public void setPago(int pago) {
/* 61 */     this.pago = pago;
/*    */   }
/*    */   
/*    */   public int getDeuda() {
/* 65 */     return this.deuda;
/*    */   }
/*    */   
/*    */   public void setDeuda(int deuda) {
/* 69 */     this.deuda = deuda;
/*    */   }
/*    */ 
/*    */   
/*    */   public ReportePagoDto(int id, String nombreCurso, String persona, int valorTotal, int pago, int deuda) {
/* 74 */     this.id = id;
/* 75 */     this.nombreCurso = nombreCurso;
/* 76 */     this.persona = persona;
/* 77 */     this.valorTotal = valorTotal;
/* 78 */     this.pago = pago;
/* 79 */     this.deuda = deuda;
/*    */   }
/*    */   
/*    */   public ReportePagoDto() {}
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\ReportePagoDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */