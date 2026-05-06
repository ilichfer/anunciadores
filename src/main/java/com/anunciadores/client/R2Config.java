/*    */ package  com.anunciadores.client;
/*    */ 
/*    */ import com.anunciadores.client.R2Client;
/*    */ import java.net.URI;
/*    */ import org.springframework.beans.factory.annotation.Value;
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
/*    */ import software.amazon.awssdk.auth.credentials.AwsCredentials;
/*    */ import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
/*    */ import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
/*    */ import software.amazon.awssdk.regions.Region;
/*    */ import software.amazon.awssdk.services.s3.S3Client;
/*    */ import software.amazon.awssdk.services.s3.S3ClientBuilder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Configuration
/*    */ public class R2Config
/*    */ {
/*    */   @Value("${cloudflare.r2.access-key}")
/*    */   private String accessKey;
/*    */   @Value("${cloudflare.r2.secret-key}")
/*    */   private String secretKey;
/*    */   @Value("${cloudflare.r2.endpoint}")
/*    */   private String endpoint;
/*    */   
/*    */   @Bean
/*    */   public R2Client r2Client() {
/* 31 */     S3Client client = (S3Client)((S3ClientBuilder)((S3ClientBuilder)((S3ClientBuilder)S3Client.builder().endpointOverride(URI.create(this.endpoint))).credentialsProvider((AwsCredentialsProvider)StaticCredentialsProvider.create((AwsCredentials)AwsBasicCredentials.create(this.accessKey, this.secretKey)))).region(Region.of("auto"))).build();
/* 32 */     return new R2Client(client);
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\client\R2Config.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */