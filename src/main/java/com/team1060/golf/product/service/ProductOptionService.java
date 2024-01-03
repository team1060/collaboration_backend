package com.team1060.golf.product.service;

import org.springframework.stereotype.Service;

import com.team1060.golf.product.api.request.RegisterProductImageRequest;
import com.team1060.golf.product.api.request.RegisterProductOptionRequest;
import com.team1060.golf.product.mapper.ProductImageMapper;
import com.team1060.golf.product.mapper.ProductOptionMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductOptionService {
	
	private final ProductOptionMapper productOptionMapper;
	
	public int register(RegisterProductOptionRequest request) {
		return productOptionMapper.insertProductOption(request);
	}

}
