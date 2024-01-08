package com.team1060.golf.golf.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team1060.golf.golf.api.request.RegisterAndModifyCourse;
import com.team1060.golf.golf.api.response.ViewCourse;
import com.team1060.golf.golf.mapper.CourseMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 코스 CRUD
 * </pre>
 * @author KJY
 * @since 2023.12.20
 */

@Service
@RequiredArgsConstructor
public class CourseService {
	private final CourseMapper courseMapper;
	
	// 코스 등록 
	public int register(RegisterAndModifyCourse course) {
		return courseMapper.insert(course);
	}
	// 코스 조회 
	public List<ViewCourse> selectAll() {
		return courseMapper.getList();
	}
	public boolean getCourseNo(Long course_no) {
		return courseMapper.getCourseNo(course_no);
	}
	// 코스 1개 조회 
	public ViewCourse select(Long course_no) {
		return courseMapper.select(course_no);
	}
	// 골프장별 코스 조회 
	public List<ViewCourse> getGolfList(Long golf_no){
		return courseMapper.getGolfList(golf_no);
	}
	// 코스 수정 
	public int modifyCourse(RegisterAndModifyCourse course) {
		return courseMapper.update(course);
	}
	// 코스 삭제 
	public int removeCourse(Long course_no) {
		return courseMapper.delete(course_no);
	}
	// 날짜별 코스 조회 
	public int selectCourseCount(LocalDate golf_date) {
		return courseMapper.selectDataCourse(golf_date);
	}
}
