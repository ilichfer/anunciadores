/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.dto.ResultDTO;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ResponseTelegram {
/*    */   public boolean ok;
/*    */   
/*    */   public boolean isOk() {
/* 10 */     return this.ok;
/*    */   }
/*    */   public ArrayList<ResultDTO> result;
/*    */   public void setOk(boolean ok) {
/* 14 */     this.ok = ok;
/*    */   }
/*    */   
/*    */   public ArrayList<ResultDTO> getResult() {
/* 18 */     return this.result;
/*    */   }
/*    */   
/*    */   public void setResult(ArrayList<ResultDTO> result) {
/* 22 */     this.result = result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\ResponseTelegram.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */