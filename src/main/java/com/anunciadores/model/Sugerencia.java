/*    */ package  com.anunciadores.model;
/*    */ 
/*    */ import com.anunciadores.model.Persona;
/*    */ import com.sun.istack.NotNull;
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import javax.persistence.Basic;
/*    */ import javax.persistence.CascadeType;
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
/*    */ @Entity
/*    */ @Table(name = "sugerencia")
/*    */ public class Sugerencia
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @Basic(optional = false)
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   @NotNull
/*    */   private int id;
/*    */   @OneToOne(cascade = {CascadeType.ALL})
/*    */   @JoinColumn(name = "id_persona", referencedColumnName = "id")
/*    */   private Persona persona;
/*    */   @Column(name = "descripcion")
/*    */   private String descripcion;
/*    */   @Column(name = "fecha_registro")
/*    */   Date fechaRegistro;
/*    */   
/*    */   public int getId() {
/* 40 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 44 */     this.id = id;
/*    */   }
/*    */   
/*    */   public Persona getPersona() {
/* 48 */     return this.persona;
/*    */   }
/*    */   
/*    */   public void setPersona(Persona persona) {
/* 52 */     this.persona = persona;
/*    */   }
/*    */   
/*    */   public String getDescripcion() {
/* 56 */     return this.descripcion;
/*    */   }
/*    */   
/*    */   public void setDescripcion(String descripcion) {
/* 60 */     this.descripcion = descripcion;
/*    */   }
/*    */   
/*    */   public Date getFechaRegistro() {
/* 64 */     return this.fechaRegistro;
/*    */   }
/*    */   
/*    */   public void setFechaRegistro(Date fechaRegistro) {
/* 68 */     this.fechaRegistro = fechaRegistro;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\Sugerencia.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */