  package  com.anunciadores.dto;
  
  import com.anunciadores.dto.AsignacionServicioDTO;
  import com.anunciadores.dto.MensajesDTO;
  import com.anunciadores.dto.NotasCursoDTO;
  import com.anunciadores.model.PermisosMenu;
  import com.anunciadores.model.Rol;
  import java.io.Serializable;
  import java.util.Date;
  import java.util.List;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  public class PersonaDto
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
    private boolean consolidacion;
    private List<Rol> roles;
    private List<PermisosMenu> permisosMenu;
    private List<MensajesDTO> mensajes;
    private List<AsignacionServicioDTO> asignacion;
    private Rol rolUnico;
    private boolean admin;
    private boolean user;
    private boolean coordinadorActual;
    private Date fechaCoordinador;
    private boolean validarPago;
    private boolean cumpleActual;
    private NotasCursoDTO notas;
    private Boolean selecionado;
    
    public Boolean getSelecionado() {
/*  63 */     return this.selecionado;
    }
    
    public void setSelecionado(Boolean selecionado) {
/*  67 */     this.selecionado = selecionado;
    }
  
    
    public PersonaDto() {}
    
    public int getId() {
/*  74 */     return this.id;
    }
    
    public void setId(int id) {
/*  78 */     this.id = id;
    }
    
    public String getNombre() {
/*  82 */     return this.nombre;
    }
    
    public void setNombre(String nombre) {
/*  86 */     this.nombre = nombre;
    }
    
    public String getApellido() {
/*  90 */     return this.apellido;
    }
    
    public void setApellido(String apellido) {
/*  94 */     this.apellido = apellido;
    }
    
    public Integer getDocumento() {
/*  98 */     return this.documento;
    }
    
    public void setDocumento(Integer documento) {
/* 102 */     this.documento = documento;
    }
    
    public String getTelefono() {
/* 106 */     return this.telefono;
    }
    
    public void setTelefono(String telefono) {
/* 110 */     this.telefono = telefono;
    }
    
    public String getFechanacimiento() {
/* 114 */     return this.fechanacimiento;
    }
    
    public void setFechanacimiento(String fechanacimiento) {
/* 118 */     this.fechanacimiento = fechanacimiento;
    }
    
    public String getTipodocumento() {
/* 122 */     return this.tipodocumento;
    }
    
    public void setTipodocumento(String tipodocumento) {
/* 126 */     this.tipodocumento = tipodocumento;
    }
    
    public String getEmail() {
/* 130 */     return this.email;
    }
    
    public void setEmail(String email) {
/* 134 */     this.email = email;
    }
    
    public String getPassword() {
/* 138 */     return this.password;
    }
    
    public void setPassword(String password) {
/* 142 */     this.password = password;
    }
  
  
  
    
    public PersonaDto(int id, String nombre, String apellido, Integer documento, String telefono, String fechanacimiento, String tipodocumento, String email, String password, boolean consolidacion, List<Rol> roles, List<PermisosMenu> permisosMenu, Rol rolUnico, boolean admin, boolean user, boolean validarPago) {
/* 149 */     this.id = id;
/* 150 */     this.nombre = nombre;
/* 151 */     this.apellido = apellido;
/* 152 */     this.documento = documento;
/* 153 */     this.telefono = telefono;
/* 154 */     this.fechanacimiento = fechanacimiento;
/* 155 */     this.tipodocumento = tipodocumento;
/* 156 */     this.email = email;
/* 157 */     this.password = password;
/* 158 */     this.consolidacion = consolidacion;
/* 159 */     this.roles = roles;
/* 160 */     this.permisosMenu = permisosMenu;
/* 161 */     this.rolUnico = rolUnico;
/* 162 */     this.admin = admin;
/* 163 */     this.user = user;
/* 164 */     this.validarPago = validarPago;
    }
    
    public List<Rol> getRoles() {
/* 168 */     return this.roles;
    }
    
    public void setRoles(List<Rol> roles) {
/* 172 */     this.roles = roles;
    }
    
    public boolean isAdmin() {
/* 176 */     return this.admin;
    }
    
    public void setAdmin(boolean admin) {
/* 180 */     this.admin = admin;
    }
    
    public boolean isUser() {
/* 184 */     return this.user;
    }
    
    public void setUser(boolean user) {
/* 188 */     this.user = user;
    }
    
    public boolean isConsolidacion() {
/* 192 */     return this.consolidacion;
    }
    
    public void setConsolidacion(boolean consolidacion) {
/* 196 */     this.consolidacion = consolidacion;
    }
    
    public boolean isValidarPago() {
/* 200 */     return this.validarPago;
    }
    
    public void setValidarPago(boolean validarPago) {
/* 204 */     this.validarPago = validarPago;
    }
    
    public Rol getRolUnico() {
/* 208 */     return this.rolUnico;
    }
    
    public void setRolUnico(Rol rolUnico) {
/* 212 */     this.rolUnico = rolUnico;
    }
    
    public List<PermisosMenu> getPermisosMenu() {
/* 216 */     return this.permisosMenu;
    }
    
    public void setPermisosMenu(List<PermisosMenu> permisosMenu) {
/* 220 */     this.permisosMenu = permisosMenu;
    }
    
    public boolean isCoordinadorActual() {
/* 224 */     return this.coordinadorActual;
    }
    
    public void setCoordinadorActual(boolean coordinadorActual) {
/* 228 */     this.coordinadorActual = coordinadorActual;
    }
    
    public Date getFechaCoordinador() {
/* 232 */     return this.fechaCoordinador;
    }
    
    public void setFechaCoordinador(Date fechaCoordinador) {
/* 236 */     this.fechaCoordinador = fechaCoordinador;
    }
    
    public boolean isCumpleActual() {
/* 240 */     return this.cumpleActual;
    }
    
    public void setCumpleActual(boolean cumpleActual) {
/* 244 */     this.cumpleActual = cumpleActual;
    }
    
    public NotasCursoDTO getNotas() {
/* 248 */     return this.notas;
    }
    
    public void setNotas(NotasCursoDTO notas) {
/* 252 */     this.notas = notas;
    }
    
    public List<MensajesDTO> getMensajes() {
/* 256 */     return this.mensajes;
    }
    
    public void setMensajes(List<MensajesDTO> mensajes) {
/* 260 */     this.mensajes = mensajes;
    }
    
    public List<AsignacionServicioDTO> getAsignacion() {
/* 264 */     return this.asignacion;
    }
    
    public void setAsignacion(List<AsignacionServicioDTO> asignacion) {
/* 268 */     this.asignacion = asignacion;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\PersonaDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */