/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.dto.MinistryMember;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class MinistryDto
/*    */ {
/*    */   private Integer id;
/*    */   private String name;
/*    */   private List<MinistryMember> positions;
/*    */   
/*    */   public MinistryDto() {}
/*    */   
/*    */   public MinistryDto(Integer id, String name, List<MinistryMember> positions) {
/* 16 */     this.id = id;
/* 17 */     this.name = name;
/* 18 */     this.positions = positions;
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getId() {
/* 23 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Integer id) {
/* 27 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 31 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 35 */     this.name = name;
/*    */   }
/*    */   
/*    */   public List<MinistryMember> getPositions() {
/* 39 */     return this.positions;
/*    */   }
/*    */   
/*    */   public void setPositions(List<MinistryMember> positions) {
/* 43 */     this.positions = positions;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     return "Ministry{id='" + this.id + "', name='" + this.name + "', positions=" + this.positions + "}";
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\MinistryDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */