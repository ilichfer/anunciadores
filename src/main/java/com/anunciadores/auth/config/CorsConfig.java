/*    */ package  com.anunciadores.auth.config;
/*    */ 
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.web.cors.CorsConfiguration;
/*    */ import org.springframework.web.cors.CorsConfigurationSource;
/*    */ import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
/*    */ import org.springframework.web.filter.CorsFilter;
/*    */ 
/*    */ @Configuration
/*    */ public class CorsConfig {
/*    */   @Bean
/*    */   public CorsFilter corsFilter() {
/* 14 */     CorsConfiguration config = new CorsConfiguration();
/*    */ 
/*    */     
/* 17 */     config.addAllowedOrigin("http://localhost:3000");
/* 18 */     config.addAllowedOrigin("http://localhost:5000");
/* 19 */     config.addAllowedOrigin("http://localhost:5173");
/* 20 */     config.addAllowedOrigin("https://anunciaig.com/");
/* 21 */     config.addAllowedOrigin("https://anunciadores.netlify.app/");
/*    */ 
/*    */     
/* 24 */     config.addAllowedHeader("*");
/* 25 */     config.addAllowedMethod("*");
/*    */ 
/*    */     
/* 28 */     config.setAllowCredentials(Boolean.valueOf(false));
/*    */     
/* 30 */     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
/* 31 */     source.registerCorsConfiguration("/**", config);
/*    */     
/* 33 */     return new CorsFilter((CorsConfigurationSource)source);
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\auth\config\CorsConfig.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */