/*    */ package  com.anunciadores.mapper;
/*    */ 
/*    */ import com.anunciadores.dto.MensajesDTO;
/*    */ import com.anunciadores.mapper.mapperMensaje;
/*    */ import com.anunciadores.model.Mensajes;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class mapperMensajeImpl
/*    */   implements mapperMensaje
/*    */ {
/*    */   public MensajesDTO EntityToMensajesDTO(Mensajes entity) {
/* 20 */     if (entity == null) {
/* 21 */       return null;
/*    */     }
/*    */     
/* 24 */     MensajesDTO mensajesDTO = new MensajesDTO();
/*    */     
/* 26 */     mensajesDTO.setId(entity.getId());
/* 27 */     mensajesDTO.setDescripcion(entity.getDescripcion());
/* 28 */     mensajesDTO.setRemitente(entity.getRemitente());
/* 29 */     mensajesDTO.setDestinatario(entity.getDestinatario());
/* 30 */     mensajesDTO.setFechaRegistro(entity.getFechaRegistro());
/* 31 */     mensajesDTO.setActivo(entity.isActivo());
/*    */     
/* 33 */     return mensajesDTO;
/*    */   }
/*    */ 
/*    */   
/*    */   public Mensajes MensajesDTOToEntity(MensajesDTO dto) {
/* 38 */     if (dto == null) {
/* 39 */       return null;
/*    */     }
/*    */     
/* 42 */     Mensajes mensajes = new Mensajes();
/*    */     
/* 44 */     mensajes.setId(dto.getId());
/* 45 */     mensajes.setDescripcion(dto.getDescripcion());
/* 46 */     mensajes.setRemitente(dto.getRemitente());
/* 47 */     mensajes.setDestinatario(dto.getDestinatario());
/* 48 */     mensajes.setFechaRegistro(dto.getFechaRegistro());
/* 49 */     mensajes.setActivo(dto.isActivo());
/*    */     
/* 51 */     return mensajes;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<MensajesDTO> listEntityToMensajesDTO(List<Mensajes> entity) {
/* 56 */     if (entity == null) {
/* 57 */       return null;
/*    */     }
/*    */     
/* 60 */     List<MensajesDTO> list = new ArrayList<>(entity.size());
/* 61 */     for (Mensajes mensajes : entity) {
/* 62 */       list.add(EntityToMensajesDTO(mensajes));
/*    */     }
/*    */     
/* 65 */     return list;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\mapper\mapperMensajeImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */