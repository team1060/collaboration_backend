package com.team1060.golf.product.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class ProductMapperTest {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Test
	public void productMapperTest() {
		log.info(productMapper);
	}
	
	public void selectAllTest() {
		
	}
}
