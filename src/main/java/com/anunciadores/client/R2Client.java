/*    */ package  com.anunciadores.client;
/*    */ 
/*    */ import software.amazon.awssdk.services.s3.S3Client;
/*    */ 
/*    */ public class R2Client {
/*    */   private final S3Client s3Client;
/*    */   
/*    */   public R2Client(S3Client s3Client) {
/*  9 */     this.s3Client = s3Client;
/*    */   }
/*    */   
/*    */   public S3Client getClient() {
/* 13 */     return this.s3Client;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\client\R2Client.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */