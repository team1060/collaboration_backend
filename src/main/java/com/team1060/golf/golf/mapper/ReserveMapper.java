package com.team1060.golf.golf.mapper;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.team1060.golf.golf.api.request.RegisterAndModifyReserve;
import com.team1060.golf.golf.api.request.RegisterAndModifyCourse;
import com.team1060.golf.golf.api.response.ViewCourse;
import com.team1060.golf.golf.api.response.ViewGolf;
import com.team1060.golf.golf.api.response.ViewReserve;

/**
 * <pre>
 * 예약 
 * </pre>
 * @author KJY
 * @since 2023.12.23
 */

@Mapper
public interface ReserveMapper {
	
	// 코스 전체 조회 
	List<ViewCourse> getList();
	
	// 골프장 예약 
	int insert(RegisterAndModifyReserve golf);

	// 코스1개조회
	ViewCourse select(Long course_no);
    
	// status 수정 
	int modify(RegisterAndModifyReserve golf);
	
	// 예약내역 
	List<ViewReserve> selectEmail(String email);
	
	// 예약내역갯수
	int getEmailCount(String email);

	// 골프장 예약 취소 
	int cancelGolf(Long reserve_no);
	
	// 코스 상태 수정 전 조회 
	ViewCourse getCourse(Long reserve_no);
	
	// 예약취소 후 코스 상태 수정 
	int golfUpdate(Long course_no);
	
	// 예약 할 때 3개 제한 
	int getDayCount(String email);

}
