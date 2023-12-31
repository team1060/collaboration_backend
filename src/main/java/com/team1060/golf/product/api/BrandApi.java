package com.team1060.golf.product.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team1060.golf.product.api.request.ModifyBrandRequest;
import com.team1060.golf.product.api.request.RegisterBrandRequest;
import com.team1060.golf.product.api.response.ViewBrand;
import com.team1060.golf.product.service.BrandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 브랜드 api
 * </pre>
 * @author GHL
 * @since 2023.12.26
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class BrandApi {

		private final BrandService brandService;

		// 브랜드 추가
		@PostMapping("/admin/brand")
		@CrossOrigin
		public ResponseEntity<String> registerBrand(@RequestBody RegisterBrandRequest request) {
			try {
				brandService.register(request);
				log.info("register");
				return ResponseEntity.ok("브랜드 추가 완료");
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("브랜드 추가 실패");
			}
		}
		
		// 브랜드 여러개 추가 (RequestBody를 List로 받고, forEach로 requests 돌리기
		@PostMapping("/admin/brands")
		@CrossOrigin
		public ResponseEntity<String> registerBrands(@RequestBody List<RegisterBrandRequest> requests) {
		    try {
		    	int idx = 1;
		        for (RegisterBrandRequest request : requests) {
		        	log.info(idx);
		        	idx++;
		            brandService.register(request);
		        }
		        log.info("register");
		        return ResponseEntity.ok("브랜드 추가 완료");
		    } catch (Exception e) {
		    	
		        e.printStackTrace();
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("브랜드 추가 실패");
		    }
		}

		// 브랜드 전체 조회
		@GetMapping("/brand")
		@CrossOrigin
		public List<ViewBrand> findAll() {
			return brandService.getList();
		}

		// 브랜드 1개 조회
		@GetMapping("/brand/{brand_no}")
		@CrossOrigin
		public ViewBrand find(@PathVariable(name = "brand_no") Long brand_no) {
			return brandService.get(brand_no);
		}
		
		// 브랜드 단일 수정
//		@PutMapping("/admin/brand")
		@CrossOrigin
		public ResponseEntity<String> modifyBrand(@RequestBody ModifyBrandRequest request) {
			try {
				brandService.modify(request);
				return ResponseEntity.ok("브랜드 수정 완료");
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("브랜드 수정 실패");
			}
		}

		// 브랜드 삭제
//		@DeleteMapping("/admin/brand/{brand_no}")
		public ResponseEntity<String> removeCourse(@PathVariable(name = "brand_no") Long brand_no) {
			try {
				brandService.remove(brand_no);
				return ResponseEntity.ok("브랜드 삭제 완료");
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("브랜드 삭제 실패" + e.getMessage());
			}
		}
		
		
}
