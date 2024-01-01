package com.team1060.golf.product.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@ToString
public class RegisterBrandRequest {
	private String brand_name;
}
