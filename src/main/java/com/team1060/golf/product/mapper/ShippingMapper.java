package com.team1060.golf.product.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.team1060.golf.product.api.request.RegisterShippingRequest;

@Mapper
public interface ShippingMapper {

	int insertShipping(RegisterShippingRequest request);

	Long getMaxShippingNo();

}
