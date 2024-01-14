package com.team1060.golf.product.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team1060.golf.product.api.request.RegisterBrandRequest;
import com.team1060.golf.product.api.request.RegisterPaymentOptionsRequest;
import com.team1060.golf.product.api.request.RegisterPaymentRequest;
import com.team1060.golf.product.service.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentApi {
	
	private final PaymentService paymentService;

	@PostMapping("/pay")
	@CrossOrigin
	public ResponseEntity<String> registerPayment(@RequestBody RegisterPaymentRequest request) {
		try {
			paymentService.registerPayment(request);
			return ResponseEntity.ok("주문 완료");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("브랜드 추가 실패");
		}
	}
	
	@PostMapping("/option")
	@CrossOrigin
	public ResponseEntity<String> registerPaymentOption(@RequestBody RegisterPaymentOptionsRequest request) {
		try {
			paymentService.registerPaymentOptions(request);
			return ResponseEntity.ok("주문 옵션 완료");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("브랜드 추가 실패");
		}
	}
	
	@PostMapping("/options")
	@CrossOrigin
	public ResponseEntity<String> registerPaymentOptions(@RequestBody List<RegisterPaymentOptionsRequest> requests) {
		try {
			for (RegisterPaymentOptionsRequest request : requests) {
				paymentService.registerPaymentOptions(request);
	        }
			return ResponseEntity.ok("주문 옵션 완료");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("브랜드 추가 실패");
		}
	}
	
	@GetMapping("/paymentno")
	@CrossOrigin
	public Long getMaxPaymentNo() {
		return paymentService.getMaxPaymentNo();
	}
	
	@GetMapping("/paymentByMemberCount")
	@CrossOrigin
	public int getPaymentByMemberCount(@RequestParam String email) {
		log.info("이메일");
		log.info(email);
		return paymentService.getPaymentByMemberCount(email);
	}
	
	@GetMapping("/paymentByMember")
    @CrossOrigin
    public List<Map<String, Object>> getPaymentByMember(@RequestParam String email) {
        List<Map<String, Object>> paymentList = paymentService.getPaymentByMember(email);
        String jsonString = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonString = objectMapper.writeValueAsString(paymentList);
            JsonNode rootNode = objectMapper.readTree(jsonString);
            String innerJsonArrayString = rootNode.get(0).get("result").asText();

            // 내부 JSON 배열을 다시 파싱
            paymentList = objectMapper.readValue(innerJsonArrayString, new TypeReference<List<Map<String, Object>>>() {});

            // options 필드 파싱 추가
            for (Map<String, Object> payment : paymentList) {
                String optionsJsonString = (String) payment.get("options");
                List<Map<String, Object>> optionsList = objectMapper.readValue(optionsJsonString, new TypeReference<List<Map<String, Object>>>() {});
                payment.put("options", optionsList);
            }

        } catch (JsonProcessingException e) {
            // 예외 처리
            e.printStackTrace();
        }

        // 생성된 JSON 문자열 반환
        return paymentList;
    }
	
}
