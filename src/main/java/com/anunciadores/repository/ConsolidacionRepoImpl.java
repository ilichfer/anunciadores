  package  com.anunciadores.repository;
  
  import com.anunciadores.model.Persona;
  import com.anunciadores.model.inscripcionConsolidacion;
  import java.util.ArrayList;
  import java.util.List;
  import javax.persistence.EntityManager;
  import javax.persistence.PersistenceContext;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.jdbc.core.JdbcTemplate;
  import org.springframework.jdbc.core.ResultSetExtractor;
  import org.springframework.stereotype.Service;
  
  
  
  
  
  
  
  
  
  @Service
  public class ConsolidacionRepoImpl
  {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<Persona> listarConsolidacion1() {
/*  31 */     StringBuilder sql = new StringBuilder();
/*  32 */     Persona retorno = new Persona();
/*  33 */     List<Persona> personaList = new ArrayList<>();
      try {
/*  35 */       sql.append("select * from persona p ");
/*  36 */       sql.append("join consolidacion c on  p.id <> c.id_persona_consolidar ");
/*  37 */       sql.append("WHERE p.id not in(select pr.id_persona from persona_rol pr) ");
/*  38 */       System.out.println("buscar personas sin consolidacion ===> " + sql.toString());
        
/*  40 */       retorno = (Persona)this.jdbcTemplate.query(sql.toString(), (ResultSetExtractor)new Object(this, personaList));
  
  
  
  
  
  
  
  
      
      }
/*  51 */     catch (Exception e) {
/*  52 */       e.printStackTrace();
      } 
/*  54 */     return personaList;
    }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
    
    public inscripcionConsolidacion listarConsolidacionByPersona(int idPersona) {
/*  91 */     StringBuilder sql = new StringBuilder();
/*  92 */     sql.append("select * from consolidacion c ");
/*  93 */     sql.append("WHERE c.id_persona = " + idPersona);
  
  
      
      try {
/*  98 */       return (inscripcionConsolidacion)this.entityManager.createNativeQuery(sql.toString(), inscripcionConsolidacion.class)
/*  99 */         .getSingleResult();
/* 100 */     } catch (Exception e) {
/* 101 */       e.printStackTrace();
/* 102 */       return null;
      } 
    }
  
  
    
    public Persona listarPersonaConsolidacion(int idPersona) {
/* 109 */     StringBuilder sql = new StringBuilder();
/* 110 */     sql.append("select p.* from consolidacion c ");
/* 111 */     sql.append("join persona p on  c.id_persona_consolidar = p.id ");
/* 112 */     sql.append("WHERE c.id_persona = " + idPersona);
  
  
      
      try {
/* 117 */       return (Persona)this.entityManager.createNativeQuery(sql.toString(), Persona.class)
/* 118 */         .getSingleResult();
/* 119 */     } catch (Exception e) {
/* 120 */       e.printStackTrace();
/* 121 */       return null;
      } 
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\ConsolidacionRepoImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */