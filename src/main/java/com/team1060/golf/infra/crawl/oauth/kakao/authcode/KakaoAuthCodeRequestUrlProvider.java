package com.team1060.golf.infra.crawl.oauth.kakao.authcode;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.team1060.golf.auth.vo.OauthServerType;
import com.team1060.golf.auth.vo.authcode.AuthCodeRequestUrlProvider;
import com.team1060.golf.infra.crawl.oauth.kakao.KakaoOauthConfig;

@Component
@RequiredArgsConstructor
public class KakaoAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

    private final KakaoOauthConfig kakaoOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.KAKAO;
    }

    @Override
    public String provide() {
        return UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com/oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", kakaoOauthConfig.clientId())
                .queryParam("redirect_uri", kakaoOauthConfig.redirectUri())
                .queryParam("scope", kakaoOauthConfig.scope())
                .toUriString();
    }
}

