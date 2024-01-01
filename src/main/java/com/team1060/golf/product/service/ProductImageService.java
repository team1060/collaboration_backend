package com.team1060.golf.product.service;

import org.springframework.stereotype.Service;

import com.team1060.golf.product.api.request.RegisterProductImageRequest;
import com.team1060.golf.product.mapper.ProductImageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductImageService {
	
	private final ProductImageMapper productImageMapper;
	
	public int register(RegisterProductImageRequest request) {
		return productImageMapper.insertProductImage(request);
	}

}
