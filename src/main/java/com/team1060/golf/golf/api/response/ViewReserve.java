	package com.team1060.golf.golf.api.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * <pre>
 * 골프 예약 내역 및 취소 내역 
 * </pre>
 * @author KJY
 * @since 2023.12.24
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ViewReserve {
	private Long reserve_no; // 예약 번호 
	private Long course_no; // 코스번호 
	private String email; // 이메일 
	private Long golf_no; // 골프장 번호 
	private ZonedDateTime reserve_time; // 예약 날짜, 시간 
	private LocalDate golf_date; // 예약 날짜 
	private LocalTime golf_time; // 예약 시간 
	private int golf_status; // 예약 상태 (1)예약완료, (2)예약취소
	private String course_name; // 코스이름 
	private int greenpee; // 그린피 
	
	private ZonedDateTime cancelTime; // 취소 날짜 + 시간 
}
