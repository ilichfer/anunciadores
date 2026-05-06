  package  com.anunciadores.dto;
  
  import java.io.Serializable;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  public class Clientes
    implements Serializable
  {
    private String tipoDocumento;
    private int Documento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String celular;
    private String direccion;
    private String email;
    
    public String getPrimerNombre() {
/*  43 */     return this.primerNombre;
    }
    
    public void setPrimerNombre(String primerNombre) {
/*  47 */     this.primerNombre = primerNombre;
    }
    
    public String getSegundoNombre() {
/*  51 */     return this.segundoNombre;
    }
    
    public void setSegundoNombre(String segundoNombre) {
/*  55 */     this.segundoNombre = segundoNombre;
    }
    
    public String getPrimerApellido() {
/*  59 */     return this.primerApellido;
    }
    
    public void setPrimerApellido(String primerApellido) {
/*  63 */     this.primerApellido = primerApellido;
    }
    
    public String getSegundoApellido() {
/*  67 */     return this.segundoApellido;
    }
    
    public void setSegundoApellido(String segundoApellido) {
/*  71 */     this.segundoApellido = segundoApellido;
    }
    
    public String getCelular() {
/*  75 */     return this.celular;
    }
    
    public void setCelular(String celular) {
/*  79 */     this.celular = celular;
    }
    
    public String getDireccion() {
/*  83 */     return this.direccion;
    }
    
    public void setDireccion(String direccion) {
/*  87 */     this.direccion = direccion;
    }
    
    public String getEmail() {
/*  91 */     return this.email;
    }
    
    public void setEmail(String email) {
/*  95 */     this.email = email;
    }
  
  
  
  
  
  
  
  
  
    
    public String getTipoDocumento() {
/* 108 */     return this.tipoDocumento;
    }
    
    public void setTipoDocumento(String tipoDocumento) {
/* 112 */     this.tipoDocumento = tipoDocumento;
    }
    
    public int getDocumento() {
/* 116 */     return this.Documento;
    }
    
    public void setDocumento(int documento) {
/* 120 */     this.Documento = documento;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\Clientes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */