package com.team1060.golf.golf.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * 골프장 등록 & 수정 
 * </pre>
 * @author KJY
 * @since 2023.12.20
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterAndModifyGolf {
	private Long golf_no; // 골프장 pk 
	private String region; // 지역 
	private String name; // 골프장명 
	private String description; // 골프장 설명 
	private int holes; // 홀 수 
	private int pars; // 파 수 
	private int land_area; // 전장 
	private String address; // 주소 
	private String contact; // 전화번호 
	private String fax; // 팩스번호 
}
