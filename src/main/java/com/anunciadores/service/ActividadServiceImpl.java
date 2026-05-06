/*    */ package  com.anunciadores.service;
/*    */ 
/*    */ import com.anunciadores.dto.ActividadDto;
/*    */ import com.anunciadores.model.Actividad;
/*    */ import com.anunciadores.model.Curso;
/*    */ import com.anunciadores.model.Mesa;
/*    */ import com.anunciadores.model.Persona;
/*    */ import com.anunciadores.repository.IActividadRepo;
/*    */ import com.anunciadores.repository.IPersonaRepo;
/*    */ import com.anunciadores.service.interfaces.IActividadService;
/*    */ import java.text.ParseException;
/*    */ import java.util.ArrayList;
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
/*    */ public class ActividadServiceImpl
/*    */   implements IActividadService
/*    */ {
/*    */   @Autowired
/*    */   private IActividadRepo ActividadRepository;
/*    */   @Autowired
/*    */   private IPersonaRepo personaRepository;
/* 30 */   List<Curso> listaActividades = new ArrayList<>();
/*    */   
/*    */   Actividad actividadEntity;
/*    */   List<Persona> listaPersonas;
/*    */   
/*    */   public List<Actividad> listarActiviades() {
/* 36 */     return this.ActividadRepository.findAll();
/*    */   }
/*    */ 
/*    */   
/*    */   public Actividad save(ActividadDto actividadDTO) throws ParseException {
/* 41 */     this.actividadEntity = new Actividad();
/* 42 */     this.actividadEntity.setNombreActividad(actividadDTO.getNombreActividad());
/* 43 */     this.actividadEntity.setFecha(actividadDTO.getFecha());
/* 44 */     if (actividadDTO.getId() != 0) {
/* 45 */       this.actividadEntity.setId(actividadDTO.getId());
/*    */     }
/*    */ 
/*    */     
/* 49 */     return (Actividad)this.ActividadRepository.save(this.actividadEntity);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Actividad findActividadById(Integer id) {
/* 55 */     return this.ActividadRepository.findById(id).get();
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(ActividadDto actividadDTO) {
/* 60 */     this.ActividadRepository.deleteById(Integer.valueOf(actividadDTO.getId()));
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Persona> buscarTodosSinActividad(int idActividad) {
/* 65 */     return this.personaRepository.buscarPersonaSinActividad(Integer.valueOf(idActividad));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Persona> buscarPersonasActividad(int idActividad) {
/* 71 */     return this.personaRepository.buscarPersonaByActividad(Integer.valueOf(idActividad));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Mesa> buscarMesasActividad(ActividadDto actividadDto) {
/* 79 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Actividad> listarActiviadesByPersona(int idPersona) {
/* 84 */     return this.ActividadRepository.findActividadesByPersona(idPersona);
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\ActividadServiceImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */