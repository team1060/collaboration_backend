package com.team1060.golf.product.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team1060.golf.product.api.request.RegisterShippingRequest;
import com.team1060.golf.product.service.ShippingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shipping")
public class ShippingApi {
	private final ShippingService shippingService;

	@PostMapping("/shipping")
	@CrossOrigin
	public ResponseEntity<String> registerShipping(@RequestBody RegisterShippingRequest request) {
		try {
			shippingService.registerShipping(request);
			return ResponseEntity.ok("배송 완료");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("브랜드 추가 실패");
		}
	}
	
	@GetMapping("/shippingno")
	@CrossOrigin
	public Long getMaxShippingNo() {
		return shippingService.getMaxShippingNo();
	}
}
