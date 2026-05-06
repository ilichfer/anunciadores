/*    */ package  com.anunciadores.mapper;
/*    */ 
/*    */ import com.anunciadores.dto.ItemCombo;
/*    */ import com.anunciadores.model.ParametrosCombos;
/*    */ import java.util.List;
/*    */ import org.mapstruct.Mapper;
/*    */ import org.mapstruct.factory.Mappers;
/*    */ 
/*    */ 
/*    */ @Mapper(componentModel = "spring")
/*    */ public interface mapperParametros
/*    */ {
/* 13 */   public static final com.anunciadores.mapper.mapperParametros INSTANCE = (com.anunciadores.mapper.mapperParametros)Mappers.getMapper(com.anunciadores.mapper.mapperParametros.class);
/*    */   
/*    */   List<ItemCombo> listEntitytoListDto(List<ParametrosCombos> paramList);
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\mapper\mapperParametros.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */