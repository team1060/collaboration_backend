package com.team1060.golf.infra.crawl.oauth.kakao.client;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors; // 임포트 추가

import org.springframework.stereotype.Component;

import com.team1060.golf.auth.vo.OauthMember;
import com.team1060.golf.auth.vo.OauthServerType;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class OauthMemberClientComposite {

    private final Map<OauthServerType, OauthMemberClient> mapping;

    public OauthMemberClientComposite(Set<OauthMemberClient> clients) {
        mapping = clients.stream()
                .collect(Collectors.toMap(
                        OauthMemberClient::supportServer,
                        client -> client
                ));
    }

    public OauthMember fetch(OauthServerType oauthServerType, String authCode) {
        return getClient(oauthServerType).fetch(authCode);
    }

    private OauthMemberClient getClient(OauthServerType oauthServerType) {
        return Optional.ofNullable(mapping.get(oauthServerType))
                .orElseThrow(() -> new RuntimeException("지원하지 않는 소셜 로그인 타입입니다: " + oauthServerType));
    }
}

