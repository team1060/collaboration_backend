package com.team1060.golf.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.team1060.golf.product.api.request.RegisterPaymentOptionsRequest;
import com.team1060.golf.product.api.request.RegisterPaymentRequest;
import com.team1060.golf.product.mapper.PaymentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	private final PaymentMapper paymentMapper;

	public int registerPayment(RegisterPaymentRequest request) {
		return paymentMapper.insertPayment(request);
	}
	
	public int registerPaymentOptions(RegisterPaymentOptionsRequest request) {
		return paymentMapper.insertPaymentOptions(request);
	}
	
	public Long getMaxPaymentNo() {
		return paymentMapper.getMaxPaymentNo();
	}

	public List<Map<String, Object>> getPaymentByMember(String email) {
		return paymentMapper.getMaxPaymentByMember(email);
	}

	public int getPaymentByMemberCount(String email) {
		return paymentMapper.getPaymentByMemberCount(email);
	}
}
