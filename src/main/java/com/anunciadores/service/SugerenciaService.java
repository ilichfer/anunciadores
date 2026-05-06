/*    */ package  com.anunciadores.service;
/*    */ 
/*    */ import com.anunciadores.model.Sugerencia;
/*    */ import com.anunciadores.repository.IPersonaRepo;
/*    */ import com.anunciadores.repository.ISugerenciaRepo;
/*    */ import com.anunciadores.service.interfaces.ISugerenciaService;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service
/*    */ public class SugerenciaService implements ISugerenciaService {
/*    */   private ISugerenciaRepo sugerenciaRepo;
/*    */   private IPersonaRepo personaRepo;
/*    */   
/*    */   public SugerenciaService(ISugerenciaRepo sugerenciaRepo, IPersonaRepo personaRepo) {
/* 15 */     this.sugerenciaRepo = sugerenciaRepo;
/* 16 */     this.personaRepo = personaRepo;
/*    */   }
/*    */ 
/*    */   
/*    */   public Sugerencia saveSugerencia(Sugerencia saveSugerencia, Integer idPersona) {
/* 21 */     saveSugerencia.setPersona(this.personaRepo.findById(idPersona).get());
/* 22 */     return (Sugerencia)this.sugerenciaRepo.save(saveSugerencia);
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\SugerenciaService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */