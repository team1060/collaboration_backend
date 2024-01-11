package com.team1060.golf.product.service;

import org.springframework.stereotype.Service;

import com.team1060.golf.product.api.request.RegisterShippingRequest;
import com.team1060.golf.product.mapper.ShippingMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShippingService {

	private final ShippingMapper shippingMapper;
	
	public int registerShipping(RegisterShippingRequest request) {
		return shippingMapper.insertShipping(request);
	}

	public Long getMaxShippingNo() {
		return shippingMapper.getMaxShippingNo();
	}

}
