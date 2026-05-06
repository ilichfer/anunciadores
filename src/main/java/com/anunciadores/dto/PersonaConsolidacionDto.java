  package  com.anunciadores.dto;
  
  import com.anunciadores.dto.AsignacionConsolidacionDto;
  import java.io.Serializable;
  import java.util.List;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  public class PersonaConsolidacionDto
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private String apellido;
    private Integer documento;
    private String telefono;
    private String fechanacimiento;
    private String tipodocumento;
    private String email;
    private String password;
    private String genero;
    private String estadoCivil;
    private String paisNacimiento;
    private String ciudad;
    private Boolean discapacidad;
    private String descDiscapacidad;
    private Boolean perteneceMinoria;
    private String descMinoria;
    private String direccion;
    private String ciudadDeptoDireccion;
    private String celular;
    private String ocupacion;
    private String escolaridad;
    private String fechaConvercionCristo;
    private String fechaLlegadaAdc;
    private String fechaBautizo;
    private String fechaBautizoEspiritu;
    private Boolean consolidacion;
    private List<AsignacionConsolidacionDto> asignacion;
    private Integer tamanoLista;
    
    public int getId() {
/*  67 */     return this.id;
    }
    
    public void setId(int id) {
/*  71 */     this.id = id;
    }
    
    public String getNombre() {
/*  75 */     return this.nombre;
    }
    
    public void setNombre(String nombre) {
/*  79 */     this.nombre = nombre;
    }
    
    public String getApellido() {
/*  83 */     return this.apellido;
    }
    
    public void setApellido(String apellido) {
/*  87 */     this.apellido = apellido;
    }
    
    public Integer getDocumento() {
/*  91 */     return this.documento;
    }
    
    public void setDocumento(Integer documento) {
/*  95 */     this.documento = documento;
    }
    
    public String getTelefono() {
/*  99 */     return this.telefono;
    }
    
    public void setTelefono(String telefono) {
/* 103 */     this.telefono = telefono;
    }
    
    public String getFechanacimiento() {
/* 107 */     return this.fechanacimiento;
    }
    
    public void setFechanacimiento(String fechanacimiento) {
/* 111 */     this.fechanacimiento = fechanacimiento;
    }
    
    public String getTipodocumento() {
/* 115 */     return this.tipodocumento;
    }
    
    public void setTipodocumento(String tipodocumento) {
/* 119 */     this.tipodocumento = tipodocumento;
    }
    
    public String getEmail() {
/* 123 */     return this.email;
    }
    
    public void setEmail(String email) {
/* 127 */     this.email = email;
    }
    
    public String getPassword() {
/* 131 */     return this.password;
    }
    
    public void setPassword(String password) {
/* 135 */     this.password = password;
    }
    
    public String getGenero() {
/* 139 */     return this.genero;
    }
    
    public void setGenero(String genero) {
/* 143 */     this.genero = genero;
    }
    
    public String getEstadoCivil() {
/* 147 */     return this.estadoCivil;
    }
    
    public void setEstadoCivil(String estadoCivil) {
/* 151 */     this.estadoCivil = estadoCivil;
    }
    
    public String getPaisNacimiento() {
/* 155 */     return this.paisNacimiento;
    }
    
    public void setPaisNacimiento(String paisNacimiento) {
/* 159 */     this.paisNacimiento = paisNacimiento;
    }
    
    public String getCiudad() {
/* 163 */     return this.ciudad;
    }
    
    public void setCiudad(String ciudad) {
/* 167 */     this.ciudad = ciudad;
    }
    
    public Boolean getDiscapacidad() {
/* 171 */     return this.discapacidad;
    }
    
    public void setDiscapacidad(Boolean discapacidad) {
/* 175 */     this.discapacidad = discapacidad;
    }
    
    public String getDescDiscapacidad() {
/* 179 */     return this.descDiscapacidad;
    }
    
    public void setDescDiscapacidad(String descDiscapacidad) {
/* 183 */     this.descDiscapacidad = descDiscapacidad;
    }
    
    public Boolean getPerteneceMinoria() {
/* 187 */     return this.perteneceMinoria;
    }
    
    public void setPerteneceMinoria(Boolean perteneceMinoria) {
/* 191 */     this.perteneceMinoria = perteneceMinoria;
    }
    
    public String getDescMinoria() {
/* 195 */     return this.descMinoria;
    }
    
    public void setDescMinoria(String descMinoria) {
/* 199 */     this.descMinoria = descMinoria;
    }
    
    public String getDireccion() {
/* 203 */     return this.direccion;
    }
    
    public void setDireccion(String direccion) {
/* 207 */     this.direccion = direccion;
    }
    
    public String getCiudadDeptoDireccion() {
/* 211 */     return this.ciudadDeptoDireccion;
    }
    
    public void setCiudadDeptoDireccion(String ciudadDeptoDireccion) {
/* 215 */     this.ciudadDeptoDireccion = ciudadDeptoDireccion;
    }
    
    public String getCelular() {
/* 219 */     return this.celular;
    }
    
    public void setCelular(String celular) {
/* 223 */     this.celular = celular;
    }
    
    public String getOcupacion() {
/* 227 */     return this.ocupacion;
    }
    
    public void setOcupacion(String ocupacion) {
/* 231 */     this.ocupacion = ocupacion;
    }
    
    public String getEscolaridad() {
/* 235 */     return this.escolaridad;
    }
    
    public void setEscolaridad(String escolaridad) {
/* 239 */     this.escolaridad = escolaridad;
    }
    
    public String getFechaConvercionCristo() {
/* 243 */     return this.fechaConvercionCristo;
    }
    
    public void setFechaConvercionCristo(String fechaConvercionCristo) {
/* 247 */     this.fechaConvercionCristo = fechaConvercionCristo;
    }
    
    public String getFechaLlegadaAdc() {
/* 251 */     return this.fechaLlegadaAdc;
    }
    
    public void setFechaLlegadaAdc(String fechaLlegadaAdc) {
/* 255 */     this.fechaLlegadaAdc = fechaLlegadaAdc;
    }
    
    public String getFechaBautizo() {
/* 259 */     return this.fechaBautizo;
    }
    
    public void setFechaBautizo(String fechaBautizo) {
/* 263 */     this.fechaBautizo = fechaBautizo;
    }
    
    public String getFechaBautizoEspiritu() {
/* 267 */     return this.fechaBautizoEspiritu;
    }
    
    public void setFechaBautizoEspiritu(String fechaBautizoEspiritu) {
/* 271 */     this.fechaBautizoEspiritu = fechaBautizoEspiritu;
    }
    
    public Boolean getConsolidacion() {
/* 275 */     return this.consolidacion;
    }
    
    public void setConsolidacion(Boolean consolidacion) {
/* 279 */     this.consolidacion = consolidacion;
    }
    
    public List<AsignacionConsolidacionDto> getAsignacion() {
/* 283 */     return this.asignacion;
    }
    
    public void setAsignacion(List<AsignacionConsolidacionDto> asignacion) {
/* 287 */     this.asignacion = asignacion;
    }
    
    public Integer getTamanoLista() {
/* 291 */     return this.tamanoLista;
    }
    
    public void setTamanoLista(Integer tamanoLista) {
/* 295 */     this.tamanoLista = tamanoLista;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\PersonaConsolidacionDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */