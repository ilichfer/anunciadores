/*    */ package  com.anunciadores.repository;
/*    */ 
/*    */ import com.anunciadores.model.Rol;
/*    */ import com.anunciadores.model.RolPersona;
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
/*    */ @Service
/*    */ public class RolesRepoImpl
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
/*    */   public List<Rol> buscarRoles(Integer idPersona) {
/* 36 */     StringBuilder sql = new StringBuilder();
/* 37 */     Rol retorno = new Rol();
/* 38 */     List<Rol> rolesList = new ArrayList<>();
/*    */     try {
/* 40 */       sql.append("SELECT r.* FROM persona_rol pr  join rol r on  pr.id_rol = r.id where pr.id_persona = " + idPersona);
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 45 */       retorno = (Rol)this.jdbcTemplate.query(sql.toString(), (ResultSetExtractor)new Object(this, rolesList));
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
/* 56 */     catch (Exception e) {
/* 57 */       e.printStackTrace();
/*    */     } 
/* 59 */     return rolesList;
/*    */   }
/*    */   
/*    */   public List<RolPersona> buscarRolesPersona(Integer idPersona) {
/* 63 */     StringBuilder sql = new StringBuilder();
/* 64 */     RolPersona retorno = new RolPersona();
/* 65 */     List<RolPersona> rolesList = new ArrayList<>();
/*    */     try {
/* 67 */       sql.append("SELECT * FROM persona_rol pr   where pr.id_persona = " + idPersona);
/*    */ 
/*    */       
/* 70 */       retorno = (RolPersona)this.jdbcTemplate.query(sql.toString(), (ResultSetExtractor)new Object(this, rolesList));
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
/* 81 */     catch (Exception e) {
/* 82 */       e.printStackTrace();
/*    */     } 
/* 84 */     return rolesList;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\RolesRepoImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */