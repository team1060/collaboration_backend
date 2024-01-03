package com.team1060.golf.auth.api.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.team1060.golf.golf.api.request.RegisterAndModifyCourse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * 회원 추가 & 수정 
 * </pre>
 * @author KJY
 * @since 2023.12.25
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterAndModifyMember {
	private String email; // 이메일 
	private String nickname; // 닉네임 
	private String name; // 이름 
	private String username; // 이메일 
	private String role; // user, admin 
	private String password; // 비밀번호 
	private ZonedDateTime regdate; // 가입일 
	private String phone_number; // 휴대폰 번호 
	private int type; // 회원, 중간관리자, 총괄관리자 0, 1, 2
	private String auth_data; // 카카오인증, 메일인증, 휴대폰 인증 등 인증 정보 
	private int is_sms_consent; // true 1 , false 0 
	private int is_email_consent; // true 1 , false 0 

	// 비밀번호 복호화 
	public RegisterAndModifyMember encodePassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
		return this;
	}
}
