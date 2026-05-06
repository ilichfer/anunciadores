/*    */ package  com.anunciadores.mapper;
/*    */ 
/*    */ import com.anunciadores.dto.CoordinadorDTO;
/*    */ import com.anunciadores.model.Coordinador;
/*    */ import java.util.List;
/*    */ import org.mapstruct.Mapper;
/*    */ import org.mapstruct.factory.Mappers;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mapper(componentModel = "spring")
/*    */ public interface mapperCordinador
/*    */ {
/* 14 */   public static final com.anunciadores.mapper.mapperCordinador INSTANCE = (com.anunciadores.mapper.mapperCordinador)Mappers.getMapper(com.anunciadores.mapper.mapperCordinador.class);
/*    */   
/*    */   CoordinadorDTO EntitytoCoordinadorDTO(Coordinador paramCoordinador);
/*    */   
/*    */   List<CoordinadorDTO> listEntitytoCoordinadorDTO(List<Coordinador> paramList);
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\mapper\mapperCordinador.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */