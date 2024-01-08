package com.team1060.golf.auth.api.request;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * 비밀번호 변경 
 * </pre>
 * @author KJY
 * @since 2024.01.07
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordChangeRequest {
	private String email;
	private String password; // 새 비밀번호 
	private String currentPassword; // 전 비밀번호 
}
