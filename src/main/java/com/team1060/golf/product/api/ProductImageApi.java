package com.team1060.golf.product.api;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team1060.golf.product.api.request.RegisterProductImageRequest;
import com.team1060.golf.product.service.ProductImageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 상품 이미지 api
 * </pre>
 * @author GHL
 * @since 2023.12.26
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Log4j2
public class ProductImageApi {
	private final ProductImageService productImageService;
	
	// 이미지 추가
	@PostMapping("/product/image")
	@CrossOrigin
	public ResponseEntity<String> registerProductImage(@RequestBody RegisterProductImageRequest request) {
		try {
			productImageService.register(request);
			return ResponseEntity.ok("이미지 등록 완료");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 등록 실패");
		}
	}
	
	// 이미지 여러개 추가
	@PostMapping("/product/images")
	@CrossOrigin
	public ResponseEntity<String> registerProductImages(@RequestBody List<RegisterProductImageRequest> requests) {
		try {
			for (RegisterProductImageRequest request : requests) {
				try {
	                productImageService.register(request);
	            } catch (DuplicateKeyException e) {
	                log.warn("중복 키 위반 경로: {}", request.getPath());
	            }
			}
			return ResponseEntity.ok("이미지 등록 완료");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 등록 실패");
		}
	}

}
