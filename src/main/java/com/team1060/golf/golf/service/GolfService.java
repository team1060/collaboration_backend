package com.team1060.golf.golf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.team1060.golf.golf.api.request.RegisterAndModifyGolf;
import com.team1060.golf.golf.api.response.ViewGolf;
import com.team1060.golf.golf.mapper.GolfMapper;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 골프장 CRUD
 * </pre>
 * @author KJY
 * @since 2023.12.20
 */

@Service
@RequiredArgsConstructor
public class GolfService {
	
	private final GolfMapper golfMapper;
	
	// 등록 
	public int register(RegisterAndModifyGolf request) {
		return golfMapper.insert(request);
	}
	
	// 전체조회 
	public List<ViewGolf> selectAll(){
		return golfMapper.getList();
	}
	
	// 1개조회 
	public ViewGolf select(Long golf_no) {
		return golfMapper.select(golf_no);
	}
	// 수정 
	public int modifyGolf(RegisterAndModifyGolf request) {
		return golfMapper.update(request);
	}
	// 삭제 
	public int removeGolf(Long golf_no) {
		return golfMapper.delete(golf_no);
	}
	
	// 지역별 조회
	public List<ViewGolf> selectRegion(String region){
		return golfMapper.getRegionList(region);
	}
}
