/*    */ package  com.anunciadores.enums;
/*    */ 
/*    */ public enum EMeses {
/*  4 */   ENERO(1), FEBRERO(2), MARZO(3), ABRIL(4), MAYO(5), JUNIO(6), JULIO(7), AGOSTO(8), SEPTIEMBRE(9), OCTUBRE(10),
/*  5 */   NOVIEMBRE(11), DICIEMBRE(12);
/*    */   private final int numero;
/*    */   
/*    */   EMeses(int numero) {
/*  9 */     this.numero = numero;
/*    */   }
/*    */   
/*    */   public int retornarNumero() {
/* 13 */     return this.numero;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\enums\EMeses.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */