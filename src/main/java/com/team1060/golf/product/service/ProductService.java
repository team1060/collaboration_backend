package com.team1060.golf.product.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.team1060.golf.product.api.request.RegisterProductRequest;
import com.team1060.golf.product.api.request.SearchProductRequest;
import com.team1060.golf.product.api.response.ViewAllProduct;
import com.team1060.golf.product.api.response.ViewProduct;
import com.team1060.golf.product.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductMapper productMapper;
	
	/**
	 * <pre>
	 * 모든 상품을 출력하는 서비스 메서드입니다.
	 * 파라미터를 통해 검색 조건줄을 추가할 수 있습니다.
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
	
	/**
	 * <pre>
	 * 상품을 create하는 서비스 메서드입니다.
	 * </pre>
	 * @author GHL
	 * @since 2023.12.26
	 * 
	 */
	
	// 상품 등록 
	public int registerProduct(RegisterProductRequest request) {
		return productMapper.insertProduct(request);
		
	}
	
	public List<Map<String, Object>>getProductListItem(Long product_no) {
		return productMapper.getProductListItem(product_no);
	}
	
	public List<Map<String, Object>>getProductList() {
		return productMapper.getProductList();
	}
	
	
//	public List<ViewProduct> searchProducts(SearchProductRequest request) {
//	}
}
