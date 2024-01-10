package com.team1060.golf.product.api.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ViewProduct {
	private Long product_no;
	private Long brand_no;
	private String product;
	private int price;
	private float discount;
	private String benefit;
	private String no_interest_installment;
	private int is_shop_pickup;
	private int is_shop_delivery;
	private LocalDateTime regdate;
	
	 public static ViewProduct fromViewAllProduct(ViewAllProduct viewAllProduct) {
	        return ViewProduct.builder()
	                .product_no(viewAllProduct.getProduct_no())
	                .brand_no(viewAllProduct.getBrand_no())
	                .product(viewAllProduct.getProduct())
	                .price(viewAllProduct.getPrice())
	                .discount(viewAllProduct.getDiscount())
	                .is_shop_pickup(viewAllProduct.getIs_shop_pickup())
	                .is_shop_delivery(viewAllProduct.getIs_shop_delivery())
	                .build();
	    }
}
