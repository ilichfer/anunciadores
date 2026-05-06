/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ public class ApiResponse {
/*    */   private boolean success;
/*    */   private String message;
/*    */   private Object data;
/*    */   
/*    */   public ApiResponse(boolean success, String message) {
/*  9 */     this.success = success;
/* 10 */     this.message = message;
/*    */   }
/*    */   
/*    */   public boolean isSuccess() {
/* 14 */     return this.success;
/*    */   }
/*    */   
/*    */   public void setSuccess(boolean success) {
/* 18 */     this.success = success;
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 22 */     return this.message;
/*    */   }
/*    */   
/*    */   public void setMessage(String message) {
/* 26 */     this.message = message;
/*    */   }
/*    */   
/*    */   public Object getData() {
/* 30 */     return this.data;
/*    */   }
/*    */   
/*    */   public void setData(Object data) {
/* 34 */     this.data = data;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\ApiResponse.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */