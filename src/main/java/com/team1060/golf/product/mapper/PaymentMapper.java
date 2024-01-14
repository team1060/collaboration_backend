package com.team1060.golf.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.team1060.golf.product.api.request.RegisterPaymentOptionsRequest;
import com.team1060.golf.product.api.request.RegisterPaymentRequest;

@Mapper
public interface PaymentMapper {

	int insertPayment(RegisterPaymentRequest request);

	int insertPaymentOptions(RegisterPaymentOptionsRequest request);
	
	Long getMaxPaymentNo();

	List<Map<String, Object>> getMaxPaymentByMember(String email);

	int getPaymentByMemberCount(String email);
}
