package com.team1060.golf.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.team1060.golf.product.api.request.RegisterProductImageRequest;
import com.team1060.golf.product.api.response.ViewAllProduct;

@Mapper
public interface ProductImageMapper {

	int insertProductImage(RegisterProductImageRequest request);
	
	// 상품 번호 별 이미지 조회 
	List<ViewAllProduct> getAllProductList(Long product_no);

}
