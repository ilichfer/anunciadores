/*    */ package  com.anunciadores.mapper;
/*    */ 
/*    */ import com.anunciadores.dto.NotasCursoDTO;
/*    */ import com.anunciadores.mapper.mapperNotas;
/*    */ import com.anunciadores.model.NotasCurso;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class mapperNotasImpl
/*    */   implements mapperNotas
/*    */ {
/*    */   public NotasCursoDTO EntityToNotasCursoDTO(NotasCurso entity) {
/* 18 */     if (entity == null) {
/* 19 */       return null;
/*    */     }
/*    */     
/* 22 */     NotasCursoDTO notasCursoDTO = new NotasCursoDTO();
/*    */     
/* 24 */     notasCursoDTO.setId(entity.getId());
/* 25 */     notasCursoDTO.setCurso(entity.getCurso());
/* 26 */     notasCursoDTO.setNotaMaestro(entity.getNotaMaestro());
/* 27 */     notasCursoDTO.setNotaAsistencia(entity.getNotaAsistencia());
/* 28 */     notasCursoDTO.setNotaPractica(entity.getNotaPractica());
/* 29 */     notasCursoDTO.setNotaExamenFinal(entity.getNotaExamenFinal());
/* 30 */     notasCursoDTO.setNotaFinal(entity.getNotaFinal());
/*    */     
/* 32 */     return notasCursoDTO;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\mapper\mapperNotasImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */