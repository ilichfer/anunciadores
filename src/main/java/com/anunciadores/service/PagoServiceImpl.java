/*    */ package  com.anunciadores.service;
/*    */ 
/*    */ import com.anunciadores.dto.PagoDto;
/*    */ import com.anunciadores.dto.ReportePagoDto;
/*    */ import com.anunciadores.model.Pago;
/*    */ import com.anunciadores.repository.IPagoRepo;
/*    */ import com.anunciadores.repository.PagoRepoImpl;
/*    */ import com.anunciadores.service.interfaces.IPagoService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class PagoServiceImpl
/*    */   implements IPagoService
/*    */ {
/*    */   @Autowired
/*    */   private IPagoRepo PagoRepository;
/*    */   @Autowired
/*    */   private PagoRepoImpl pagoDao;
/*    */   
/*    */   public List<Pago> findAll() {
/* 28 */     return this.PagoRepository.findAll();
/*    */   }
/*    */ 
/*    */   
/*    */   public Pago save(Pago pago) {
/* 33 */     return (Pago)this.PagoRepository.save(pago);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Pago> findPagosByIdCurso(int idpersona, int idcurso) {
/* 38 */     List<Pago> ListaPago = this.pagoDao.findPagosByIdCurso(idpersona, Integer.valueOf(idcurso));
/* 39 */     return ListaPago;
/*    */   }
/*    */ 
/*    */   
/*    */   public Pago delete(Pago pago) {
/* 44 */     this.PagoRepository.deleteById(Integer.valueOf(pago.getId()));
/* 45 */     return pago;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<PagoDto> reportePagosCursos(int idCurso) {
/* 50 */     return this.pagoDao.reportePagosCursos(idCurso);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<ReportePagoDto> reportePagos() {
/* 55 */     return this.pagoDao.reportePagos();
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\PagoServiceImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */