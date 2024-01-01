package com.team1060.golf.golf.vo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * 골프장 코스
 * </pre>
 * @author KJY
 * @since 2023.12.19
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GolfCourse {
	private Long course_no; // 코스 번호 
	private Long golf_no; // 골프장 번호 
	private String course_name; // 코스이름 
	private int greenpee; // 그린피 
	private LocalTime golf_time; // 코스 시간 
	private LocalDate golf_date; // 코스 날짜 
	private int golf_status; // 예약상태 
}
