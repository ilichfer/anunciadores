  package  com.anunciadores.repository;
  
  import com.anunciadores.model.InscripcionActividad;
  import com.anunciadores.model.Inscripciones;
  import com.anunciadores.model.Mesa;
  import com.anunciadores.model.Persona;
  import com.anunciadores.repository.IPersonaRepo;
  import com.anunciadores.repository.InscripcionActividadRepo;
  import com.anunciadores.repository.InscripcionRepo;
  import java.util.ArrayList;
  import java.util.HashMap;
  import java.util.List;
  import java.util.Map;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.jdbc.core.JdbcTemplate;
  import org.springframework.jdbc.core.ResultSetExtractor;
  import org.springframework.stereotype.Service;
  
  
  
  
  
  
  
  
  
  @Service
  public class ActividadRepoImpl
  {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private IPersonaRepo PersonaRepository;
    @Autowired
    private InscripcionRepo inscripcionesRepository;
    @Autowired
    private InscripcionActividadRepo inscripcionActividadRepository;
    
    public List<Persona> buscarPersonaSinActividad(Integer idActividad) {
/*  40 */     StringBuilder sql = new StringBuilder();
/*  41 */     Persona retorno = new Persona();
/*  42 */     List<Persona> personaList = new ArrayList<>();
      try {
/*  44 */       sql.append("select * from persona p where id not in (select i.id_persona from inscripcion_actividades i  join actividades a on i.id_actividad = a.id where i.id_actividad =" + idActividad + ")");
  
  
  
  
  
        
/*  51 */       retorno = (Persona)this.jdbcTemplate.query(sql.toString(), (ResultSetExtractor)new Object(this, personaList));
  
  
  
  
  
  
  
  
      
      }
/*  62 */     catch (Exception e) {
/*  63 */       e.printStackTrace();
      } 
/*  65 */     return personaList;
    }
  
    
    public void eliminarPersonaConActividad1(Integer idPersona, Integer idCurso) {
/*  70 */     StringBuilder sql = new StringBuilder();
/*  71 */     Map<String, Object> parameters = new HashMap<>();
      
      try {
/*  74 */       sql.append("delete from inscripciones where id_persona =" + idPersona + " and id_curso = " + idCurso);
        
/*  76 */       this.jdbcTemplate.execute(sql.toString());
      
      }
/*  79 */     catch (Exception e) {
/*  80 */       e.printStackTrace();
      } 
    }
  
  
  
    
    public void agregarPersonaConActividad(Integer idPersona, int idCurso) {
      try {
/*  89 */       Inscripciones inscripcion = new Inscripciones();
/*  90 */       inscripcion.setIdCurso(idCurso);
/*  91 */       inscripcion.setIdPersona(idPersona.intValue());
        
/*  93 */       this.inscripcionesRepository.save(inscripcion);
      }
/*  95 */     catch (Exception e) {
/*  96 */       e.printStackTrace();
      } 
    }
  
  
    
    public void agregarPersonaActividad(Integer idPersona, int idActividad) {
      try {
/* 104 */       InscripcionActividad inscripcion = new InscripcionActividad();
/* 105 */       inscripcion.setIdActividad(idActividad);
/* 106 */       inscripcion.setIdPersona(idPersona.intValue());
        
/* 108 */       this.inscripcionActividadRepository.save(inscripcion);
      }
/* 110 */     catch (Exception e) {
/* 111 */       e.printStackTrace();
      } 
    }
    
    public List<Persona> buscarPersonaByActividad(Integer idActividad) {
/* 116 */     StringBuilder sql = new StringBuilder();
/* 117 */     Persona retorno = new Persona();
/* 118 */     List<Persona> personaList = new ArrayList<>();
      try {
/* 120 */       sql.append("select distinct p.* from persona p join inscripcion_actividades i on p.id  = i.id_persona join actividades a on i.id_actividad = a.id where a.id =" + idActividad);
  
  
  
        
/* 125 */       System.out.println("personas de una actividad === >" + sql.toString());
/* 126 */       retorno = (Persona)this.jdbcTemplate.query(sql.toString(), (ResultSetExtractor)new Object(this, personaList));
  
  
  
  
  
  
  
  
      
      }
/* 137 */     catch (Exception e) {
/* 138 */       e.printStackTrace();
      } 
/* 140 */     return personaList;
    }
  
    
    public List<Mesa> buscarMesasByActividad(Integer idActividad) {
/* 145 */     StringBuilder sql = new StringBuilder();
/* 146 */     Mesa retorno = new Mesa();
/* 147 */     List<Mesa> mesasList = new ArrayList<>();
      try {
/* 149 */       sql.append("SELECT * FROM actividades act  join mesa m on act.id = m.id_actividad where act.id=" + idActividad);
  
  
        
/* 153 */       System.out.println("mesas de una actividad === >" + sql.toString());
/* 154 */       retorno = (Mesa)this.jdbcTemplate.query(sql.toString(), (ResultSetExtractor)new Object(this, mesasList));
  
  
  
  
  
  
  
  
      
      }
/* 165 */     catch (Exception e) {
/* 166 */       e.printStackTrace();
      } 
/* 168 */     return mesasList;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\ActividadRepoImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */