package com.team1060.golf.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.DispatcherType;

/**
 * <pre>
 * SecurityConfig
 * </pre>
 * @author KJY
 * @since 2023.12.25
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	private static  final String[] PERMIT_URL_ARRAY = {
		"/","/golf/**", "/member/join/**", "/admin/golf/**" ,"/reservation/**"
	};
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable();
        http.csrf((csrf) -> csrf.disable())
        	.cors().and()
            .authorizeHttpRequests()
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .requestMatchers(PERMIT_URL_ARRAY).permitAll()
//                .requestMatchers("/reservation/**").hasAnyRole("USER")
                .anyRequest().permitAll();
        
//                .requestMatchers("/reservation/**").authenticated());
        
//        http.formLogin((formLogin) -> formLogin
//                .loginPage("/member/join")
//                .failureUrl("/member/join?error")
//                .permitAll()
//            );


        return http.build();
	}
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // 리액트 개발 서버 주소
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	// 복호화 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
