package com.team1060.golf.product.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.team1060.golf.product.api.request.RegisterProductRequest;
import com.team1060.golf.product.api.request.SearchProductRequest;
import com.team1060.golf.product.api.response.ViewAllProduct;
import com.team1060.golf.product.api.response.ViewProduct;

@Mapper
public interface ProductMapper {
	
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
    List<ViewAllProduct> selectAll(SearchProductRequest request);

	int createProduct(RegisterProductRequest request); // ????? 
	
	// 상품별 이미지 조회 
	List<Map<String, Object>> getProductListItem(Long product_no);
    
	// 전체상품 목록 
	List<Map<String, Object>> getProductList();
	
//    /**
//     * 페이지네이션을 위한 제품 목록 조회 메서드
//     *
//     * @param page  		페이지 번호
//     * @param size  		페이지 크기
//     * @return 제품 목록
//     */
//    List<ViewAllProduct> selectByPagination(
//    		@Param("page") int page,
//    		@Param("size") int size
//    );
    
	// 상품 등록 
	int insertProduct(RegisterProductRequest request);
    

}
