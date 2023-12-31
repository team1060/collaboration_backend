package com.team1060.golf.golf.api;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team1060.golf.golf.api.response.ViewCourse;
import com.team1060.golf.golf.service.CourseService;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 골프 메인 API
 * </pre>
 * @author KJY
 * @since 2023.12.27
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GolfMainApi {
	
	private final CourseService courseService;
	
	@GetMapping("/dataSerch")
	@CrossOrigin
	public ResponseEntity<Integer> courseCount(@RequestParam(name = "golf_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate golf_date) {
	    int count = courseService.selectCourseCount(golf_date);
	    return ResponseEntity.ok(count);
	}
}
