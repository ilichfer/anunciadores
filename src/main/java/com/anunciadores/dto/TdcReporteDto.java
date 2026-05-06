/*    */ package  com.anunciadores.dto;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TdcReporteDto
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int idPersona;
/*    */   private int cantidadEntregados;
/*    */   private String nombre;
/*    */   private Double porcentajeCumplimiento;
/*    */   
/*    */   public int getCantidadEntregados() {
/* 29 */     return this.cantidadEntregados;
/*    */   }
/*    */   
/*    */   public void setCantidadEntregados(int cantidadEntregados) {
/* 33 */     this.cantidadEntregados = cantidadEntregados;
/*    */   }
/*    */   
/*    */   public String getNombre() {
/* 37 */     return this.nombre;
/*    */   }
/*    */   
/*    */   public void setNombre(String nombre) {
/* 41 */     this.nombre = nombre;
/*    */   }
/*    */   
/*    */   public int getIdPersona() {
/* 45 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(int idPersona) {
/* 49 */     this.idPersona = idPersona;
/*    */   }
/*    */   
/*    */   public Double getPorcentajeCumplimiento() {
/* 53 */     return this.porcentajeCumplimiento;
/*    */   }
/*    */   
/*    */   public void setPorcentajeCumplimiento(Double porcentajeCumplimiento) {
/* 57 */     this.porcentajeCumplimiento = porcentajeCumplimiento;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\TdcReporteDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */