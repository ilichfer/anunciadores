/**
 * 
 */
package com.anunciadores.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

/**
 * @author SoporteTI
 *
 */
@Configuration
public class FeignClientConfiguration {
	@Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
         return new BasicAuthRequestInterceptor("api-key", "1f9d97c52fb351d56a1bc7ffe1140e58");
    }
}
