  package  com.anunciadores.dto;
  
  import com.anunciadores.dto.ItemCombo;
  import java.io.Serializable;
  import java.util.List;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  public class ServicioResponseDto
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    private String posicion;
    private String encargado;
    private String fechaServcio;
    private String nombreMinisterio;
    private Integer idMinisterio;
    private String celular;
    private String idNotificacionTelegram;
    private List<ItemCombo> asistenciaList;
    
    public String getPosicion() {
/*  35 */     return this.posicion;
    }
    
    public void setPosicion(String posicion) {
/*  39 */     this.posicion = posicion;
    }
    
    public String getEncargado() {
/*  43 */     return this.encargado;
    }
    
    public void setEncargado(String encargado) {
/*  47 */     this.encargado = encargado;
    }
    
    public String getFechaServcio() {
/*  51 */     return this.fechaServcio;
    }
    
    public void setFechaServcio(String fechaServcio) {
/*  55 */     this.fechaServcio = fechaServcio;
    }
    
    public String getNombreMinisterio() {
/*  59 */     return this.nombreMinisterio;
    }
    
    public void setNombreMinisterio(String nombreMinisterio) {
/*  63 */     this.nombreMinisterio = nombreMinisterio;
    }
    
    public Integer getIdMinisterio() {
/*  67 */     return this.idMinisterio;
    }
    
    public void setIdMinisterio(Integer idMinisterio) {
/*  71 */     this.idMinisterio = idMinisterio;
    }
    
    public List<ItemCombo> getAsistenciaList() {
/*  75 */     return this.asistenciaList;
    }
    
    public void setAsistenciaList(List<ItemCombo> asistenciaList) {
/*  79 */     this.asistenciaList = asistenciaList;
    }
    
    public ServicioResponseDto(String posicion, String encargado, String fechaServcio, String nombreMinisterio, Integer idMinisterio) {
/*  83 */     this.posicion = posicion;
/*  84 */     this.encargado = encargado;
/*  85 */     this.fechaServcio = fechaServcio;
/*  86 */     this.nombreMinisterio = nombreMinisterio;
/*  87 */     this.idMinisterio = idMinisterio;
    }
  
    
    public ServicioResponseDto() {}
    
    public String getIdNotificacionTelegram() {
/*  94 */     return this.idNotificacionTelegram;
    }
    
    public void setIdNotificacionTelegram(String idNotificacionTelegram) {
/*  98 */     this.idNotificacionTelegram = idNotificacionTelegram;
    }
    
    public String getCelular() {
/* 102 */     return this.celular;
    }
    
    public void setCelular(String celular) {
/* 106 */     this.celular = celular;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\ServicioResponseDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */