  package  com.anunciadores.dto;
  
  import com.anunciadores.dto.PersonaDto;
  import com.anunciadores.model.Persona;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  public class CursoDto
  {
    private int id;
    private int idPersona;
    private int idPago;
    private String nombreCurso;
    private String fechaInicio;
    private String fechaFin;
    private int valorTotal;
    private Boolean check;
    private PersonaDto personaAConsolidar;
    private int profesor;
    private Persona profesorDto;
    private boolean notas;
    
    public int getId() {
/*  43 */     return this.id;
    }
    
    public void setId(int id) {
/*  47 */     this.id = id;
    }
    
    public int getIdPersona() {
/*  51 */     return this.idPersona;
    }
    
    public void setIdPersona(int idPersona) {
/*  55 */     this.idPersona = idPersona;
    }
    
    public int getIdPago() {
/*  59 */     return this.idPago;
    }
    
    public void setIdPago(int idPago) {
/*  63 */     this.idPago = idPago;
    }
    
    public String getNombreCurso() {
/*  67 */     return this.nombreCurso;
    }
    
    public void setNombreCurso(String nombreCurso) {
/*  71 */     this.nombreCurso = nombreCurso;
    }
  
    
    public String getFechaInicio() {
/*  76 */     return this.fechaInicio;
    }
    
    public void setFechaInicio(String fechaInicio) {
/*  80 */     this.fechaInicio = fechaInicio;
    }
    
    public String getFechaFin() {
/*  84 */     return this.fechaFin;
    }
    
    public void setFechaFin(String fechaFin) {
/*  88 */     this.fechaFin = fechaFin;
    }
    
    public int getValorTotal() {
/*  92 */     return this.valorTotal;
    }
    
    public void setValorTotal(int valorTotal) {
/*  96 */     this.valorTotal = valorTotal;
    }
    
    public Boolean getCheck() {
/* 100 */     return this.check;
    }
    
    public void setCheck(Boolean check) {
/* 104 */     this.check = check;
    }
    
    public PersonaDto getPersonaAConsolidar() {
/* 108 */     return this.personaAConsolidar;
    }
    
    public void setPersonaAConsolidar(PersonaDto personaAConsolidar) {
/* 112 */     this.personaAConsolidar = personaAConsolidar;
    }
    
    public int getProfesor() {
/* 116 */     return this.profesor;
    }
    
    public void setProfesor(int profesor) {
/* 120 */     this.profesor = profesor;
    }
    
    public Persona getProfesorDto() {
/* 124 */     return this.profesorDto;
    }
    
    public void setProfesorDto(Persona profesorDto) {
/* 128 */     this.profesorDto = profesorDto;
    }
    
    public boolean isNotas() {
/* 132 */     return this.notas;
    }
    
    public void setNotas(boolean notas) {
/* 136 */     this.notas = notas;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\CursoDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */