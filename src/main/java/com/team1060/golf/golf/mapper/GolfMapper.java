package com.team1060.golf.golf.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.team1060.golf.golf.api.request.RegisterAndModifyGolf;
import com.team1060.golf.golf.api.response.ViewGolf;

/**
 * <pre>
 * 골프장 CRUD
 * </pre>
 * @author KJY
 * @since 2023.12.20
 */

@Mapper
public interface GolfMapper {
	
	// 골프장 전체 조회 
	List<ViewGolf> getList();
	// 골프장 등록 
	int insert(RegisterAndModifyGolf golf);
    // 골프장 1개 조회
    ViewGolf select(Long golf_no);
    // 골프장 수정
    int update(RegisterAndModifyGolf golf);
    // 골프장 삭제
    int delete(Long golf_no);
    // 지역별 조회
    List<ViewGolf> getRegionList(String region);
}
