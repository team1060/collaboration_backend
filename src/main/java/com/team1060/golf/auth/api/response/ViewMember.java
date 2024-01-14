package com.team1060.golf.auth.api.response;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import com.team1060.golf.auth.api.request.RegisterAndModifyMember;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * 회원 조회 
 * </pre>
 * @author KJY
 * @since 2023.12.25
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewMember {
	private String email; // 이메일 
	private String nickname; // 닉네임 
	private String name; // 이름 
	private String username; // 이메일 
	private String role; // user, admin 
	private String password; // 비밀번호 
	private ZonedDateTime regdate; // 가입일 
	private String phone_number; // 휴대폰 번호 
	private int type; // 회원, 중간관리자, 총괄관리자 0, 1, 2
	private int is_sms_consent; // true 1 , false 0 
	private int is_email_consent; // true 1 , false 0 
	private String oauthServerType; // 카카오, 네이버 등 인증 정보 
	
	
	
	@Getter
	@Setter
	@NoArgsConstructor
	public static class LoginUser {
	    private String email;
	    private String password;
	    private String username; // 이름 
		private String role; // user, admin 
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	public static class LoginUserMypage {
	    private String email;
	    private String password;
	}
	@Getter
	@Setter
	@NoArgsConstructor
	public static class CheckedEmial {
		private boolean is_checkedEmail;
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	public static class PwChange {
		 private String email;
		 private String password;
	}
}
