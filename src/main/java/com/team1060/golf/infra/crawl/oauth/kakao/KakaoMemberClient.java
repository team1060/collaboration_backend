package com.team1060.golf.infra.crawl.oauth.kakao;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.team1060.golf.auth.vo.OauthMember;
import com.team1060.golf.auth.vo.OauthServerType;
import com.team1060.golf.infra.crawl.oauth.kakao.client.KakaoApiClient;
import com.team1060.golf.infra.crawl.oauth.kakao.client.OauthMemberClient;
import com.team1060.golf.infra.crawl.oauth.kakao.dto.KakaoMemberResponse;
import com.team1060.golf.infra.crawl.oauth.kakao.dto.KakaoToken;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OauthMemberClient {

    private final KakaoApiClient kakaoApiClient;
    private final KakaoOauthConfig kakaoOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.KAKAO;
    }

    @Override
    public OauthMember fetch(String authCode) {
        KakaoToken tokenInfo = kakaoApiClient.fetchToken(tokenRequestParams(authCode)); // 조회
        KakaoMemberResponse kakaoMemberResponse =
                kakaoApiClient.fetchMember("Bearer " + tokenInfo.accessToken());  // 회원정보 받음 
        return kakaoMemberResponse.toDomain();  // 변환 
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoOauthConfig.clientId());
        params.add("redirect_uri", kakaoOauthConfig.redirectUri());
        params.add("code", authCode);
        params.add("client_secret", kakaoOauthConfig.clientSecret());
        return params;
    }
}

