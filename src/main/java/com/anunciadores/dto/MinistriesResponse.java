/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.dto.Ministry;
/*    */ import java.util.List;
/*    */ 
/*    */ public class MinistriesResponse
/*    */ {
/*    */   private List<Ministry> ministries;
/*    */   
/*    */   public MinistriesResponse(List<Ministry> ministries) {
/* 11 */     this.ministries = ministries;
/*    */   }
/*    */   public MinistriesResponse() {}
/*    */   public List<Ministry> getMinistries() {
/* 15 */     return this.ministries;
/*    */   }
/*    */   
/*    */   public void setMinistries(List<Ministry> ministries) {
/* 19 */     this.ministries = ministries;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\MinistriesResponse.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */