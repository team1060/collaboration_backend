package com.team1060.golf.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.team1060.golf.auth.presentation.OauthServerTypeConverter;

/**
 * <pre>
 * webConfig
 * </pre>
 * @author KJY
 * @since 2024.01.03
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	private static final long MAX_AGE_SECS = 3600;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**")
	            .allowedOrigins("http://13.209.73.98")
	            .allowedMethods("GET", "POST", "PUT", "DELETE")
	            .allowedHeaders("*")
	            .allowCredentials(true)
	            .maxAge(MAX_AGE_SECS);
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    
	    config.addAllowedOrigin("*");
	    config.addAllowedMethod("OPTIONS");
	    config.addAllowedMethod("HEAD");
	    config.addAllowedMethod("GET");
	    config.addAllowedMethod("POST");
	    config.addAllowedMethod("PUT");
	    config.addAllowedMethod("DELETE");
	    config.addAllowedMethod("PATCH");

	    source.registerCorsConfiguration("/**", config.applyPermitDefaultValues());
	    return source;
	}
	
	 @Override
	    public void addFormatters(FormatterRegistry registry) {
	        registry.addConverter(new OauthServerTypeConverter());
	    }
	
}
