package com.team1060.golf.golf.vo;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * 골프장 이미지
 * </pre>
 * @author KJY
 * @since 2023.12.19
 */

public class GolfImage {
	private UUID uuid; 
	private String category; // 이미지 카테고리 
	private Long course_no; // 코스 번호 
	private String path; // 이미지 경로 
	
}
