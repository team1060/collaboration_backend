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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewMember {
	private String email; // 이메일 
	private String nickname; // 닉네임 
	private String username; // 이름 
	private String password; // 비밀번호 
	private ZonedDateTime regdate; // 가입일 
	private ZonedDateTime password_changed_at; // 비밀번호 변경일 
	private LocalDate birthdate; // 생년월일 
	private String phone_number; // 휴대폰 번호 
	private int type; // 회원, 중간관리자, 총괄관리자 0, 1, 2
	private String auth_data; // 카카오인증, 메일인증, 휴대폰 인증 등 인증 정보 
	private byte is_sms_consent; // true 1 , false 0 
	private byte is_email_consent; // true 1 , false 0 
	private String gender; // 남자 여자 
	private String telecom; // 통신사 
	private byte is_korean; // 내국인1 외국인 0
	private boolean is_checkedEmail; // 이메일 중복 여부 
	
	@Getter
	@Setter
	@NoArgsConstructor
	public static class LoginUser {
	    private String email;
	    private String password;
	}
	@Getter
	@Setter
	@NoArgsConstructor
	public static class CheckedEmial {
		private boolean is_checkedEmail;
	}
}
