package com.team1060.golf.product.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.team1060.golf.product.api.request.RegisterProductImageRequest;

@Mapper
public interface ProductImageMapper {

	public int insertProductImage(RegisterProductImageRequest request);

}
