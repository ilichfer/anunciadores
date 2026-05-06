/*    */ package  com.anunciadores.mapper;
/*    */ 
/*    */ import com.anunciadores.dto.PersonaDto;
/*    */ import com.anunciadores.model.Persona;
/*    */ import java.util.List;
/*    */ import org.mapstruct.Mapper;
/*    */ import org.mapstruct.factory.Mappers;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mapper(componentModel = "spring")
/*    */ public interface mapperPersona
/*    */ {
/* 15 */   public static final com.anunciadores.mapper.mapperPersona INSTANCE = (com.anunciadores.mapper.mapperPersona)Mappers.getMapper(com.anunciadores.mapper.mapperPersona.class);
/*    */   
/*    */   PersonaDto EntityToPersonaDto(Persona paramPersona);
/*    */   
/*    */   List<PersonaDto> listEntityToConsolidacionDto(List<Persona> paramList);
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\mapper\mapperPersona.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */