/*    */ package  com.anunciadores.dto;
/*    */ 
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
/*    */ public class PositionDto
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int id;
/*    */   private String name;
/*    */   
/*    */   public PositionDto() {}
/*    */   
/*    */   public int getId() {
/* 28 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 32 */     this.id = id;
/*    */   }
/*    */   
/*    */   public PositionDto(int id, String name) {
/* 36 */     this.id = id;
/* 37 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 41 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 45 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\PositionDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */