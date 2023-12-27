package com.team1060.golf.product.api;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team1060.golf.product.api.request.RegisterBrandRequest;
import com.team1060.golf.product.api.request.RegisterProductRequest;
import com.team1060.golf.product.api.request.SearchProductRequest;
import com.team1060.golf.product.api.response.ViewAllProduct;
import com.team1060.golf.product.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 상품 api
 * </pre>
 * @author GHL
 * @since 2023.12.26
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Log4j2
public class ProductApi {

		private final ProductService productService;
		
		// 상품 기본 조회
		@GetMapping("/product")
		@CrossOrigin
		public List<ViewAllProduct> findAll(@ModelAttribute SearchProductRequest request) {
			return productService.getList(request);
		}
		
		// 브랜드 추가
		@PostMapping("/product")
		@CrossOrigin
		public ResponseEntity<String> registerProduct(@RequestBody RegisterProductRequest request) {
			try {
				productService.register(request);
				log.info("register 완료");
				return ResponseEntity.ok("상품 추가 완료");
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상품 추가 실패");
			}
		}
		
		// 브랜드 여러개 추가 (RequestBody를 List로 받고, forEach로 requests 돌리기
		@PostMapping("/products")
		@CrossOrigin
		public ResponseEntity<String> registerProducts(@RequestBody List<RegisterProductRequest> requests) {
		    try {
		        for (RegisterProductRequest request : requests) {
		        	productService.register(request);
		        }
		        log.info("register 완료");
		        return ResponseEntity.ok("상품 추가 완료");
		    } catch (Exception e) {
		        e.printStackTrace();
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상품 추가 실패");
		    }
		}
}
