/*    */ package  com.anunciadores.service;
/*    */ 
/*    */ import com.anunciadores.dto.ItemCombo;
/*    */ import com.anunciadores.dto.ListasCombos;
/*    */ import com.anunciadores.enums.ECombos;
/*    */ import com.anunciadores.mapper.mapperParametros;
/*    */ import com.anunciadores.repository.IParametrosRepo;
/*    */ import com.anunciadores.service.interfaces.ICombos;
/*    */ import java.util.List;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class CombosServiceImpl
/*    */   implements ICombos
/*    */ {
/*    */   @Autowired
/*    */   private IParametrosRepo parametrosRepo;
/*    */   @Autowired
/*    */   private mapperParametros mapperParametros;
/* 39 */   private final Logger log = LoggerFactory.getLogger(com.anunciadores.service.CombosServiceImpl.class);
/*    */ 
/*    */   
/*    */   public ListasCombos listarParametros() {
/* 43 */     ListasCombos listas = new ListasCombos();
/* 44 */     List<ItemCombo> listaGenero = this.mapperParametros.listEntitytoListDto(this.parametrosRepo.findByGrupo(ECombos.GENERO.toString()));
/* 45 */     List<ItemCombo> listaestadoCivil = this.mapperParametros.listEntitytoListDto(this.parametrosRepo.findByGrupo(ECombos.ESTADOCIVIL.toString()));
/* 46 */     List<ItemCombo> listaEscolaridad = this.mapperParametros.listEntitytoListDto(this.parametrosRepo.findByGrupo(ECombos.ESCOLARIDAD.toString()));
/* 47 */     List<ItemCombo> listaDocumentos = this.mapperParametros.listEntitytoListDto(this.parametrosRepo.findByGrupo(ECombos.DOCUMENTO.toString()));
/* 48 */     listas.setListaGenero(listaGenero);
/* 49 */     listas.setEstadoCivil(listaestadoCivil);
/* 50 */     listas.setListaEscolaridad(listaEscolaridad);
/* 51 */     listas.setListaDocuemntos(listaDocumentos);
/* 52 */     return listas;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\CombosServiceImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */