/*    */ package  com.anunciadores.mapper;
/*    */ 
/*    */ import com.anunciadores.dto.PersonaConsolidacionDto;
/*    */ import com.anunciadores.model.Persona;
/*    */ import java.util.List;
/*    */ import org.mapstruct.Mapper;
/*    */ import org.mapstruct.factory.Mappers;
/*    */ 
/*    */ @Mapper(componentModel = "spring")
/*    */ public interface mapperConsolidacion
/*    */ {
/* 12 */   public static final com.anunciadores.mapper.mapperConsolidacion INSTANCE = (com.anunciadores.mapper.mapperConsolidacion)Mappers.getMapper(com.anunciadores.mapper.mapperConsolidacion.class);
/*    */   
/*    */   PersonaConsolidacionDto EntitytoConsolidacionDto(Persona paramPersona);
/*    */   
/*    */   List<PersonaConsolidacionDto> liitEntitytoConsolidacionDto(List<Persona> paramList);
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\mapper\mapperConsolidacion.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */