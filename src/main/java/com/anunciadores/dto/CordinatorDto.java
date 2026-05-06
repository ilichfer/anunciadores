/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import java.time.LocalDate;
/*    */ 
/*    */ public class CordinatorDto
/*    */ {
/*    */   private String name;
/*    */   private LocalDate date;
/*    */   private int id;
/*    */   
/*    */   public CordinatorDto() {}
/*    */   
/*    */   public CordinatorDto(String name, LocalDate date, int id) {
/* 14 */     this.name = name;
/* 15 */     this.date = date;
/* 16 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 20 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 24 */     this.name = name;
/*    */   }
/*    */   
/*    */   public LocalDate getDate() {
/* 28 */     return this.date;
/*    */   }
/*    */   
/*    */   public void setDate(LocalDate date) {
/* 32 */     this.date = date;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 36 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 40 */     this.id = id;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\CordinatorDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */