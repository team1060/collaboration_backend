package com.team1060.golf.product.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterShippingRequest {

	 private String email;	// 회원 이메일
     private String recipient;	// 수령인 이름
     private String destination;	// (선택) 집, 회사, 직접 입력
     private String contact_number;	// 수령인 전화번호
     private int is_default_shipping;	// 기본배송지=1, false=0
     private String roadAddrPart1; 	// 도로명주소 (참고주소 제외)
     private String roadAddrPart2;	// 참고주소
     private String zipNo;	// 우편번호
     private String addrDetail;	// 상세주소
}
