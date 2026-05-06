  package  com.anunciadores.dto;
  
  import java.io.Serializable;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  public class AsignacionConsolidacionDto
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    private Integer idConsolidacion;
    private String nombre;
    private String telefono;
    private String apellido;
    private Integer documento;
    private int idPersona;
    private Boolean aceptaConsolidacion;
    private String horarioConsolidacionSugerido;
    private String horarioConsolidacionPersona;
    
    public String getNombre() {
/*  41 */     return this.nombre;
    }
    
    public void setNombre(String nombre) {
/*  45 */     this.nombre = nombre;
    }
    
    public String getApellido() {
/*  49 */     return this.apellido;
    }
    
    public void setApellido(String apellido) {
/*  53 */     this.apellido = apellido;
    }
    
    public Integer getDocumento() {
/*  57 */     return this.documento;
    }
    
    public void setDocumento(Integer documento) {
/*  61 */     this.documento = documento;
    }
    
    public int getIdPersona() {
/*  65 */     return this.idPersona;
    }
    
    public void setIdPersona(int idPersona) {
/*  69 */     this.idPersona = idPersona;
    }
    
    public Boolean getAceptaConsolidacion() {
/*  73 */     return this.aceptaConsolidacion;
    }
    
    public void setAceptaConsolidacion(Boolean aceptaConsolidacion) {
/*  77 */     this.aceptaConsolidacion = aceptaConsolidacion;
    }
    
    public String getHorarioConsolidacionSugerido() {
/*  81 */     return this.horarioConsolidacionSugerido;
    }
    
    public void setHorarioConsolidacionSugerido(String horarioConsolidacionSugerido) {
/*  85 */     this.horarioConsolidacionSugerido = horarioConsolidacionSugerido;
    }
    
    public String getHorarioConsolidacionPersona() {
/*  89 */     return this.horarioConsolidacionPersona;
    }
    
    public void setHorarioConsolidacionPersona(String horarioConsolidacionPersona) {
/*  93 */     this.horarioConsolidacionPersona = horarioConsolidacionPersona;
    }
    
    public String getTelefono() {
/*  97 */     return this.telefono;
    }
    
    public void setTelefono(String telefono) {
/* 101 */     this.telefono = telefono;
    }
    
    public Integer getIdConsolidacion() {
/* 105 */     return this.idConsolidacion;
    }
    
    public void setIdConsolidacion(Integer idCosolidacion) {
/* 109 */     this.idConsolidacion = idCosolidacion;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\AsignacionConsolidacionDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */