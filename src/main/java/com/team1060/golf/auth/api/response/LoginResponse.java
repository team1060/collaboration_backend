package com.team1060.golf.auth.api.response;

import lombok.Builder;
import lombok.Getter;



@Getter
@Builder
public class LoginResponse {
	
	private String email;
	private String token;
	private String message;
	
	
	public LoginResponse(String email, String token, String message) {
		this.email = email;
		this.token = token;
		this.message = message;
	}
	
    public LoginResponse(String message) {
        this.message = message;
    }
}
