  package  com.anunciadores.model;
  
  import java.io.Serializable;
  import javax.persistence.Column;
  import javax.persistence.Entity;
  import javax.persistence.GeneratedValue;
  import javax.persistence.GenerationType;
  import javax.persistence.Id;
  import javax.persistence.Table;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  @Entity
  @Table(name = "persona")
  public class Persona
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "documento")
    private Integer documento;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "fechanacimiento")
    private String fechanacimiento;
    @Column(name = "tipodocumento")
    private String tipodocumento;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "genero")
    private String genero;
    @Column(name = "estado_civil")
    private String estadoCivil;
    @Column(name = "pais_nacimiento")
    private String paisNacimiento;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "discapacidad")
    private Boolean discapacidad;
    @Column(name = "descripcion_discapacidad")
    private String descDiscapacidad;
    @Column(name = "pertenece_minoria")
    private Boolean perteneceMinoria;
    @Column(name = "descripcion_minoria")
    private String descMinoria;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "ciudad_depto_direccion")
    private String ciudadDeptoDireccion;
    @Column(name = "celular")
    private String celular;
    @Column(name = "ocupacion")
    private String ocupacion;
    @Column(name = "escolaridad")
    private String escolaridad;
    @Column(name = "fecha_convercion_cristo")
    private String fechaConvercionCristo;
    @Column(name = "fecha_llegada_adc")
    private String fechaLlegadaAdc;
    @Column(name = "fecha_bautizo")
    private String fechaBautizo;
    @Column(name = "fecha_bautizo_espiritu")
    private String fechaBautizoEspiritu;
    @Column(name = "id_telegram")
    private String idTelegram;
    @Column(name = "consolidacion")
    private Boolean consolidacion;
    @Column(name = "estado")
    private Boolean estado;
    
    public Integer getId() {
/* 111 */     return this.id;
    }
    
    public void setId(Integer id) {
/* 115 */     this.id = id;
    }
    
    public String getNombre() {
/* 119 */     return this.nombre;
    }
    
    public void setNombre(String nombre) {
/* 123 */     this.nombre = nombre;
    }
    
    public String getApellido() {
/* 127 */     return this.apellido;
    }
    
    public void setApellido(String apellido) {
/* 131 */     this.apellido = apellido;
    }
    
    public Integer getDocumento() {
/* 135 */     return this.documento;
    }
    
    public void setDocumento(Integer documento) {
/* 139 */     this.documento = documento;
    }
    
    public String getTelefono() {
/* 143 */     return this.telefono;
    }
    
    public void setTelefono(String telefono) {
/* 147 */     this.telefono = telefono;
    }
    
    public String getFechanacimiento() {
/* 151 */     return this.fechanacimiento;
    }
    
    public void setFechanacimiento(String fechanacimiento) {
/* 155 */     this.fechanacimiento = fechanacimiento;
    }
    
    public String getTipodocumento() {
/* 159 */     return this.tipodocumento;
    }
    
    public void setTipodocumento(String tipodocumento) {
/* 163 */     this.tipodocumento = tipodocumento;
    }
    
    public String getEmail() {
/* 167 */     return this.email;
    }
    
    public void setEmail(String email) {
/* 171 */     this.email = email;
    }
    
    public String getPassword() {
/* 175 */     return this.password;
    }
    
    public void setPassword(String password) {
/* 179 */     this.password = password;
    }
    
    public String getGenero() {
/* 183 */     return this.genero;
    }
    
    public void setGenero(String genero) {
/* 187 */     this.genero = genero;
    }
    
    public String getEstadoCivil() {
/* 191 */     return this.estadoCivil;
    }
    
    public void setEstadoCivil(String estadoCivil) {
/* 195 */     this.estadoCivil = estadoCivil;
    }
    
    public String getPaisNacimiento() {
/* 199 */     return this.paisNacimiento;
    }
    
    public void setPaisNacimiento(String paisNacimiento) {
/* 203 */     this.paisNacimiento = paisNacimiento;
    }
    
    public String getCiudad() {
/* 207 */     return this.ciudad;
    }
    
    public void setCiudad(String ciudad) {
/* 211 */     this.ciudad = ciudad;
    }
    
    public Boolean getDiscapacidad() {
/* 215 */     return this.discapacidad;
    }
    
    public void setDiscapacidad(Boolean discapacidad) {
/* 219 */     this.discapacidad = discapacidad;
    }
    
    public Boolean getPerteneceMinoria() {
/* 223 */     return this.perteneceMinoria;
    }
    
    public void setPerteneceMinoria(Boolean perteneceMinoria) {
/* 227 */     this.perteneceMinoria = perteneceMinoria;
    }
    
    public boolean isDiscapacidad() {
/* 231 */     return this.discapacidad.booleanValue();
    }
    
    public void setDiscapacidad(boolean discapacidad) {
/* 235 */     this.discapacidad = Boolean.valueOf(discapacidad);
    }
    
    public String getDescDiscapacidad() {
/* 239 */     return this.descDiscapacidad;
    }
    
    public void setDescDiscapacidad(String descDiscapacidad) {
/* 243 */     this.descDiscapacidad = descDiscapacidad;
    }
    
    public boolean isPerteneceMinoria() {
/* 247 */     return this.perteneceMinoria.booleanValue();
    }
    
    public void setPerteneceMinoria(boolean perteneceMinoria) {
/* 251 */     this.perteneceMinoria = Boolean.valueOf(perteneceMinoria);
    }
    
    public String getDescMinoria() {
/* 255 */     return this.descMinoria;
    }
    
    public void setDescMinoria(String descMinoria) {
/* 259 */     this.descMinoria = descMinoria;
    }
    
    public String getDireccion() {
/* 263 */     return this.direccion;
    }
    
    public void setDireccion(String direccion) {
/* 267 */     this.direccion = direccion;
    }
    
    public String getCiudadDeptoDireccion() {
/* 271 */     return this.ciudadDeptoDireccion;
    }
    
    public void setCiudadDeptoDireccion(String ciudadDeptoDireccion) {
/* 275 */     this.ciudadDeptoDireccion = ciudadDeptoDireccion;
    }
    
    public String getCelular() {
/* 279 */     return this.celular;
    }
    
    public void setCelular(String celular) {
/* 283 */     this.celular = celular;
    }
    
    public String getOcupacion() {
/* 287 */     return this.ocupacion;
    }
    
    public void setOcupacion(String ocupacion) {
/* 291 */     this.ocupacion = ocupacion;
    }
    
    public String getEscolaridad() {
/* 295 */     return this.escolaridad;
    }
    
    public void setEscolaridad(String escolaridad) {
/* 299 */     this.escolaridad = escolaridad;
    }
    
    public String getFechaConvercionCristo() {
/* 303 */     return this.fechaConvercionCristo;
    }
    
    public void setFechaConvercionCristo(String fechaConvercionCristo) {
/* 307 */     this.fechaConvercionCristo = fechaConvercionCristo;
    }
    
    public String getFechaLlegadaAdc() {
/* 311 */     return this.fechaLlegadaAdc;
    }
    
    public void setFechaLlegadaAdc(String fechaLlegadaAdc) {
/* 315 */     this.fechaLlegadaAdc = fechaLlegadaAdc;
    }
    
    public String getFechaBautizo() {
/* 319 */     return this.fechaBautizo;
    }
    
    public void setFechaBautizo(String fechaBautizo) {
/* 323 */     this.fechaBautizo = fechaBautizo;
    }
    
    public String getFechaBautizoEspiritu() {
/* 327 */     return this.fechaBautizoEspiritu;
    }
    
    public void setFechaBautizoEspiritu(String fechaBautizoEspiritu) {
/* 331 */     this.fechaBautizoEspiritu = fechaBautizoEspiritu;
    }
    
    public Boolean getConsolidacion() {
/* 335 */     return this.consolidacion;
    }
    
    public void setConsolidacion(Boolean consolidacion) {
/* 339 */     this.consolidacion = consolidacion;
    }
    
    public Boolean getEstado() {
/* 343 */     return this.estado;
    }
    
    public void setEstado(Boolean estado) {
/* 347 */     this.estado = estado;
    }
    
    public String getIdTelegram() {
/* 351 */     return this.idTelegram;
    }
    
    public void setIdTelegram(String idTelegram) {
/* 355 */     this.idTelegram = idTelegram;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\Persona.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */