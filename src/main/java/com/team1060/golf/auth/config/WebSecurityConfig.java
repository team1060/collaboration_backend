package com.team1060.golf.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.team1060.golf.auth.security.JwtAuthenticationFilter;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * SecurityConfig
 * </pre>
 * @author KJY
 * @since 2023.12.25
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	private final JwtAuthenticationFilter JwtAuthenticationFilter;
	
	private static  final String[] PERMIT_URL_ARRAY = {
		"/",
		"api/golf/**", 
		"api/member/join/**" , 
		"api/member/deljoin",
		"oauth/**", 
		"api/product" , 
		"api/golf/info/**",
		"api/member/login/**", 
		"api/member/getEmail/**", 
		"api/main/reserve", 
		"api/member/isAdmin",
		"api/member/modify/**",
		"api/products/**",
		"api/payment/**",
		"api/shipping/**",
		"api/brand",
		"img/**",
		"api/admin/course/**"
	};
	
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http 
	        .cors().and()
	        .csrf().disable()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .formLogin().disable()
	        .httpBasic().disable()
	        .authorizeRequests()
	            .requestMatchers(PERMIT_URL_ARRAY).permitAll()
	            .requestMatchers("/member/mypage/**").authenticated()
	            .requestMatchers("/api/reservation/**", "/api/payment/**", "/api/shipping/**").authenticated()
	            .requestMatchers("/**").hasRole("ADMIN")
	            .anyRequest().permitAll()
	        .and()
	        .addFilterAfter(
	        		JwtAuthenticationFilter, 
	        		UsernamePasswordAuthenticationFilter.class
	        		)
	        .exceptionHandling().accessDeniedPage("/access-denied");
	    return http.build();
	}
	
	// 복호화 
	@Bean
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}
}
