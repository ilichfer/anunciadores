/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.model.Persona;
/*    */ import java.io.Serializable;
/*    */ import java.sql.Date;
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
/*    */ public class CoordinadorDTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int id;
/*    */   private int idPersona;
/*    */   private Date fechaServcio;
/*    */   private String fechaString;
/*    */   public Date fechaServicio;
/*    */   public String notasServicio;
/*    */   private Persona persona;
/*    */   
/*    */   public int getIdPersona() {
/* 37 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(int idPersona) {
/* 41 */     this.idPersona = idPersona;
/*    */   }
/*    */   
/*    */   public Date getFechaServcio() {
/* 45 */     return this.fechaServcio;
/*    */   }
/*    */   
/*    */   public void setFechaServcio(Date fechaServcio) {
/* 49 */     this.fechaServcio = fechaServcio;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 53 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 57 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getFechaString() {
/* 61 */     return this.fechaString;
/*    */   }
/*    */   
/*    */   public void setFechaString(String fechaString) {
/* 65 */     this.fechaString = fechaString;
/*    */   }
/*    */   
/*    */   public Date getFechaServicio() {
/* 69 */     return this.fechaServicio;
/*    */   }
/*    */   
/*    */   public void setFechaServicio(Date fechaServicio) {
/* 73 */     this.fechaServicio = fechaServicio;
/*    */   }
/*    */   
/*    */   public String getNotasServicio() {
/* 77 */     return this.notasServicio;
/*    */   }
/*    */   
/*    */   public void setNotasServicio(String notasServicio) {
/* 81 */     this.notasServicio = notasServicio;
/*    */   }
/*    */   
/*    */   public Persona getPersona() {
/* 85 */     return this.persona;
/*    */   }
/*    */   
/*    */   public void setPersona(Persona persona) {
/* 89 */     this.persona = persona;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\CoordinadorDTO.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */