/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.sql.Date;
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
/*    */ public class ServicioDto
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private List<String> posicion;
/*    */   private List<String> encargado;
/*    */   private List<String> asistencia;
/*    */   private Date fechaServcio;
/*    */   
/*    */   public List<String> getPosicion() {
/* 30 */     return this.posicion;
/*    */   }
/*    */   
/*    */   public void setPosicion(List<String> posicion) {
/* 34 */     this.posicion = posicion;
/*    */   }
/*    */   
/*    */   public List<String> getEncargado() {
/* 38 */     return this.encargado;
/*    */   }
/*    */   
/*    */   public void setEncargado(List<String> encargado) {
/* 42 */     this.encargado = encargado;
/*    */   }
/*    */   
/*    */   public Date getFechaServcio() {
/* 46 */     return this.fechaServcio;
/*    */   }
/*    */   
/*    */   public void setFechaServcio(Date fechaServcio) {
/* 50 */     this.fechaServcio = fechaServcio;
/*    */   }
/*    */   
/*    */   public List<String> getAsistencia() {
/* 54 */     return this.asistencia;
/*    */   }
/*    */   
/*    */   public void setAsistencia(List<String> asistencia) {
/* 58 */     this.asistencia = asistencia;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\ServicioDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */