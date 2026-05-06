  package  com.anunciadores.model;
  
  import java.io.Serializable;
  import java.sql.Date;
  import javax.persistence.Column;
  import javax.persistence.Entity;
  import javax.persistence.GeneratedValue;
  import javax.persistence.GenerationType;
  import javax.persistence.Id;
  import javax.persistence.Lob;
  import javax.persistence.Table;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  @Entity
  @Table(name = "tdc")
  public class Tdc
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Lob
    @Column(name = "tdc", columnDefinition = "MEDIUMBLOB")
    private String tdc;
    @Column(name = "fecha_creacion")
    Date fechaCreacion;
    @Column(name = "id_persona")
    int idPersona;
    @Column(name = "nombre_documento")
    String nombredocumento;
    @Column(name = "pdf")
    Boolean pdf;
    @Column(name = "imagen")
    Boolean imagen;
    @Column(name = "url_image")
    String urlImage;
    
    public String getUrlImage() {
/*  55 */     return this.urlImage;
    }
    
    public void setUrlImage(String urlImage) {
/*  59 */     this.urlImage = urlImage;
    }
    
    public int getId() {
/*  63 */     return this.id;
    }
    
    public void setId(int id) {
/*  67 */     this.id = id;
    }
    
    public String getTdc() {
/*  71 */     return this.tdc;
    }
    
    public void setTdc(String tdc) {
/*  75 */     this.tdc = tdc;
    }
    
    public Date getFechaCreacion() {
/*  79 */     return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
/*  83 */     this.fechaCreacion = fechaCreacion;
    }
    
    public int getIdPersona() {
/*  87 */     return this.idPersona;
    }
    
    public void setIdPersona(int idPersona) {
/*  91 */     this.idPersona = idPersona;
    }
    
    public String getNombredocumento() {
/*  95 */     return this.nombredocumento;
    }
    
    public void setNombredocumento(String nombredocumento) {
/*  99 */     this.nombredocumento = nombredocumento;
    }
    
    public Boolean getPdf() {
/* 103 */     return this.pdf;
    }
    
    public void setPdf(Boolean pdf) {
/* 107 */     this.pdf = pdf;
    }
    
    public Boolean getImagen() {
/* 111 */     return this.imagen;
    }
    
    public void setImagen(Boolean imagen) {
/* 115 */     this.imagen = imagen;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\Tdc.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */