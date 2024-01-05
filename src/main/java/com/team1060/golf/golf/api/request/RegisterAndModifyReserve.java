package com.team1060.golf.golf.api.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 골프장 예약 
 * </pre>
 * @author KJY
 * @since 2023.12.20
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterAndModifyReserve {
	private Long reserve_no; // 예약 번호 
	private Long course_no; // 코스번호 
	private String course_name;
	private String email; // 이메일 
	private Long golf_no; // 골프장 번호 
	private ZonedDateTime reserve_time; // 예약 완료 날짜 시간 
	private LocalTime golf_time; // 예약시간 
	private LocalDate golf_date; // 예약날짜 
	private int golf_status; // 예약 상태 (0)완료, (1)취소
	private int greenpee;
	
	private ZonedDateTime cancelTimd; // 취소 날짜 + 시간 
}
