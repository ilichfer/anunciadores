/*    */ package  com.anunciadores.model;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ @Entity
/*    */ @Table(name = "consolidacion")
/*    */ public class Consolidacion
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "id_persona")
/*    */   private int idPersona;
/*    */   @Column(name = "acepta_consolidacion")
/*    */   private Boolean aceptaConsolidacion;
/*    */   @Column(name = "horario_consolidacion_sugerido")
/*    */   private String horarioConsolidacionSugerido;
/*    */   @Column(name = "horario_consolidacion_persona")
/*    */   private String horarioConsolidacionPersona;
/*    */   
/*    */   public int getId() {
/* 36 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 40 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdPersona() {
/* 44 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(int idPersona) {
/* 48 */     this.idPersona = idPersona;
/*    */   }
/*    */   
/*    */   public String getHorarioConsolidacionSugerido() {
/* 52 */     return this.horarioConsolidacionSugerido;
/*    */   }
/*    */   
/*    */   public void setHorarioConsolidacionSugerido(String horarioConsolidacionSugerido) {
/* 56 */     this.horarioConsolidacionSugerido = horarioConsolidacionSugerido;
/*    */   }
/*    */   
/*    */   public String getHorarioConsolidacionPersona() {
/* 60 */     return this.horarioConsolidacionPersona;
/*    */   }
/*    */   
/*    */   public void setHorarioConsolidacionPersona(String horarioConsolidacionPersona) {
/* 64 */     this.horarioConsolidacionPersona = horarioConsolidacionPersona;
/*    */   }
/*    */   
/*    */   public Boolean getAceptaConsolidacion() {
/* 68 */     return this.aceptaConsolidacion;
/*    */   }
/*    */   
/*    */   public void setAceptaConsolidacion(Boolean aceptaConsolidacion) {
/* 72 */     this.aceptaConsolidacion = aceptaConsolidacion;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\Consolidacion.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */