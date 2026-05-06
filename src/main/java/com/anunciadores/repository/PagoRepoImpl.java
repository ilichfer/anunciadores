  package  com.anunciadores.repository;
  
  import com.anunciadores.dto.PagoDto;
  import com.anunciadores.dto.ReportePagoDto;
  import com.anunciadores.model.Pago;
  import java.util.ArrayList;
  import java.util.List;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.jdbc.core.JdbcTemplate;
  import org.springframework.jdbc.core.ResultSetExtractor;
  import org.springframework.stereotype.Service;
  
  
  
  
  
  
  
  
  @Service
  public class PagoRepoImpl
  {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public List<Pago> findPagosByIdCurso(int idpersona, Integer idcurso) {
/*  27 */     StringBuilder sql = new StringBuilder();
      
/*  29 */     List<Pago> pagoList = new ArrayList<>();
      try {
/*  31 */       sql.append("select p.* from pago p \njoin curso c on p.id_curso = c.id \nwhere c.id =" + idcurso + " and p.idpersona = " + idpersona);
  
  
        
/*  35 */       this.jdbcTemplate.query(sql.toString(), (ResultSetExtractor)new Object(this, pagoList));
  
  
  
  
  
  
  
  
  
      
      }
/*  47 */     catch (Exception e) {
/*  48 */       e.printStackTrace();
      } 
/*  50 */     return pagoList;
    }
  
    
    public List<PagoDto> reportePagosCursos(int idCurso) {
/*  55 */     StringBuilder sql = new StringBuilder();
      
/*  57 */     List<PagoDto> pagoList = new ArrayList<>();
      try {
/*  59 */       sql.append("select SUM(pa.valor) as suma, pa.id_curso, pa.idpersona, c.valortotal from curso c  join pago pa on c.id = pa.id_curso  join persona pe on pa.idpersona = pe.id  where pa.id_curso = " + idCurso + " GROUP by pa.idpersona ");
  
  
  
  
        
/*  65 */       this.jdbcTemplate.query(sql.toString(), (ResultSetExtractor)new Object(this, pagoList));
  
  
  
  
  
  
  
  
      
      }
/*  76 */     catch (Exception e) {
/*  77 */       e.printStackTrace();
      } 
/*  79 */     return pagoList;
    }
    
    public List<ReportePagoDto> reportePagos() {
/*  83 */     StringBuilder sql = new StringBuilder();
      
/*  85 */     List<ReportePagoDto> pagoList = new ArrayList<>();
      try {
/*  87 */       sql.append("select c.id ,c.nombrecurso ,c.valortotal ,pe.nombre, (select sum(pa.valor) from pago pa where pa.idpersona = pe.id and pa.id_curso = c.id) as pagos, case  when (select sum(pa.valor) from pago pa where pa.idpersona = pe.id and pa.id_curso = c.id) is null then c.valortotal   when (select sum(pa.valor) from pago pa where pa.idpersona = pe.id and pa.id_curso = c.id) is not null  then valortotal-(select sum(pa.valor) from pago pa where pa.idpersona = pe.id and pa.id_curso = c.id) else Null end adeuda from curso c  join inscripciones i on c.id  = i.id_curso  join persona pe on i.id_persona = pe.id  order by c.nombrecurso asc ");
  
  
  
  
  
  
  
  
  
  
        
/*  99 */       this.jdbcTemplate.query(sql.toString(), (ResultSetExtractor)new Object(this, pagoList));
  
  
  
  
  
  
  
  
      
      }
/* 110 */     catch (Exception e) {
/* 111 */       e.printStackTrace();
      } 
/* 113 */     return pagoList;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\PagoRepoImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */