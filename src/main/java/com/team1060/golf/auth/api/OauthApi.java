package com.team1060.golf.auth.api;

import java.time.Duration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team1060.golf.auth.config.jwt.TokenProvider;
import com.team1060.golf.auth.service.OauthService;
import com.team1060.golf.auth.vo.OauthMember;
import com.team1060.golf.auth.vo.OauthServerType;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
@Log4j2
public class OauthApi {
	
	private final OauthService oauthService;
	private final TokenProvider tokenProvider;
	
	@SneakyThrows
    @GetMapping("/{oauthServerType}")
    ResponseEntity<Void> redirectAuthCodeRequestUrl(
            @PathVariable(name = "oauthServerType") OauthServerType oauthServerType,
            HttpServletResponse response
    ) {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }
	
	@GetMapping("/login/{oauthServerType}")
	public ResponseEntity<String> login(
	        @PathVariable(name = "oauthServerType") OauthServerType oauthServerType,
	        @RequestParam(name = "code") String code
	) {
	    OauthMember user = oauthService.login(oauthServerType, code);
	    return processLoginResult(user);
	}

	// 토큰 생성 
	private ResponseEntity<String> processLoginResult(OauthMember user) {
	    if (user != null) {
	        Duration expirationTime = Duration.ofDays(7); // 7일
	        String token = tokenProvider.generateAuthToken(user, expirationTime);
	        log.info("토큰: {}", token);
	        return ResponseEntity.ok(token);
	    } else {
	        return ResponseEntity.badRequest().body("로그인 실패");
	    }
	}
}
