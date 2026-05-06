/*    */ package  com.anunciadores.model;
/*    */ 
/*    */ import com.sun.istack.NotNull;
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Basic;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
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
/*    */ @Table(name = "persona_mesa")
/*    */ public class MesaPersona
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @Basic(optional = false)
/*    */   @Column(name = "id")
/*    */   @NotNull
/*    */   private int id;
/*    */   @Column(name = "id_mesa")
/*    */   private int idMesa;
/*    */   @Column(name = "id_persona")
/*    */   private String idPersona;
/*    */   
/*    */   public int getId() {
/* 47 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 51 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdMesa() {
/* 55 */     return this.idMesa;
/*    */   }
/*    */   
/*    */   public void setIdMesa(int idMesa) {
/* 59 */     this.idMesa = idMesa;
/*    */   }
/*    */   
/*    */   public String getIdPersona() {
/* 63 */     return this.idPersona;
/*    */   }
/*    */   
/*    */   public void setIdPersona(String idPersona) {
/* 67 */     this.idPersona = idPersona;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\MesaPersona.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */