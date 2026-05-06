/*    */ package  com.anunciadores.security;
/*    */ 
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.security.config.annotation.web.builders.HttpSecurity;
/*    */ import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
/*    */ import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/*    */ import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
/*    */ import org.springframework.security.config.http.SessionCreationPolicy;
/*    */ import org.springframework.security.web.firewall.HttpFirewall;
/*    */ import org.springframework.security.web.firewall.StrictHttpFirewall;
/*    */ 
/*    */ @Configuration
/*    */ @EnableWebSecurity
/*    */ public class SecurityConfig extends WebSecurityConfigurerAdapter {
/*    */   @Bean
/*    */   public HttpFirewall getHttpFirewall() {
/* 18 */     StrictHttpFirewall strictHttpFirewall = new StrictHttpFirewall();
/* 19 */     strictHttpFirewall.setAllowSemicolon(true);
/* 20 */     return (HttpFirewall)strictHttpFirewall;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void configure(HttpSecurity http) throws Exception {
/* 25 */     ((HttpSecurity)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)((HttpSecurity)http
/* 26 */       .csrf().disable())
/* 27 */       .sessionManagement()
/* 28 */       .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
/* 29 */       .and())
/* 30 */       .authorizeRequests()
/*    */       
/* 32 */       .antMatchers(new String[] { "/**" })).permitAll()
/* 33 */       .and())
/*    */       
/* 35 */       .formLogin().disable())
/* 36 */       .httpBasic().disable();
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\security\SecurityConfig.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */