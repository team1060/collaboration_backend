package com.team1060.golf.golf.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.team1060.golf.golf.api.request.RegisterAndModifyCourse;
import com.team1060.golf.golf.api.response.ViewCourse;

/**
 * <pre>
 * 코스 CRUD
 * </pre>
 * @author KJY
 * @since 2023.12.20
 */

@Mapper
public interface CourseMapper {
	
	// 코스등록
	int insert(RegisterAndModifyCourse course);
	// 코스 전체 조회 
	List<ViewCourse> getList();
	// 예약 가능여부 조회 
	boolean getCourseNo(Long course_no);
	// 코스1개조회
	ViewCourse select(Long course_no);
	// 골프장별 코스 조회 
	List<ViewCourse> getGolfList(Long golf_no);
	// 코스 수정 
	int update(RegisterAndModifyCourse course);
	// 코스 상태 1개 수정 
	int updateCourse(int golf_status);
	// 코스 삭제 
	int delete(Long course_no);
	// 날짜별 코스 갯수 조회
	Integer selectDataCourse(@Param("golf_date") LocalDate golf_date);
	
}
