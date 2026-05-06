/*    */ package  com.anunciadores.auth.dto;
/*    */ 
/*    */ import com.anunciadores.auth.dto.assignments;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class updateServiceRequest
/*    */ {
/*    */   private String date;
/*    */   private int ministryId;
/*    */   private String ministry;
/*    */   private List<assignments> assignments;
/*    */   
/*    */   public String getDate() {
/* 15 */     return this.date;
/*    */   }
/*    */   
/*    */   public void setDate(String date) {
/* 19 */     this.date = date;
/*    */   }
/*    */   
/*    */   public int getMinistryId() {
/* 23 */     return this.ministryId;
/*    */   }
/*    */   
/*    */   public void setMinistryId(int ministryId) {
/* 27 */     this.ministryId = ministryId;
/*    */   }
/*    */   
/*    */   public String getMinistry() {
/* 31 */     return this.ministry;
/*    */   }
/*    */   
/*    */   public void setMinistry(String ministry) {
/* 35 */     this.ministry = ministry;
/*    */   }
/*    */   
/*    */   public List<assignments> getAssignments() {
/* 39 */     return this.assignments;
/*    */   }
/*    */   
/*    */   public void setAssignments(List<assignments> assignments) {
/* 43 */     this.assignments = assignments;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\auth\dt\\updateServiceRequest.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */