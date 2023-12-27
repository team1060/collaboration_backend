package com.team1060.golf.golf.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team1060.golf.golf.api.request.RegisterAndModifyReserve;
import com.team1060.golf.golf.api.response.ViewCourse;
import com.team1060.golf.golf.api.response.ViewGolf;
import com.team1060.golf.golf.api.response.ViewReserve;
import com.team1060.golf.golf.service.CourseService;
import com.team1060.golf.golf.service.ReserveService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 골프장 예약 api 
 * </pre>
 * @author KJY
 * @since 2023.12.23
 */

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class GolfReserveApi {

	private final ReserveService reserveService;
	
	
	// 코스 전체 조회
	@GetMapping("/reservation")
	@CrossOrigin
	public List<ViewCourse> selectAll() {
		return reserveService.selectAllCourse();
	}
	// 골프장 예약 신청 
	@PostMapping("/reservation")
	@CrossOrigin
	@Transactional
	public ResponseEntity<?> reserveGolf(
	        @RequestBody RegisterAndModifyReserve golf
	        ) {
	    try {
	        // 예약 서비스 호출 및 로깅 등을 수행
	        reserveService.reserveGolf(golf);
	        golf.setGolf_status(0);
	        reserveService.modifyCourse(golf);
	        return ResponseEntity.ok(golf + " 예약 완료");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("골프장 예약 실패");
	    }
	}
	
	// 아이디별 예약 내역조회 
	@GetMapping("reservation/{email}")
	@CrossOrigin
	public List<ViewReserve> selectEmail(@PathVariable(name ="email") String email) {
		
		return reserveService.selectEmail(email);
	}
	
	
	
}
