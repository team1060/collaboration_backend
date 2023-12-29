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
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable();
//        http.csrf((csrf) -> csrf.disable())
//        	.cors((cors) -> cors.disable())
//            .authorizeHttpRequests(request -> request
//                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                .requestMatchers("/golf/**").permitAll()
//                .requestMatchers("/reservation").authenticated()
//                .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
//                .requestMatchers("/**").permitAll());
                
            
        return http.build();
	}
	// 복호화 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
