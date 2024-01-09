package com.team1060.golf.auth.api.request;

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
 * @since 2024.01.08
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindEmailAndPassword {
	private String email;
	private String name;
	private String phone_number;
	private String password;
	
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public class FindPassword {
		private String email;
		private String password;
	}
}
