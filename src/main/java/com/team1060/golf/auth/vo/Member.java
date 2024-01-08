package com.team1060.golf.auth.vo;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * <pre>
 * 회원 vo
 * </pre>
 * @author KJY
 * @since 2023.12.25
 */

public class Member {
	private String email; // 이메일 
	private String nickname; // 닉네임 
	private String username; // 이름 
	private String password; // 비밀번호 
	private ZonedDateTime regdate; // 가입일 
	private String phone_number; // 휴대폰 번호 
	private int type; // 회원, 중간관리자, 총괄관리자 0, 1, 2
	private String auth_data; // 카카오인증, 메일인증, 휴대폰 인증 등 인증 정보 
	private byte is_sms_consent; // true 1 , false 0 
	private byte is_email_consent; // true 1 , false 0 
}
