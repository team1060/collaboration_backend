package com.team1060.golf.product.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * <pre>
 * 
 * </pre>
 * @author GHL
 * @since 2023.12.26
 */
@Getter
@Builder
@AllArgsConstructor
@ToString
public class ViewBrand {
	private final Long brand_no;
	private final String brand_name;
}