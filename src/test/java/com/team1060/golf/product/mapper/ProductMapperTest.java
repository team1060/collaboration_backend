package com.team1060.golf.product.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.team1060.golf.product.api.request.RegisterProductRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Log4j2
public class ProductMapperTest {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Test
	public void productMapperTest() {
		log.info(productMapper);
	}
	
//	public void selectAllTest() {
//		
//	}
	
	@Test
	public void createProductTest() {
		RegisterProductRequest request = RegisterProductRequest.builder()
		.brand_no(1l)
		.is_shop_pickup(1)
		.is_shop_delivery(1)
		.discount(0.42f)
		.product("핑 G425 MAX 드라이버 (ALTA J CB Slate)")
		.price(790000)
		.benefit("국민 10% 최대 5만원 즉시할인")
		.no_interest_installment("2~6개월 카드 무이자 할부지원")
		.build();
		log.info(request);
		int result = productMapper.createProduct(request);
		assertEquals(1, result);
	}
}
