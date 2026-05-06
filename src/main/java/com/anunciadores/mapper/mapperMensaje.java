/*    */ package  com.anunciadores.mapper;
/*    */ 
/*    */ import com.anunciadores.dto.MensajesDTO;
/*    */ import com.anunciadores.model.Mensajes;
/*    */ import com.anunciadores.util.UtilDate;
/*    */ import java.util.List;
/*    */ import org.mapstruct.Mapper;
/*    */ import org.mapstruct.factory.Mappers;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mapper(componentModel = "spring")
/*    */ public interface mapperMensaje
/*    */ {
/*    */   @Autowired
/* 20 */   public static final UtilDate utilDate = new UtilDate();
/* 21 */   public static final com.anunciadores.mapper.mapperMensaje INSTANCE = (com.anunciadores.mapper.mapperMensaje)Mappers.getMapper(com.anunciadores.mapper.mapperMensaje.class);
/*    */   
/*    */   MensajesDTO EntityToMensajesDTO(Mensajes paramMensajes);
/*    */   
/*    */   Mensajes MensajesDTOToEntity(MensajesDTO paramMensajesDTO);
/*    */   
/*    */   List<MensajesDTO> listEntityToMensajesDTO(List<Mensajes> paramList);
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\mapper\mapperMensaje.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */