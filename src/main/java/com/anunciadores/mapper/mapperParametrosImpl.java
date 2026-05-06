/*    */ package  com.anunciadores.mapper;
/*    */ 
/*    */ import com.anunciadores.dto.ItemCombo;
/*    */ import com.anunciadores.mapper.mapperParametros;
/*    */ import com.anunciadores.model.ParametrosCombos;
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
/*    */ public class mapperParametrosImpl
/*    */   implements mapperParametros
/*    */ {
/*    */   public List<ItemCombo> listEntitytoListDto(List<ParametrosCombos> entity) {
/* 20 */     if (entity == null) {
/* 21 */       return null;
/*    */     }
/*    */     
/* 24 */     List<ItemCombo> list = new ArrayList<>(entity.size());
/* 25 */     for (ParametrosCombos parametrosCombos : entity) {
/* 26 */       list.add(parametrosCombosToItemCombo(parametrosCombos));
/*    */     }
/*    */     
/* 29 */     return list;
/*    */   }
/*    */   
/*    */   protected ItemCombo parametrosCombosToItemCombo(ParametrosCombos parametrosCombos) {
/* 33 */     if (parametrosCombos == null) {
/* 34 */       return null;
/*    */     }
/*    */     
/* 37 */     ItemCombo itemCombo = new ItemCombo();
/*    */     
/* 39 */     itemCombo.setId(parametrosCombos.getId());
/* 40 */     itemCombo.setGrupo(parametrosCombos.getGrupo());
/* 41 */     itemCombo.setDescripcion(parametrosCombos.getDescripcion());
/*    */     
/* 43 */     return itemCombo;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\mapper\mapperParametrosImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */