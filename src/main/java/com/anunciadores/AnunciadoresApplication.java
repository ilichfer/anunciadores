/*    */ package  com.anunciadores;
/*    */ 
/*    */ import org.springframework.boot.SpringApplication;
/*    */ import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
/*    */ import org.springframework.boot.autoconfigure.SpringBootApplication;
/*    */ import org.springframework.cloud.openfeign.EnableFeignClients;
/*    */ import org.springframework.cloud.openfeign.FeignAutoConfiguration;
/*    */ 
/*    */ @EnableFeignClients
/*    */ @ImportAutoConfiguration({FeignAutoConfiguration.class})
/*    */ @SpringBootApplication
/*    */ public class AnunciadoresApplication
/*    */ {
/*    */   public static void main(String[] args) {
/* 15 */     SpringApplication.run(com.anunciadores.AnunciadoresApplication.class, args);
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\AnunciadoresApplication.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */