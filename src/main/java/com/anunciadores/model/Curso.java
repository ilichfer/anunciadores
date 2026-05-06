  package  com.anunciadores.model;
  
  import com.anunciadores.model.Persona;
  import java.io.Serializable;
  import javax.persistence.Column;
  import javax.persistence.Entity;
  import javax.persistence.GeneratedValue;
  import javax.persistence.GenerationType;
  import javax.persistence.Id;
  import javax.persistence.JoinColumn;
  import javax.persistence.OneToOne;
  import javax.persistence.Table;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  @Entity
  @Table(name = "curso")
  public class Curso
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "idpago")
    private int idPago;
    @Column(name = "nombrecurso")
    private String nombreCurso;
    @Column(name = "fechainicio")
    private String fechaInicio;
    @Column(name = "fechafin")
    private String fechaFin;
    @Column(name = "valortotal")
    private int valorTotal;
    @Column(name = "comentario")
    private Boolean comentario;
    @Column(name = "activo")
    private boolean activo;
    @OneToOne
    @JoinColumn(name = "profesor", referencedColumnName = "id")
    private Persona profesor;
    
    public Curso() {}
    
    public int getId() {
/*  63 */     return this.id;
    }
    
    public void setId(int id) {
/*  67 */     this.id = id;
    }
  
    
    public int getIdPago() {
/*  72 */     return this.idPago;
    }
    
    public void setIdPago(int idPago) {
/*  76 */     this.idPago = idPago;
    }
    
    public String getNombreCurso() {
/*  80 */     return this.nombreCurso;
    }
    
    public void setNombreCurso(String nombreCurso) {
/*  84 */     this.nombreCurso = nombreCurso;
    }
    
    public int getValorTotal() {
/*  88 */     return this.valorTotal;
    }
    
    public void setValorTotal(int valorTotal) {
/*  92 */     this.valorTotal = valorTotal;
    }
    
    public String getFechaInicio() {
/*  96 */     return this.fechaInicio;
    }
    
    public void setFechaInicio(String fechaInicio) {
/* 100 */     this.fechaInicio = fechaInicio;
    }
    
    public String getFechaFin() {
/* 104 */     return this.fechaFin;
    }
    
    public void setFechaFin(String fechaFin) {
/* 108 */     this.fechaFin = fechaFin;
    }
    
    public boolean isComentario() {
/* 112 */     return this.comentario.booleanValue();
    }
    
    public void setComentario(boolean comentario) {
/* 116 */     this.comentario = Boolean.valueOf(comentario);
    }
    
    public boolean isActivo() {
/* 120 */     return this.activo;
    }
    
    public void setActivo(boolean activo) {
/* 124 */     this.activo = activo;
    }
  
  
    
    public Curso(int id, int idPago, String nombreCurso, String fechaInicio, String fechaFin, int valorTotal, boolean comentario, boolean activo) {
/* 130 */     this.id = id;
/* 131 */     this.idPago = idPago;
/* 132 */     this.nombreCurso = nombreCurso;
/* 133 */     this.fechaInicio = fechaInicio;
/* 134 */     this.fechaFin = fechaFin;
/* 135 */     this.valorTotal = valorTotal;
/* 136 */     this.comentario = Boolean.valueOf(comentario);
/* 137 */     this.activo = activo;
    }
    
    public Persona getProfesor() {
/* 141 */     return this.profesor;
    }
    
    public void setProfesor(Persona profesor) {
/* 145 */     this.profesor = profesor;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\Curso.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */