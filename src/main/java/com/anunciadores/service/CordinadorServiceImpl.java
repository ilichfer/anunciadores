/*    */ package  com.anunciadores.service;
/*    */ 
/*    */ import com.anunciadores.dto.CoordinadorDTO;
/*    */ import com.anunciadores.mapper.mapperCordinador;
/*    */ import com.anunciadores.repository.ICoordinadorRepo;
/*    */ import com.anunciadores.service.interfaces.ICordinadorService;
/*    */ import java.util.Date;
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
/*    */ @Service
/*    */ public class CordinadorServiceImpl
/*    */   implements ICordinadorService
/*    */ {
/* 31 */   private final Logger log = LoggerFactory.getLogger(com.anunciadores.service.CordinadorServiceImpl.class);
/*    */   
/*    */   @Autowired
/*    */   private ICoordinadorRepo coordinadorRepo;
/*    */   
/*    */   @Autowired
/*    */   private mapperCordinador mapperCordinador;
/*    */ 
/*    */   
/*    */   public List<CoordinadorDTO> findAllBetweenDates(Date fechaStart, Date fechaEnd) {
/* 41 */     List<CoordinadorDTO> informesCoordinadores = this.mapperCordinador.listEntitytoCoordinadorDTO(this.coordinadorRepo.buscarHisInformes(fechaStart, fechaEnd));
/* 42 */     return informesCoordinadores;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\CordinadorServiceImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */