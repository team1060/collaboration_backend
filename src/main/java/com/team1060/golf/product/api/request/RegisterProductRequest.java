package com.team1060.golf.product.api.request;


import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString	
@Setter
public class RegisterProductRequest {
	private Long product_no;
	private Long brand_no;
	private String product;
	private int price;
	private float discount;
	private String benefit;
	private String no_interest_installment;
	private int is_shop_pickup;
	private int is_shop_delivery;
	private LocalDateTime regdate; // regdate 필드 추가
	
	public RegisterProductRequest() {
        this.regdate = LocalDateTime.now();
    }
}
