package com.team1060.golf.product.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.team1060.golf.product.api.request.RegisterProductOptionRequest;

@Mapper
public interface ProductOptionMapper {

	public int insertProductOption(RegisterProductOptionRequest request);

}
