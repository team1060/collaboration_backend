package com.team1060.golf.auth.config.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * jwt 
 * </pre>
 * 
 * @author KJY
 * @since 2024.01.03
 */

@Setter
@Getter
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
	private String issuer;
	private String secretKey;
}
