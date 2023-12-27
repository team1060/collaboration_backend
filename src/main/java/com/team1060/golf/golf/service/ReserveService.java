package com.team1060.golf.golf.service;

import java.time.LocalDate;
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
	
	// 골프장 예약 
	public int reserveGolf (RegisterAndModifyReserve golf) {
		log.info(golf);
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
}
