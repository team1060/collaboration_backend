package com.team1060.golf.product.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.team1060.golf.product.api.request.RegisterBrandRequest;
import com.team1060.golf.product.api.request.RegisterProductImageRequest;
import com.team1060.golf.product.api.request.RegisterProductOptionRequest;
import com.team1060.golf.product.api.request.RegisterProductRequest;
import com.team1060.golf.product.api.request.SearchProductRequest;
import com.team1060.golf.product.api.response.ViewAllProduct;
import com.team1060.golf.product.api.response.ViewProduct;
import com.team1060.golf.product.service.ProductImageService;
import com.team1060.golf.product.service.ProductOptionService;
import com.team1060.golf.product.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 상품 api & 상품별 이미지 조회 
 * </pre>
 * @author GHL, KJY
 * @since 2023.12.26
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class ProductApi {

		private final ProductService productService;
		private final ProductOptionService optionService;
		private final ProductImageService imageService;		
		
		// 전체 상품 조회 
		@GetMapping("/product")
		@CrossOrigin
		public List<Map<String, Object>> getProductList(){
			return productService.getProductList();
		}
		
		// 상품 상세페이지 
		@GetMapping("/products/{product_no}")
		@CrossOrigin
		public List<Map<String, Object>> getProduct(@PathVariable Long product_no){
			return productService.getProductListItem(product_no);
		}
		
		// 브랜드 추가
		@PostMapping("/admin/product")
		@CrossOrigin
		public ResponseEntity<String> registerProduct(@RequestBody RegisterProductRequest request) {
			try {
				productService.registerProduct(request);
				log.info("register 완료");
				return ResponseEntity.ok("상품 추가 완료");
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상품 추가 실패");
			}
		}
		
		// 브랜드 여러개 추가 (RequestBody를 List로 받고, forEach로 requests 돌리기
		@PostMapping("/admin/products")
		@CrossOrigin
		public ResponseEntity<String> registerProducts(@RequestBody List<RegisterProductRequest> requests) {
		    try {
		        for (RegisterProductRequest request : requests) {
		        	productService.registerProduct(request);
		        }
		        log.info("register 완료");
		        return ResponseEntity.ok("상품 추가 완료");
		    } catch (Exception e) {
		        e.printStackTrace();
		        log.error("상품 추가 실패 - 요청 데이터: " + e);
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상품 추가 실패" + e.getMessage());
		    }
		}
		// 상품 등록(이미지, 브랜드)
		@PostMapping("/admin/products/insert")
		@Transactional
		public ResponseEntity<String> insertProduct(
		        @RequestParam(name = "brand_no") Long brand_no,
		        @RequestParam(name = "product") String product,
		        @RequestParam(name = "price") int price,
		        @RequestParam(name = "discount") float discount,
		        @RequestParam(name = "benefit") String benefit,
		        @RequestParam(name = "no_interest_installment") String no_interest_installment,
		        @RequestParam(name = "is_shop_pickup") int is_shop_pickup,
		        @RequestParam(name = "is_shop_delivery") int is_shop_delivery,
		        @RequestParam(name = "name") String name,
		        @RequestParam(name = "count") int count,
		        @RequestParam("images") List<MultipartFile> files) {

		    try {
		        // 상품 정보 등록
		        RegisterProductRequest productRequest = RegisterProductRequest.builder()
		                .brand_no(brand_no)
		                .product(product)
		                .price(price)
		                .discount(discount)
		                .benefit(benefit)
		                .no_interest_installment(no_interest_installment)
		                .is_shop_pickup(is_shop_pickup)
		                .is_shop_delivery(is_shop_delivery)
		                .regdate(LocalDateTime.now())
		                .build();
		        productService.registerProduct(productRequest);
		        Long productNo = productRequest.getProduct_no();

		        // 상품 이미지 등록
		        List<RegisterProductImageRequest> imageRequests = new ArrayList<>();
		        for (MultipartFile file : files) {
		            String uuid = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "_" + file.getOriginalFilename();
		            String relativePath = "img/product/" + productNo + "/" + uuid; // 상대 경로
		            String absolutePath = "static/" + relativePath; // 절대 경로

		            try {
		                Path directoryPath = Paths.get("static/img/product/" + productNo);
		                Files.createDirectories(directoryPath);
		                Path filePath = Paths.get(absolutePath);
		                Files.write(filePath, file.getBytes());

		                RegisterProductImageRequest imageRequest = RegisterProductImageRequest.builder()
		                        .product_no(productNo)
		                        .uuid(uuid)
		                        .path(relativePath)
		                        .type("image_300")
		                        .build();

		                imageRequests.add(imageRequest);
		            } catch (IOException e) {
		                e.printStackTrace();
		                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 저장 실패: " + e.getMessage());
		            }
		        }
		        for (RegisterProductImageRequest imageRequest : imageRequests) {
		            imageService.register(imageRequest);
		        }

		        // 상품 옵션 등록
		        RegisterProductOptionRequest optionRequest = RegisterProductOptionRequest.builder()
		                .product_no(productNo)
		                .name(name)
		                .count(count)
		                .build();
		        optionService.register(optionRequest);

		        return ResponseEntity.ok("상품 추가 성공" + imageRequests);
		    } catch (Exception e) {
		        e.printStackTrace();
		        log.info("상품 추가 실패", e);
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상품 추가 실패: " + e.getMessage());
		    }
		}

		    


}
