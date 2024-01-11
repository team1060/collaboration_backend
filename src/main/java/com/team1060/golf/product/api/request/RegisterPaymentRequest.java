package com.team1060.golf.product.api.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterPaymentRequest {
	private Long product_no;
	private String email;
	private Long shipping_no;
	private int status;
	private LocalDateTime payment_date;
	private String payment_method;
	private int amount;
	private String delivery_message;
	private int installment;
	private String card;
}
