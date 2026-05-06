/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.model.Persona;
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MensajesDTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int id;
/*    */   private String descripcion;
/*    */   private Persona remitente;
/*    */   private Persona destinatario;
/*    */   private Date fechaRegistro;
/*    */   private String fechaMostrar;
/*    */   private boolean activo;
/*    */   
/*    */   public int getId() {
/* 41 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 45 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getDescripcion() {
/* 49 */     return this.descripcion;
/*    */   }
/*    */   
/*    */   public void setDescripcion(String descripcion) {
/* 53 */     this.descripcion = descripcion;
/*    */   }
/*    */   
/*    */   public Persona getRemitente() {
/* 57 */     return this.remitente;
/*    */   }
/*    */   
/*    */   public void setRemitente(Persona remitente) {
/* 61 */     this.remitente = remitente;
/*    */   }
/*    */   
/*    */   public Persona getDestinatario() {
/* 65 */     return this.destinatario;
/*    */   }
/*    */   
/*    */   public void setDestinatario(Persona destinatario) {
/* 69 */     this.destinatario = destinatario;
/*    */   }
/*    */   
/*    */   public Date getFechaRegistro() {
/* 73 */     return this.fechaRegistro;
/*    */   }
/*    */   
/*    */   public void setFechaRegistro(Date fechaRegistro) {
/* 77 */     this.fechaRegistro = fechaRegistro;
/*    */   }
/*    */   
/*    */   public boolean isActivo() {
/* 81 */     return this.activo;
/*    */   }
/*    */   
/*    */   public void setActivo(boolean activo) {
/* 85 */     this.activo = activo;
/*    */   }
/*    */   
/*    */   public String getFechaMostrar() {
/* 89 */     return this.fechaMostrar;
/*    */   }
/*    */   
/*    */   public void setFechaMostrar(String fechaMostrar) {
/* 93 */     this.fechaMostrar = fechaMostrar;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\MensajesDTO.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */