/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.dto.PersonaDto;
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
/*    */ public class PosicionDto
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int id;
/*    */   String nombrePosicion;
/*    */   PersonaDto personaDto;
/*    */   String asistencia;
/*    */   private int idMinisterio;
/*    */   
/*    */   public int getId() {
/* 28 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 32 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getNombrePosicion() {
/* 36 */     return this.nombrePosicion;
/*    */   }
/*    */   
/*    */   public void setNombrePosicion(String nombrePosicion) {
/* 40 */     this.nombrePosicion = nombrePosicion;
/*    */   }
/*    */   
/*    */   public PersonaDto getPersonaDto() {
/* 44 */     return this.personaDto;
/*    */   }
/*    */   
/*    */   public void setPersonaDto(PersonaDto personaDto) {
/* 48 */     this.personaDto = personaDto;
/*    */   }
/*    */   
/*    */   public int getIdMinisterio() {
/* 52 */     return this.idMinisterio;
/*    */   }
/*    */   
/*    */   public void setIdMinisterio(int idMinisterio) {
/* 56 */     this.idMinisterio = idMinisterio;
/*    */   }
/*    */   
/*    */   public String getAsistencia() {
/* 60 */     return this.asistencia;
/*    */   }
/*    */   
/*    */   public void setAsistencia(String asistencia) {
/* 64 */     this.asistencia = asistencia;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\PosicionDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */