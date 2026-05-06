/*    */ package  com.anunciadores;
/*    */ 
/*    */ import com.anunciadores.AnunciadoresApplication;
/*    */ import org.springframework.boot.builder.SpringApplicationBuilder;
/*    */ import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/*    */ 
/*    */ public class ServletInitializer
/*    */   extends SpringBootServletInitializer {
/*    */   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
/* 10 */     return application.sources(new Class[] { AnunciadoresApplication.class });
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\ServletInitializer.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */