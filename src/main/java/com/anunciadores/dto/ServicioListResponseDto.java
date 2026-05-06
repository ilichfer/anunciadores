/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.dto.ServicioResponseDto;
/*    */ import java.io.Serializable;
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
/*    */ public class ServicioListResponseDto
/*    */   implements Serializable
/*    */ {
/*    */   private List<ServicioResponseDto> servicioDTO;
/*    */   private String fechaServcio;
/*    */   private String nombreMinisterio;
/*    */   private Integer idMinisterio;
/*    */   private Integer tamanoLista;
/*    */   
/*    */   public List<ServicioResponseDto> getServicioDTO() {
/* 28 */     return this.servicioDTO;
/*    */   }
/*    */   
/*    */   public void setServicioDTO(List<ServicioResponseDto> servicioDTO) {
/* 32 */     this.servicioDTO = servicioDTO;
/*    */   }
/*    */   
/*    */   public String getFechaServcio() {
/* 36 */     return this.fechaServcio;
/*    */   }
/*    */   
/*    */   public void setFechaServcio(String fechaServcio) {
/* 40 */     this.fechaServcio = fechaServcio;
/*    */   }
/*    */   
/*    */   public String getNombreMinisterio() {
/* 44 */     return this.nombreMinisterio;
/*    */   }
/*    */   
/*    */   public void setNombreMinisterio(String nombreMinisterio) {
/* 48 */     this.nombreMinisterio = nombreMinisterio;
/*    */   }
/*    */   
/*    */   public Integer getIdMinisterio() {
/* 52 */     return this.idMinisterio;
/*    */   }
/*    */   
/*    */   public void setIdMinisterio(Integer idMinisterio) {
/* 56 */     this.idMinisterio = idMinisterio;
/*    */   }
/*    */   
/*    */   public Integer getTamanoLista() {
/* 60 */     return this.tamanoLista;
/*    */   }
/*    */   
/*    */   public void setTamanoLista(Integer tamanoLista) {
/* 64 */     this.tamanoLista = tamanoLista;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\ServicioListResponseDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */