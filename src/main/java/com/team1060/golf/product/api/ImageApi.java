package com.team1060.golf.product.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team1060.golf.product.api.request.RegisterBrandRequest;
import com.team1060.golf.product.api.request.firstRequest;
import com.team1060.golf.product.service.ImageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 상품 이미지 api
 * </pre>
 * @author GHL
 * @since 2023.12.26
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Log4j2
public class ImageApi {
	private final ImageService imageService;

}
