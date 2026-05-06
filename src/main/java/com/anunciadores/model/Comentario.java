/*    */ package  com.anunciadores.model;
/*    */ 
/*    */ import com.sun.istack.NotNull;
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Basic;
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
/*    */ @Entity
/*    */ @Table(name = "comentario")
/*    */ public class Comentario
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @Basic(optional = false)
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   @NotNull
/*    */   private int id;
/*    */   @Column(name = "id_curso")
/*    */   private int idCurso;
/*    */   @Column(name = "id_persona")
/*    */   private int idPersona;
/*    */   @Column(name = "fecha")
/*    */   private String fecha;
/*    */   @Column(name = "comentario")
/*    */   private String comentario;
/*    */   
/*    */   public int getId() {
/* 54 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 58 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdCurso() {
/* 62 */     return this.idCurso;
/*    */   }
/*    */   
/*    */   public void setIdCurso(int idCurso) {
/* 66 */     this.idCurso = idCurso;
/*    */   }
/*    */   
/*    */   public int getIdPersona() {
/* 70 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(int idPersona) {
/* 74 */     this.idPersona = idPersona;
/*    */   }
/*    */   
/*    */   public String getFecha() {
/* 78 */     return this.fecha;
/*    */   }
/*    */   
/*    */   public void setFecha(String fecha) {
/* 82 */     this.fecha = fecha;
/*    */   }
/*    */   
/*    */   public String getComentario() {
/* 86 */     return this.comentario;
/*    */   }
/*    */   
/*    */   public void setComentario(String comentario) {
/* 90 */     this.comentario = comentario;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\Comentario.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */