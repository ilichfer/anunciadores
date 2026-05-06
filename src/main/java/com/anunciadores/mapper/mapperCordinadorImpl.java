/*    */ package  com.anunciadores.mapper;
/*    */ 
/*    */ import com.anunciadores.dto.CoordinadorDTO;
/*    */ import com.anunciadores.mapper.mapperCordinador;
/*    */ import com.anunciadores.model.Coordinador;
/*    */ import java.sql.Date;
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
/*    */ public class mapperCordinadorImpl
/*    */   implements mapperCordinador
/*    */ {
/*    */   public CoordinadorDTO EntitytoCoordinadorDTO(Coordinador entity) {
/* 21 */     if (entity == null) {
/* 22 */       return null;
/*    */     }
/*    */     
/* 25 */     CoordinadorDTO coordinadorDTO = new CoordinadorDTO();
/*    */     
/* 27 */     coordinadorDTO.setId(entity.getId());
/* 28 */     if (entity.getFechaServicio() != null) {
/* 29 */       coordinadorDTO.setFechaServicio(new Date(entity.getFechaServicio().getTime()));
/*    */     }
/* 31 */     coordinadorDTO.setNotasServicio(entity.getNotasServicio());
/* 32 */     coordinadorDTO.setPersona(entity.getPersona());
/*    */     
/* 34 */     return coordinadorDTO;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<CoordinadorDTO> listEntitytoCoordinadorDTO(List<Coordinador> entity) {
/* 39 */     if (entity == null) {
/* 40 */       return null;
/*    */     }
/*    */     
/* 43 */     List<CoordinadorDTO> list = new ArrayList<>(entity.size());
/* 44 */     for (Coordinador coordinador : entity) {
/* 45 */       list.add(EntitytoCoordinadorDTO(coordinador));
/*    */     }
/*    */     
/* 48 */     return list;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\mapper\mapperCordinadorImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */