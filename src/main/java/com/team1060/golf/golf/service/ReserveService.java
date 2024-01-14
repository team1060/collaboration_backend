package com.team1060.golf.golf.service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team1060.golf.golf.api.request.RegisterAndModifyReserve;
import com.team1060.golf.golf.api.response.ViewCourse;
import com.team1060.golf.golf.api.response.ViewReserve;
import com.team1060.golf.golf.mapper.ReserveMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 예약 서비스 
 * </pre>
 * @author KJY
 * @since 2023.12.23
 */

@Service
@RequiredArgsConstructor
@Log4j2
public class ReserveService {
	private final ReserveMapper reserveMapper;
	

	// 코스 전체 조회 
	public List<ViewCourse> selectAllCourse() {
		return reserveMapper.getList();
	}
	
	// 동시예약 방지 
	
	// 골프장 예약 
	public int reserveGolf (RegisterAndModifyReserve golf) {
		return reserveMapper.insert(golf);
	}
	// status 수정 
	public int modifyCourse(RegisterAndModifyReserve golf) {
		log.info(golf);
		return reserveMapper.modify(golf);
	}
	
	// 아이디별 예약 내역 조회 
	public List<ViewReserve> selectEmail(String email){
		return reserveMapper.selectEmail(email);
	}
	
	// 예약 취소 
	public int cancel(Long reserve_no) {
		return reserveMapper.cancelGolf(reserve_no);
	}
	
	// 취소 전 코스 번호 조회 
	public ViewCourse getCourse(Long reserve_no) {
		return reserveMapper.getCourse(reserve_no);
	}
	
	// 예약 취소 후 코스 상태 수정 
	public int golfUpdate(Long course_no) {
		log.info("service");
		return reserveMapper.golfUpdate(course_no);
	}
	
	// 하루에 3개 예약 제한 
	public int getDayCount(String email) {
		return reserveMapper.getDayCount(email);
	}

	public int selectEmailCount(String email) {
		// TODO Auto-generated method stub
		return reserveMapper.getEmailCount(email);
	}
}
