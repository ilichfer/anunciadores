/*    */ package  com.anunciadores.service;
/*    */ 
/*    */ import com.anunciadores.dto.MensajesDTO;
/*    */ import com.anunciadores.mapper.mapperMensaje;
/*    */ import com.anunciadores.model.Mensajes;
/*    */ import com.anunciadores.model.Persona;
/*    */ import com.anunciadores.repository.IMensajesRepo;
/*    */ import com.anunciadores.repository.IPersonaRepo;
/*    */ import com.anunciadores.service.interfaces.IMensajeService;
/*    */ import com.anunciadores.util.UtilDate;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class MensajeService
/*    */   implements IMensajeService
/*    */ {
/*    */   private IMensajesRepo mensajesRepo;
/*    */   @Autowired
/*    */   private UtilDate utilDate;
/*    */   @Autowired
/*    */   private mapperMensaje mapperMensaje;
/*    */   @Autowired
/*    */   private IPersonaRepo personaRepo;
/*    */   
/*    */   public MensajeService(IMensajesRepo mensajesRepo) {
/* 34 */     this.mensajesRepo = mensajesRepo;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<MensajesDTO> buscarMensaje(Integer idPersona) {
/* 39 */     return this.mapperMensaje.listEntityToMensajesDTO(this.mensajesRepo.findMensajesByIdPersona(idPersona.intValue()));
/*    */   }
/*    */ 
/*    */   
/*    */   public List<MensajesDTO> buscarTodosMensaje(Integer idPersona) {
/* 44 */     List<MensajesDTO> lista = this.mapperMensaje.listEntityToMensajesDTO(this.mensajesRepo.findMensajesByIdPersona(idPersona.intValue()));
/* 45 */     for (MensajesDTO dto : lista) {
/* 46 */       if (dto.getFechaRegistro() != null) {
/* 47 */         dto.setFechaMostrar(this.utilDate.convertDateToString(dto.getFechaRegistro()));
/*    */       }
/*    */     } 
/* 50 */     return lista;
/*    */   }
/*    */ 
/*    */   
/*    */   public MensajesDTO buscarMensajeXId(Integer idMensaje) {
/* 55 */     Mensajes mensajeSave = this.mensajesRepo.findById(idMensaje).get();
/* 56 */     mensajeSave.setActivo(false);
/* 57 */     return this.mapperMensaje.EntityToMensajesDTO((Mensajes)this.mensajesRepo.save(mensajeSave));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MensajesDTO guardarMensaje(MensajesDTO mensaje) {
/* 64 */     return this.mapperMensaje.EntityToMensajesDTO((Mensajes)this.mensajesRepo.save(this.mapperMensaje.MensajesDTOToEntity(mensaje)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enviarTodosMensajes(MensajesDTO mensaje) {
/* 70 */     List<Persona> personas = this.personaRepo.findUsuarios();
/* 71 */     for (Persona per : personas) {
/* 72 */       mensaje.setDestinatario(per);
/* 73 */       this.mensajesRepo.save(this.mapperMensaje.MensajesDTOToEntity(mensaje));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\MensajeService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */