/*    */ package  com.anunciadores.model;
/*    */ 
/*    */ import com.anunciadores.model.Persona;
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "coordinador")
/*    */ public class Coordinador
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "fecha_servicio")
/*    */   public Date fechaServicio;
/*    */   @Column(name = "notas_servicio")
/*    */   public String notasServicio;
/*    */   @OneToOne(cascade = {CascadeType.ALL})
/*    */   @JoinColumn(name = "id_persona", referencedColumnName = "id")
/*    */   private Persona persona;
/*    */   
/*    */   public int getId() {
/* 41 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 45 */     this.id = id;
/*    */   }
/*    */   
/*    */   public Date getFechaServicio() {
/* 49 */     return this.fechaServicio;
/*    */   }
/*    */   
/*    */   public void setFechaServicio(Date fechaServicio) {
/* 53 */     this.fechaServicio = fechaServicio;
/*    */   }
/*    */   
/*    */   public Persona getPersona() {
/* 57 */     return this.persona;
/*    */   }
/*    */   
/*    */   public void setPersona(Persona persona) {
/* 61 */     this.persona = persona;
/*    */   }
/*    */   
/*    */   public String getNotasServicio() {
/* 65 */     return this.notasServicio;
/*    */   }
/*    */   
/*    */   public void setNotasServicio(String notasServicio) {
/* 69 */     this.notasServicio = notasServicio;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\Coordinador.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */