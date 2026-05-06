/*    */ package  com.anunciadores.repository;
/*    */ 
/*    */ import com.anunciadores.model.Persona;
/*    */ import com.anunciadores.repository.IPersonaRepo;
/*    */ import com.anunciadores.repository.InscripcionActividadRepo;
/*    */ import com.anunciadores.repository.InscripcionRepo;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
/*    */ import org.springframework.jdbc.core.ResultSetExtractor;
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
/*    */ public class MesasRepoImpl
/*    */ {
/*    */   @Autowired
/*    */   JdbcTemplate jdbcTemplate;
/*    */   @Autowired
/*    */   private IPersonaRepo PersonaRepository;
/*    */   @Autowired
/*    */   private InscripcionRepo inscripcionesRepository;
/*    */   @Autowired
/*    */   private InscripcionActividadRepo inscripcionActividadRepository;
/*    */   
/*    */   public List<Persona> buscarPersonaSinMesas1(Integer idMesa) {
/* 40 */     StringBuilder sql = new StringBuilder();
/* 41 */     Persona retorno = new Persona();
/* 42 */     List<Persona> personaList = new ArrayList<>();
/*    */     try {
/* 44 */       sql.append("select * from persona p where id not in (select pm.id_persona from mesa  m  join persona_mesa pm on m.id = pm.id_mesa where m.id =" + idMesa + ")");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 51 */       retorno = (Persona)this.jdbcTemplate.query(sql.toString(), (ResultSetExtractor)new Object(this, personaList));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     }
/* 62 */     catch (Exception e) {
/* 63 */       e.printStackTrace();
/*    */     } 
/* 65 */     return personaList;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\MesasRepoImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */