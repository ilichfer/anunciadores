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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TdcDto
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int id;
/*    */   String tdc;
/*    */   Persona persona;
/*    */   Date fechaCreacion;
/*    */   String nombredocumento;
/*    */   String urlImage;
/*    */   
/*    */   public String getUrlImage() {
/* 39 */     return this.urlImage;
/*    */   }
/*    */   
/*    */   public void setUrlImage(String urlImage) {
/* 43 */     this.urlImage = urlImage;
/*    */   }
/*    */   
/*    */   public String getTdc() {
/* 47 */     return this.tdc;
/*    */   }
/*    */   
/*    */   public void setTdc(String tdc) {
/* 51 */     this.tdc = tdc;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 55 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 59 */     this.id = id;
/*    */   }
/*    */   
/*    */   public Persona getPersona() {
/* 63 */     return this.persona;
/*    */   }
/*    */   
/*    */   public void setPersona(Persona persona) {
/* 67 */     this.persona = persona;
/*    */   }
/*    */   
/*    */   public Date getFechaCreacion() {
/* 71 */     return this.fechaCreacion;
/*    */   }
/*    */   
/*    */   public void setFechaCreacion(Date fechaCreacion) {
/* 75 */     this.fechaCreacion = fechaCreacion;
/*    */   }
/*    */   
/*    */   public String getNombredocumento() {
/* 79 */     return this.nombredocumento;
/*    */   }
/*    */   
/*    */   public void setNombredocumento(String nombredocumento) {
/* 83 */     this.nombredocumento = nombredocumento;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\TdcDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */