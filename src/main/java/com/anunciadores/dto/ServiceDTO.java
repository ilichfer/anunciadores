/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import java.time.LocalDate;
/*    */ 
/*    */ public class ServiceDTO
/*    */ {
/*    */   private LocalDate fechaServicio;
/*    */   private String idPersona;
/*    */   private String idPosicion;
/*    */   private String idMinisterio;
/*    */   
/*    */   public ServiceDTO() {}
/*    */   
/*    */   public ServiceDTO(LocalDate fechaServicio, String idPersona, String idPosicion, String idMinisterio) {
/* 15 */     this.fechaServicio = fechaServicio;
/* 16 */     this.idPersona = idPersona;
/* 17 */     this.idPosicion = idPosicion;
/* 18 */     this.idMinisterio = idMinisterio;
/*    */   }
/*    */ 
/*    */   
/*    */   public LocalDate getFechaServicio() {
/* 23 */     return this.fechaServicio;
/*    */   }
/*    */   
/*    */   public void setFechaServicio(LocalDate fechaServicio) {
/* 27 */     this.fechaServicio = fechaServicio;
/*    */   }
/*    */   
/*    */   public String getIdPersona() {
/* 31 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(String idPersona) {
/* 35 */     this.idPersona = idPersona;
/*    */   }
/*    */   
/*    */   public String getIdPosicion() {
/* 39 */     return this.idPosicion;
/*    */   }
/*    */   
/*    */   public void setIdPosicion(String idPosicion) {
/* 43 */     this.idPosicion = idPosicion;
/*    */   }
/*    */   
/*    */   public String getIdMinisterio() {
/* 47 */     return this.idMinisterio;
/*    */   }
/*    */   
/*    */   public void setIdMinisterio(String idMinisterio) {
/* 51 */     this.idMinisterio = idMinisterio;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\ServiceDTO.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */