/*    */ package  com.anunciadores.model;
/*    */ 
/*    */ import com.anunciadores.model.Persona;
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.OneToOne;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "mensajes")
/*    */ public class Mensajes
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "descripcion")
/*    */   private String descripcion;
/*    */   @OneToOne
/*    */   @JoinColumn(name = "remitente", referencedColumnName = "id")
/*    */   private Persona remitente;
/*    */   @OneToOne
/*    */   @JoinColumn(name = "destinatario", referencedColumnName = "id")
/*    */   private Persona destinatario;
/*    */   @Column(name = "fecha_registro")
/*    */   Date fechaRegistro;
/*    */   @Column(name = "activo")
/*    */   private boolean activo;
/*    */   
/*    */   public int getId() {
/* 53 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 57 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getDescripcion() {
/* 61 */     return this.descripcion;
/*    */   }
/*    */   
/*    */   public void setDescripcion(String descripcion) {
/* 65 */     this.descripcion = descripcion;
/*    */   }
/*    */   
/*    */   public Persona getRemitente() {
/* 69 */     return this.remitente;
/*    */   }
/*    */   
/*    */   public void setRemitente(Persona remitente) {
/* 73 */     this.remitente = remitente;
/*    */   }
/*    */   
/*    */   public Persona getDestinatario() {
/* 77 */     return this.destinatario;
/*    */   }
/*    */   
/*    */   public void setDestinatario(Persona destinatario) {
/* 81 */     this.destinatario = destinatario;
/*    */   }
/*    */   
/*    */   public Date getFechaRegistro() {
/* 85 */     return this.fechaRegistro;
/*    */   }
/*    */   
/*    */   public void setFechaRegistro(Date fechaRegistro) {
/* 89 */     this.fechaRegistro = fechaRegistro;
/*    */   }
/*    */   
/*    */   public boolean isActivo() {
/* 93 */     return this.activo;
/*    */   }
/*    */   
/*    */   public void setActivo(boolean activo) {
/* 97 */     this.activo = activo;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\Mensajes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */