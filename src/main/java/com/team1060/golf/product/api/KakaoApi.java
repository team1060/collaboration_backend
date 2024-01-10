package com.team1060.golf.product.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team1060.golf.product.service.KakaoPayService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/payment")
@RequiredArgsConstructor
public class KakaoApi {

	private final KakaoPayService kakaoPayService;

}
