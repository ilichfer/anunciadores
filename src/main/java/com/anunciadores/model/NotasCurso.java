  package  com.anunciadores.model;
  
  import com.anunciadores.model.Curso;
  import com.anunciadores.model.Persona;
  import com.sun.istack.NotNull;
  import java.io.Serializable;
  import javax.persistence.Basic;
  import javax.persistence.Column;
  import javax.persistence.Entity;
  import javax.persistence.Id;
  import javax.persistence.JoinColumn;
  import javax.persistence.OneToOne;
  import javax.persistence.Table;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  @Entity
  @Table(name = "notas_curso")
  public class NotasCurso
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @NotNull
    private double id;
    @OneToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id")
    private Curso curso;
    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id")
    private Persona persona;
    @Column(name = "nota_maestro")
    private double notaMaestro;
    @Column(name = "nota_asistencia")
    private double notaAsistencia;
    @Column(name = "nota_practica")
    private double notaPractica;
    @Column(name = "nota_examen_final")
    private double notaExamenFinal;
    @Column(name = "nota_final")
    private double notaFinal;
    
    public double getId() {
/*  58 */     return this.id;
    }
    
    public void setId(double id) {
/*  62 */     this.id = id;
    }
    
    public Curso getCurso() {
/*  66 */     return this.curso;
    }
    
    public void setCurso(Curso curso) {
/*  70 */     this.curso = curso;
    }
    
    public Persona getPersona() {
/*  74 */     return this.persona;
    }
    
    public void setPersona(Persona persona) {
/*  78 */     this.persona = persona;
    }
    
    public double getNotaMaestro() {
/*  82 */     return this.notaMaestro;
    }
    
    public void setNotaMaestro(double notaMaestro) {
/*  86 */     this.notaMaestro = notaMaestro;
    }
    
    public double getNotaAsistencia() {
/*  90 */     return this.notaAsistencia;
    }
    
    public void setNotaAsistencia(double notaAsistencia) {
/*  94 */     this.notaAsistencia = notaAsistencia;
    }
    
    public double getNotaPractica() {
/*  98 */     return this.notaPractica;
    }
    
    public void setNotaPractica(double notaPractica) {
/* 102 */     this.notaPractica = notaPractica;
    }
    
    public double getNotaExamenFinal() {
/* 106 */     return this.notaExamenFinal;
    }
    
    public void setNotaExamenFinal(double notaExamenFinal) {
/* 110 */     this.notaExamenFinal = notaExamenFinal;
    }
    
    public double getNotaFinal() {
/* 114 */     return this.notaFinal;
    }
    
    public void setNotaFinal(double notaFinal) {
/* 118 */     this.notaFinal = notaFinal;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\NotasCurso.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */