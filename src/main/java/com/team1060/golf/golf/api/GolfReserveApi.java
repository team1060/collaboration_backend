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
import org.springframework.web.bind.annotation.PutMapping;
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
	private final CourseService courseService;
	
	
	// 코스 전체 조회
	@GetMapping("/reservation/detail") // reservation/ 추가 하고 , 
	public List<ViewCourse> selectAll() {
		return reserveService.selectAllCourse();
	}

	// 골프장 예약 신청
	@PostMapping("/reservation/detail")
	@CrossOrigin
	@Transactional
	public ResponseEntity<?> reserveGolf(
	        @RequestBody RegisterAndModifyReserve golf
	) {
	    try {
	        int dayCount = reserveService.getDayCount(golf.getEmail());
//	        log.info("갯수: " + dayCount);
	        
	        // 예약갯수
	        if (dayCount >= 3) {
	            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("하루에 3회 이상 예약할 수 없습니다.");
	        }
	        // 동시예약 가능성을 막기 위해
	        boolean golfData = courseService.getCourseNo(golf.getCourse_no());
	        log.info("골프 데이터: " + golfData);

	        if (golfData) {
	            reserveService.reserveGolf(golf);
	            golf.setGolf_status(1);
	            reserveService.modifyCourse(golf);
	            return ResponseEntity.ok(golf + " 예약 완료");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미 예약이 완료된 코스입니다.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("골프장 예약 실패");
	    }
	}

	
	// 아이디별 예약 내역조회 
	@GetMapping("/reservation/confirm/{email}")
	@CrossOrigin
	public List<ViewReserve> selectEmail(@PathVariable(name ="email") String email) {
		return reserveService.selectEmail(email);
	}
	
	// 아이디별 예약 내역 갯수조회
	@GetMapping("/reservation/confirmcount/{email}")
	@CrossOrigin
	public int selectEmailcount(@PathVariable(name ="email") String email) {
		return reserveService.selectEmailCount(email);
	}
		
		
	// 예약 취소 
	@PostMapping("/reservation/cancel/{reserve_no}")
	@CrossOrigin
	@Transactional
	public ResponseEntity<String> deleteGolf(@PathVariable(name = "reserve_no") Long reserve_no){
		try {
			reserveService.cancel(reserve_no);
			ViewCourse course =  reserveService.getCourse(reserve_no);
			reserveService.golfUpdate(course.getCourse_no());
			return ResponseEntity.ok("취소완료");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("골프장 취소 실패");
		}
	}
}
