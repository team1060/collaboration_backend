package com.team1060.golf.product.vo;

import java.time.LocalDateTime;

/**
 * <pre>
 * 상품
 * </pre>
 * @author GHL
 * @since 2023.12.26
 */
public class Product {
	private Long product_no;
	private Long brand_no;
	private String product;
	private Integer price;
	private Float discount;
	private LocalDateTime regdate;
	private String benefit;
	private String no_interest_installment;
	private boolean is_shop_pickup;
	private boolean is_shop_delivery;
}
