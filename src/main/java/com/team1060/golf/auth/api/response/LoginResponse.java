package com.team1060.golf.auth.api.response;

import lombok.Builder;
import lombok.Getter;

/**
 * <pre>
 * 로그인 
 * </pre>
 * @author KJY
 * @since 2024.01.05
 */

@Getter
@Builder
public class LoginResponse {
	
	private String email;
	private String token;
	private String message;
	
	public LoginResponse(String email, String message) {
		this.email = email;
		this.message = message;
	}
	
	public LoginResponse(String email, String token, String message) {
		this.email = email;
		this.token = token;
		this.message = message;
	}
	
    public LoginResponse(String message) {
        this.message = message;
    }
}
