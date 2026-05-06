/*    */ package  com.anunciadores.model;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "notas")
/*    */ public class Notas
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "idcurso")
/*    */   private Date idcurso;
/*    */   @Column(name = "nota")
/*    */   private int nota;
/*    */   @Column(name = "nomactividad")
/*    */   private String actividad;
/*    */   @Column(name = "idpersonafk")
/*    */   private int idpersona;
/*    */   
/*    */   public int getId() {
/* 53 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 57 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdpersona() {
/* 61 */     return this.idpersona;
/*    */   }
/*    */   
/*    */   public void setIdpersona(int idpersona) {
/* 65 */     this.idpersona = idpersona;
/*    */   }
/*    */   
/*    */   public Date getIdcurso() {
/* 69 */     return this.idcurso;
/*    */   }
/*    */   
/*    */   public void setIdcurso(Date idcurso) {
/* 73 */     this.idcurso = idcurso;
/*    */   }
/*    */   
/*    */   public int getNota() {
/* 77 */     return this.nota;
/*    */   }
/*    */   
/*    */   public void setNota(int nota) {
/* 81 */     this.nota = nota;
/*    */   }
/*    */   
/*    */   public String getActividad() {
/* 85 */     return this.actividad;
/*    */   }
/*    */   
/*    */   public void setActividad(String actividad) {
/* 89 */     this.actividad = actividad;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\Notas.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */