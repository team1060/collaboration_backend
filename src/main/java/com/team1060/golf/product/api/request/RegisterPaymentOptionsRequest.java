package com.team1060.golf.product.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPaymentOptionsRequest {
	private Long p_buy_no;
    private Long option_no;
    private int count;
    private int price;
}
