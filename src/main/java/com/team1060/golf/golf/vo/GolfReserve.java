package com.team1060.golf.golf.vo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

/**
 * <pre>
 * 골프장 예약
 * </pre>
 * @author KJY
 * @since 2023.12.19
 */

public class GolfReserve { 
	private Long reserve_no; // 예약 번호 
	private Long course_no; // 코스번호 
	private String email; // 이메일 
	private int golf_no; // 골프장 번호 
	private ZonedDateTime reserve_time; // 예약 완료 날짜 시간 
	private LocalTime time; // 예약시간 
	private LocalDate date; // 예약날짜 
	private int golf_status; // 예약 상태 (0)완료, (1)취소
}
