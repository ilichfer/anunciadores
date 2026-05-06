/*    */ package  com.anunciadores.repository;
/*    */ 
/*    */ import com.anunciadores.model.Curso;
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
/*    */ @Service
/*    */ public class CursosRepoImpl
/*    */ {
/*    */   @Autowired
/*    */   JdbcTemplate jdbcTemplate;
/*    */   
/*    */   public List<Curso> cursosByIdPersona(Integer idPersona) {
/* 24 */     StringBuilder sql = new StringBuilder();
/* 25 */     Curso retorno = new Curso();
/* 26 */     List<Curso> cuersosList = new ArrayList<>();
/*    */     try {
/* 28 */       sql.append("SELECT c.* FROM persona p   join inscripciones ins on p.id = ins.id_persona join curso c on ins.id_curso = c.id  where p.id= " + idPersona);
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 33 */       retorno = (Curso)this.jdbcTemplate.query(sql.toString(), (ResultSetExtractor)new Object(this, cuersosList));
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
/* 44 */     catch (Exception e) {
/* 45 */       e.printStackTrace();
/*    */     } 
/* 47 */     return cuersosList;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\CursosRepoImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */