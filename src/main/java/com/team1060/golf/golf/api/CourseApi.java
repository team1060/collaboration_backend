package com.team1060.golf.golf.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team1060.golf.golf.api.request.RegisterAndModifyCourse;
import com.team1060.golf.golf.api.response.ViewCourse;
import com.team1060.golf.golf.service.CourseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 코스 api 
 * </pre>
 * @author KJY
 * @since 2023.12.20
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Log4j2
public class CourseApi {
	private final CourseService courseService;

	// 코스 등록
	@PostMapping("/course")
	public ResponseEntity<String> registerCourse(@RequestBody RegisterAndModifyCourse course) {
		try {
			courseService.register(course);
			return ResponseEntity.ok("코스 등록 완료");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("코스 등록 실패" + e.getMessage());
		}
	}

	// 코스 전체 조회
	@GetMapping("/course")
	@CrossOrigin
	public List<ViewCourse> selectAll() {
		return courseService.selectAll();
	}

	// 코스 1개 조회
	@GetMapping("/course/{course_no}")
	@CrossOrigin
	public ViewCourse select(@PathVariable(name = "course_no") Long course_no) {
		return courseService.select(course_no);
	}

	// 코스 1개 수정
	@PutMapping("/course/{course_no}")
	@CrossOrigin
	public ResponseEntity<String> modifyCourse(@PathVariable (name = "course_no") Long course_no , @RequestBody RegisterAndModifyCourse course) {
		try {
			
			courseService.modifyCourse(course);
			
			return ResponseEntity.ok("코스 수정 완료");
		} catch (Exception e) {

			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("코스 수정 실패" + e.getMessage());
		}
	}

	// 코스 삭제
	@DeleteMapping("/course/{course_no}")
	public ResponseEntity<String> removeCourse(@PathVariable(name = "course_no") Long course_no) {
		try {
			
			courseService.removeCourse(course_no);
			return ResponseEntity.ok("코스 삭제 완료");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("코스 삭제 실패" + e.getMessage());
		}
	}
	
	// 골프장별 코스 조회 
	@GetMapping("/course/golf/{golf_no}")
	@CrossOrigin
	public List<ViewCourse> selectGolf(@PathVariable(name = "golf_no") Long golf_no){
		return courseService.getGolfList(golf_no);
	}
}
