/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.dto.PositionDto;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Ministry
/*    */ {
/*    */   private String id;
/*    */   private String name;
/*    */   private List<PositionDto> positions;
/*    */   
/*    */   public Ministry() {}
/*    */   
/*    */   public Ministry(String id, String name, List<PositionDto> positions) {
/* 15 */     this.id = id;
/* 16 */     this.name = name;
/* 17 */     this.positions = positions;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getId() {
/* 22 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(String id) {
/* 26 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 30 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 34 */     this.name = name;
/*    */   }
/*    */   
/*    */   public List<PositionDto> getPositions() {
/* 38 */     return this.positions;
/*    */   }
/*    */   
/*    */   public void setPositions(List<PositionDto> positions) {
/* 42 */     this.positions = positions;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     return "Ministry{id='" + this.id + "', name='" + this.name + "', positions=" + this.positions + "}";
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\Ministry.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */