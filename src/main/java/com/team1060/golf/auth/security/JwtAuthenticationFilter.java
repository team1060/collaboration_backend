package com.team1060.golf.auth.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.team1060.golf.auth.config.jwt.TokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private final TokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String token = parseBearerToken(request);
			if(token != null && !token.equalsIgnoreCase("null")) {
				String userId = tokenProvider.validateAndGetUserId(token);
				 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
			                userId, 
			                null,  
			                AuthorityUtils.NO_AUTHORITIES
			            );
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
			securityContext.setAuthentication(authentication);
			SecurityContextHolder.setContext(securityContext);
			} else {
				log.info("토큰없음");
			}
		} catch (Exception e) {
			//토큰 만료 
			logger.error("토큰만료");
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 
			response.getWriter().write("토큰만료");
			return;
		}
		filterChain.doFilter(request, response);
	}
	private String parseBearerToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		log.info("1");
		 if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			 log.info("2");
		      return bearerToken.substring(7);
		    }
		    return null;
		}
	
}
