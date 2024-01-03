package com.team1060.golf.product.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterProductOptionRequest {
	private Long product_no;
	private String name;
	private int count;
}
