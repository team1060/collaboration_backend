package com.team1060.golf.auth.api.request;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * 회원삭제
 * </pre>
 * @author KJY
 * @since 2024.01.08
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemoveMember {
	private String email;
	private String password;
}
