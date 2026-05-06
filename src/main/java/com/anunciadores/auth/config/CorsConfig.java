package com.anunciadores.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Orígenes permitidos
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("http://localhost:5000"); // desarrollo
        config.addAllowedOrigin("http://localhost:5173");   // vite alternativo
        config.addAllowedOrigin("https://anunciaig.com/");   // producción
        config.addAllowedOrigin("https://anunciadores.netlify.app/");   // netlify

        // Headers y métodos permitidos
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        // Necesario para enviar el header Authorization
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
