package com.team1060.golf.product.api;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team1060.golf.product.api.request.RegisterProductImageRequest;
import com.team1060.golf.product.api.request.RegisterProductOptionRequest;
import com.team1060.golf.product.service.ProductImageService;
import com.team1060.golf.product.service.ProductOptionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 상품 옵션 api
 * </pre>
 * @author GHL
 * @since 2023.12.26
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductOptionApi {
	private final ProductOptionService productOptionService;
	
	// 상품 옵션 추가
	@PostMapping("admin/product/option")
	@CrossOrigin
	public ResponseEntity<String> registerProductImage(@RequestBody RegisterProductOptionRequest request) {
		try {
			productOptionService.register(request);
			return ResponseEntity.ok("옵션 등록 완료");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("옵션 등록 실패");
		}
	}
	
	// 옵션 여러개 추가
	@PostMapping("admin/product/options")
	@CrossOrigin
	public ResponseEntity<String> registerProductImages(@RequestBody List<RegisterProductOptionRequest> requests) {
		try {
			for (RegisterProductOptionRequest request : requests) {
				try {
					productOptionService.register(request);
	            } catch (DuplicateKeyException e) {
	            	e.printStackTrace();
	            }
			}
			return ResponseEntity.ok("옵션 등록 완료");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("옵션 등록 실패");
		}
	}

}

