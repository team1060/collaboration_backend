package com.team1060.golf.auth.config.jwt;

import java.time.Duration;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.team1060.golf.auth.api.request.RegisterAndModifyMember;
import com.team1060.golf.auth.api.response.ViewMember.LoginUser;
import com.team1060.golf.auth.vo.OauthMember;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * jwt
 * </pre>
 * 
 * @author KJY
 * @since 2024.01.03
 */

@Log4j2
@RequiredArgsConstructor
@Service
public class TokenProvider {
	private final JwtProperties jwtProperties;

	
	private String makeToken(Date expiry, LoginUser user) {
		Date now = new Date();
		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE) // jwt 헤더에 토큰 타입 설정 
				.setIssuer(jwtProperties.getIssuer()) // 발급자 
				.setIssuedAt(now) // 토큰이 발급된 시간 
				.setExpiration(expiry) // 토큰 만료시간 
				.setSubject(user.getEmail()) // 이메일 주소 
				.claim("email", user.getEmail()) 
				.claim("username", user.getUsername())
				.claim("role", user.getRole())
				.signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
				.compact();
	}
	
	private String makeAuthToken(Date expiry, OauthMember user) {
		Date now = new Date();
		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE) 
				.setIssuer(jwtProperties.getIssuer()) 
				.setIssuedAt(now) 
				.setExpiration(expiry)
				.setSubject(user.getEmail()) // 이메일 주소 
				.claim("email", user.getEmail()) 
				.claim("username", user.getUsername())
				.claim("role", user.getRole())
				.signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
				.compact();
	}
		
	public String generateToken(LoginUser user, Duration expiredAt) {
		Date now = new Date();
		return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
	}
	
	public String generateAuthToken(OauthMember user, Duration expiredAt) {
		Date now = new Date();
		return makeAuthToken(new Date(now.getTime() + expiredAt.toMillis()), user);
	}
	
	public String validateAndGetUserId(String token) throws JwtException {
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(jwtProperties.getSecretKey())
					.parseClaimsJws(token)
					.getBody();
			
			return claims.getSubject();
			
		} catch (JwtException e) {
			log.debug("Received Token: {}", token);
			log.error("토큰 검증 중 오류 발생", e);
			throw new JwtException("토큰이 틀림" + e);
			
		}
	}
	
	
}
