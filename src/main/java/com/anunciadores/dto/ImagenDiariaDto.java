/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import java.time.LocalDate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImagenDiariaDto
/*    */ {
/*    */   private int id;
/*    */   private LocalDate fechaCreacion;
/*    */   private int idPersona;
/*    */   private String tdc;
/*    */   
/*    */   public int getId() {
/* 17 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 21 */     this.id = id;
/*    */   }
/*    */   
/*    */   public LocalDate getFechaCreacion() {
/* 25 */     return this.fechaCreacion;
/*    */   }
/*    */   
/*    */   public void setFechaCreacion(LocalDate fechaCreacion) {
/* 29 */     this.fechaCreacion = fechaCreacion;
/*    */   }
/*    */   
/*    */   public int getIdPersona() {
/* 33 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(int idPersona) {
/* 37 */     this.idPersona = idPersona;
/*    */   }
/*    */   
/*    */   public String getTdc() {
/* 41 */     return this.tdc;
/*    */   }
/*    */   
/*    */   public void setTdc(String tdc) {
/* 45 */     this.tdc = tdc;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\ImagenDiariaDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */