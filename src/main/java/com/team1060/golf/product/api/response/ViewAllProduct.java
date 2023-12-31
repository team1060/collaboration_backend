package com.team1060.golf.product.api.response;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class ViewAllProduct {
	private final Long product_no;
	private final Long brand_no;
	private final String product;
	private final int price;
	private final ZonedDateTime regdate;
	private final Float discount;
	private final int is_shop_pickup;
	private final int is_shop_delivery;
	
	private List<Image> images;
	private List<Review> reviews;
	
	@Getter
	@Builder
	@AllArgsConstructor
	public static class Image {
		private final String uuid;
		private final Long product_no;
		private final String path;
		private final String type;
	}
		
	@Getter
	@Builder
	@AllArgsConstructor
	public static class Review {
		private final Long review_no;
		private final Long product_no;
		private final Float rate;
	}
}
