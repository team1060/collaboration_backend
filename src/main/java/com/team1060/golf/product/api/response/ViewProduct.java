package com.team1060.golf.product.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ViewProduct {
	private final Long product_no;
	private final Long brand_no;
	private final String product;
	private final int price;
	private final float discount;
	private final int is_shop_pickup;
	private final int is_shop_delivery;
	
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
