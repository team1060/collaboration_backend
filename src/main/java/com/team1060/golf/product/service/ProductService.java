package com.team1060.golf.product.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team1060.golf.product.api.request.RegisterProductRequest;
import com.team1060.golf.product.api.request.SearchProductRequest;
import com.team1060.golf.product.api.response.ViewAllProduct;
import com.team1060.golf.product.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductMapper productMapper;
	
	/**
	 * <pre>
	 * 모든 제품을 선택하는 메서드입니다.
	 * </pre>
	 * @author GHL
	 * @since 2023.12.26
	 * 
	 * @param brand         브랜드 필터
	 * @param regDate       등록 날짜 필터
	 * @param sortBy        정렬 기준 필드
	 * @param sortOrder     정렬 순서 ("asc" 또는 "desc" 등)
	 * @param page          페이지 번호
	 * @param size          페이지 크기
	 * @param searchKeyword 검색 키워드
	 * @return 제품 목록
	 */
	public List<ViewAllProduct> getList(SearchProductRequest request) {
		// TODO Auto-generated method stub
		return productMapper.selectAll(request);
	}

	public int register(RegisterProductRequest request) {
		return productMapper.create(request);
		
	}
}
