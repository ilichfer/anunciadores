/*    */ package  com.anunciadores.model;
/*    */ 
/*    */ import com.anunciadores.model.Persona;
/*    */ import java.io.Serializable;
/*    */ import java.time.LocalDate;
/*    */ import java.time.LocalTime;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.ManyToOne;
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
/*    */ @Entity
/*    */ @Table(name = "tdc")
/*    */ public class TimeSlot
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   private Long id;
/*    */   private LocalDate date;
/*    */   private LocalTime startTime;
/*    */   private boolean reserved;
/*    */   private boolean diaSiguiente;
/*    */   @ManyToOne
/*    */   private Persona reservedBy;
/*    */   
/*    */   public Long getId() {
/* 40 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Long id) {
/* 44 */     this.id = id;
/*    */   }
/*    */   
/*    */   public LocalDate getDate() {
/* 48 */     return this.date;
/*    */   }
/*    */   
/*    */   public void setDate(LocalDate date) {
/* 52 */     this.date = date;
/*    */   }
/*    */   
/*    */   public LocalTime getStartTime() {
/* 56 */     return this.startTime;
/*    */   }
/*    */   
/*    */   public void setStartTime(LocalTime startTime) {
/* 60 */     this.startTime = startTime;
/*    */   }
/*    */   
/*    */   public boolean isReserved() {
/* 64 */     return this.reserved;
/*    */   }
/*    */   
/*    */   public void setReserved(boolean reserved) {
/* 68 */     this.reserved = reserved;
/*    */   }
/*    */   
/*    */   public Persona getReservedBy() {
/* 72 */     return this.reservedBy;
/*    */   }
/*    */   
/*    */   public void setReservedBy(Persona reservedBy) {
/* 76 */     this.reservedBy = reservedBy;
/*    */   }
/*    */   
/*    */   public boolean isDiaSiguiente() {
/* 80 */     return this.diaSiguiente;
/*    */   }
/*    */   
/*    */   public void setDiaSiguiente(boolean diaSiguiente) {
/* 84 */     this.diaSiguiente = diaSiguiente;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\model\TimeSlot.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */