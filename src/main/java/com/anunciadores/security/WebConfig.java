/*    */ package  com.anunciadores.security;
/*    */ 
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
/*    */ import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
/*    */ import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*    */ 
/*    */ 
/*    */ @Configuration
/*    */ public class WebConfig
/*    */   implements WebMvcConfigurer
/*    */ {
/*    */   public void addViewControllers(ViewControllerRegistry registro) {
/* 14 */     registro.addViewController("/").setViewName("index");
/* 15 */     registro.addViewController("/login");
/*    */   }
/*    */ 
/*    */   
/*    */   public void addResourceHandlers(ResourceHandlerRegistry registry) {
/* 20 */     registry
/* 21 */       .addResourceHandler(new String[] { "/resources/**"
/* 22 */         }).addResourceLocations(new String[] { "file:./src/", "/resources/" });
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\security\WebConfig.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */