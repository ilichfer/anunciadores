/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.model.Curso;
/*    */ import java.io.Serializable;
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
/*    */ public class NotasCursoDTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private double id;
/*    */   private Curso curso;
/*    */   private double notaMaestro;
/*    */   private double notaAsistencia;
/*    */   private double notaPractica;
/*    */   private double notaExamenFinal;
/*    */   private double notaFinal;
/*    */   private int colorCelda;
/*    */   
/*    */   public double getId() {
/* 38 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(double id) {
/* 42 */     this.id = id;
/*    */   }
/*    */   
/*    */   public Curso getCurso() {
/* 46 */     return this.curso;
/*    */   }
/*    */   
/*    */   public void setCurso(Curso curso) {
/* 50 */     this.curso = curso;
/*    */   }
/*    */   
/*    */   public double getNotaMaestro() {
/* 54 */     return this.notaMaestro;
/*    */   }
/*    */   
/*    */   public void setNotaMaestro(double notaMaestro) {
/* 58 */     this.notaMaestro = notaMaestro;
/*    */   }
/*    */   
/*    */   public double getNotaAsistencia() {
/* 62 */     return this.notaAsistencia;
/*    */   }
/*    */   
/*    */   public void setNotaAsistencia(double notaAsistencia) {
/* 66 */     this.notaAsistencia = notaAsistencia;
/*    */   }
/*    */   
/*    */   public double getNotaPractica() {
/* 70 */     return this.notaPractica;
/*    */   }
/*    */   
/*    */   public void setNotaPractica(double notaPractica) {
/* 74 */     this.notaPractica = notaPractica;
/*    */   }
/*    */   
/*    */   public double getNotaExamenFinal() {
/* 78 */     return this.notaExamenFinal;
/*    */   }
/*    */   
/*    */   public void setNotaExamenFinal(double notaExamenFinal) {
/* 82 */     this.notaExamenFinal = notaExamenFinal;
/*    */   }
/*    */   
/*    */   public double getNotaFinal() {
/* 86 */     return this.notaFinal;
/*    */   }
/*    */   
/*    */   public void setNotaFinal(double notaFinal) {
/* 90 */     this.notaFinal = notaFinal;
/*    */   }
/*    */   
/*    */   public int getColorCelda() {
/* 94 */     return this.colorCelda;
/*    */   }
/*    */   
/*    */   public void setColorCelda(int colorCelda) {
/* 98 */     this.colorCelda = colorCelda;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\NotasCursoDTO.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */