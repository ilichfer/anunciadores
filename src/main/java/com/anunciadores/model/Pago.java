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
/*    */ @Table(name = "pago")
/*    */ public class Pago
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private int id;
/*    */   @Column(name = "idpersona")
/*    */   private int idpersona;
/*    */   @Column(name = "fecha_Pago")
/*    */   private String fechaPago;
/*    */   @Column(name = "valor")
/*    */   private int valor;
/*    */   @Column(name = "id_curso")
/*    */   private int id_curso;
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
/*    */   public String getFechaPago() {
/* 69 */     return this.fechaPago;
/*    */   }
/*    */   
/*    */   public void setFechaPago(String fechaPago) {
/* 73 */     this.fechaPago = fechaPago;
/*    */   }
/*    */   
/*    */   public int getValor() {
/* 77 */     return this.valor;
/*    */   }
/*    */   
/*    */   public void setValor(int valor) {
/* 81 */     this.valor = valor;
/*    */   }
/*    */   
/*    */   public int getId_curso() {
/* 85 */     return this.id_curso;
/*    */   }
/*    */   
/*    */   public void setId_curso(int id_curso) {
/* 89 */     this.id_curso = id_curso;
/*    */   }
/*    */ 
/*    */   
/*    */   public Pago(int id, int idpersona, String fechaPago, int valor, int id_curso) {
/* 94 */     this.id = id;
/* 95 */     this.idpersona = idpersona;
/* 96 */     this.fechaPago = fechaPago;
/* 97 */     this.valor = valor;
/* 98 */     this.id_curso = id_curso;
/*    */   }
/*    */   
/*    */   public Pago() {}
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\Pago.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */