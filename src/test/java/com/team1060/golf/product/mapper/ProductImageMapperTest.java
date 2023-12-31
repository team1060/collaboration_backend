package com.team1060.golf.product.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.team1060.golf.product.api.request.RegisterProductImageRequest;
import com.team1060.golf.product.api.request.RegisterProductRequest;

import lombok.extern.log4j.Log4j2;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Log4j2
public class ProductImageMapperTest {

	@Autowired
	private ProductImageMapper productImageMapper;
	
	@Test
	public void productMapperTest() {
		log.info(productImageMapper);
	}
	
//	public void selectAllTest() {
//		
//	}
	
	@Test
	public void createProductImageTest() {
		RegisterProductImageRequest request = RegisterProductImageRequest.builder()
				.uuid("abdc.jpg")
				.product_no(2l)
				.type("detail_1")
				.build();
//		request.getPath();
		log.info(request);
		int result = productImageMapper.insertProductImage(request);
		assertEquals(1, result);
	}
}
