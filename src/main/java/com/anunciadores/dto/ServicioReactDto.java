/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.dto.MinistryMember;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class ServicioReactDto
/*    */ {
/*    */   private List<Map<String, List<MinistryMember>>> ministries;
/*    */   
/*    */   public ServicioReactDto() {}
/*    */   
/*    */   public ServicioReactDto(List<Map<String, List<MinistryMember>>> ministries) {
/* 15 */     this.ministries = ministries;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Map<String, List<MinistryMember>>> getMinistries() {
/* 20 */     return this.ministries;
/*    */   }
/*    */   
/*    */   public void setMinistries(List<Map<String, List<MinistryMember>>> ministries) {
/* 24 */     this.ministries = ministries;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\ServicioReactDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */