/*    */ package  com.anunciadores.mapper;
/*    */ 
/*    */ import com.anunciadores.dto.PersonaDto;
/*    */ import com.anunciadores.mapper.mapperPersona;
/*    */ import com.anunciadores.model.Persona;
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
/*    */ public class mapperPersonaImpl
/*    */   implements mapperPersona
/*    */ {
/*    */   public PersonaDto EntityToPersonaDto(Persona entity) {
/* 20 */     if (entity == null) {
/* 21 */       return null;
/*    */     }
/*    */     
/* 24 */     PersonaDto personaDto = new PersonaDto();
/*    */     
/* 26 */     if (entity.getId() != null) {
/* 27 */       personaDto.setId(entity.getId().intValue());
/*    */     }
/* 29 */     personaDto.setNombre(entity.getNombre());
/* 30 */     personaDto.setApellido(entity.getApellido());
/* 31 */     personaDto.setDocumento(entity.getDocumento());
/* 32 */     personaDto.setTelefono(entity.getTelefono());
/* 33 */     personaDto.setFechanacimiento(entity.getFechanacimiento());
/* 34 */     personaDto.setTipodocumento(entity.getTipodocumento());
/* 35 */     personaDto.setEmail(entity.getEmail());
/* 36 */     personaDto.setPassword(entity.getPassword());
/* 37 */     if (entity.getConsolidacion() != null) {
/* 38 */       personaDto.setConsolidacion(entity.getConsolidacion().booleanValue());
/*    */     }
/*    */     
/* 41 */     return personaDto;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<PersonaDto> listEntityToConsolidacionDto(List<Persona> entity) {
/* 46 */     if (entity == null) {
/* 47 */       return null;
/*    */     }
/*    */     
/* 50 */     List<PersonaDto> list = new ArrayList<>(entity.size());
/* 51 */     for (Persona persona : entity) {
/* 52 */       list.add(EntityToPersonaDto(persona));
/*    */     }
/*    */     
/* 55 */     return list;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\mapper\mapperPersonaImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */