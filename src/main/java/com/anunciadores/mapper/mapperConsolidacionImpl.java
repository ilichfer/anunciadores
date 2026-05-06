/*    */ package  com.anunciadores.mapper;
/*    */ 
/*    */ import com.anunciadores.dto.PersonaConsolidacionDto;
/*    */ import com.anunciadores.mapper.mapperConsolidacion;
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
/*    */ public class mapperConsolidacionImpl
/*    */   implements mapperConsolidacion
/*    */ {
/*    */   public PersonaConsolidacionDto EntitytoConsolidacionDto(Persona entity) {
/* 20 */     if (entity == null) {
/* 21 */       return null;
/*    */     }
/*    */     
/* 24 */     PersonaConsolidacionDto personaConsolidacionDto = new PersonaConsolidacionDto();
/*    */     
/* 26 */     if (entity.getId() != null) {
/* 27 */       personaConsolidacionDto.setId(entity.getId().intValue());
/*    */     }
/* 29 */     personaConsolidacionDto.setNombre(entity.getNombre());
/* 30 */     personaConsolidacionDto.setApellido(entity.getApellido());
/* 31 */     personaConsolidacionDto.setDocumento(entity.getDocumento());
/* 32 */     personaConsolidacionDto.setTelefono(entity.getTelefono());
/* 33 */     personaConsolidacionDto.setFechanacimiento(entity.getFechanacimiento());
/* 34 */     personaConsolidacionDto.setTipodocumento(entity.getTipodocumento());
/* 35 */     personaConsolidacionDto.setEmail(entity.getEmail());
/* 36 */     personaConsolidacionDto.setPassword(entity.getPassword());
/* 37 */     personaConsolidacionDto.setGenero(entity.getGenero());
/* 38 */     personaConsolidacionDto.setEstadoCivil(entity.getEstadoCivil());
/* 39 */     personaConsolidacionDto.setPaisNacimiento(entity.getPaisNacimiento());
/* 40 */     personaConsolidacionDto.setCiudad(entity.getCiudad());
/* 41 */     personaConsolidacionDto.setDiscapacidad(entity.getDiscapacidad());
/* 42 */     personaConsolidacionDto.setDescDiscapacidad(entity.getDescDiscapacidad());
/* 43 */     personaConsolidacionDto.setPerteneceMinoria(entity.getPerteneceMinoria());
/* 44 */     personaConsolidacionDto.setDescMinoria(entity.getDescMinoria());
/* 45 */     personaConsolidacionDto.setDireccion(entity.getDireccion());
/* 46 */     personaConsolidacionDto.setCiudadDeptoDireccion(entity.getCiudadDeptoDireccion());
/* 47 */     personaConsolidacionDto.setCelular(entity.getCelular());
/* 48 */     personaConsolidacionDto.setOcupacion(entity.getOcupacion());
/* 49 */     personaConsolidacionDto.setEscolaridad(entity.getEscolaridad());
/* 50 */     personaConsolidacionDto.setFechaConvercionCristo(entity.getFechaConvercionCristo());
/* 51 */     personaConsolidacionDto.setFechaLlegadaAdc(entity.getFechaLlegadaAdc());
/* 52 */     personaConsolidacionDto.setFechaBautizo(entity.getFechaBautizo());
/* 53 */     personaConsolidacionDto.setFechaBautizoEspiritu(entity.getFechaBautizoEspiritu());
/* 54 */     personaConsolidacionDto.setConsolidacion(entity.getConsolidacion());
/*    */     
/* 56 */     return personaConsolidacionDto;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<PersonaConsolidacionDto> liitEntitytoConsolidacionDto(List<Persona> entity) {
/* 61 */     if (entity == null) {
/* 62 */       return null;
/*    */     }
/*    */     
/* 65 */     List<PersonaConsolidacionDto> list = new ArrayList<>(entity.size());
/* 66 */     for (Persona persona : entity) {
/* 67 */       list.add(EntitytoConsolidacionDto(persona));
/*    */     }
/*    */     
/* 70 */     return list;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\mapper\mapperConsolidacionImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */