package com.team1060.golf.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.team1060.golf.product.api.request.ModifyBrandRequest;
import com.team1060.golf.product.api.request.RegisterBrandRequest;
import com.team1060.golf.product.api.response.ViewBrand;
import com.team1060.golf.product.mapper.BrandMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {
	private final BrandMapper brandMapper;
	
	public int register(RegisterBrandRequest request) {
		return brandMapper.insertBrand(request);
	}

	public List<ViewBrand> getList() {
		return brandMapper.selectAllBrands();
	}

	public ViewBrand get(Long brand_no) {
		return brandMapper.selectBrand(brand_no);
	}

	public int modify(ModifyBrandRequest request) {
		return brandMapper.updateBrand(request);
		
	}

	public int remove(Long brand_no) {
		return brandMapper.deleteBrand(brand_no);
		
	}



}
