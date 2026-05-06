/*    */ package  com.anunciadores.client;
/*    */ 
/*    */ import feign.auth.BasicAuthRequestInterceptor;
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Configuration
/*    */ public class FeignClientConfiguration
/*    */ {
/*    */   @Bean
/*    */   public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
/* 19 */     return new BasicAuthRequestInterceptor("api-key", "1f9d97c52fb351d56a1bc7ffe1140e58");
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\client\FeignClientConfiguration.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */