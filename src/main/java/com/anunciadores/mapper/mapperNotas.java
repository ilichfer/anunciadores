/*    */ package  com.anunciadores.mapper;
/*    */ 
/*    */ import com.anunciadores.dto.NotasCursoDTO;
/*    */ import com.anunciadores.model.NotasCurso;
/*    */ import org.mapstruct.Mapper;
/*    */ import org.mapstruct.factory.Mappers;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mapper(componentModel = "spring")
/*    */ public interface mapperNotas
/*    */ {
/* 16 */   public static final com.anunciadores.mapper.mapperNotas INSTANCE = (com.anunciadores.mapper.mapperNotas)Mappers.getMapper(com.anunciadores.mapper.mapperNotas.class);
/*    */   
/*    */   NotasCursoDTO EntityToNotasCursoDTO(NotasCurso paramNotasCurso);
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\mapper\mapperNotas.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */