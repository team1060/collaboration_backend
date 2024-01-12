package com.team1060.golf.auth.service;

import org.springframework.stereotype.Service;

import com.team1060.golf.auth.mapper.AuthMemberMapper;
import com.team1060.golf.auth.vo.OauthMember;
import com.team1060.golf.auth.vo.OauthServerType;
import com.team1060.golf.auth.vo.authcode.AuthCodeRequestUrlProviderComposite;
import com.team1060.golf.infra.crawl.oauth.kakao.client.OauthMemberClientComposite;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class OauthService {
	
	private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
	private final OauthMemberClientComposite oauthMemberClientComposite;
	private final AuthMemberMapper authMemberMapper;
	
	public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }
	
	public OauthMember login(OauthServerType oauthServerType, String authcode) {
	    OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authcode);
	    log.info("코드 - OauthId: {}, Username: {}, nickname: {}", oauthMember.getOauthId(), oauthMember.getEmail(), oauthMember.getNickname());

	    if (oauthMember != null) {
	        log.info("로그인 시도 - OauthMember 정보: {}", oauthMember);
	        OauthMember saved = authMemberMapper.select(oauthMember.getOauthId().getOauthServerId());
	        if (saved != null) {
	            return saved;
	        } else {
	            int insert = authMemberMapper.authJoin(oauthMember);
	            if (insert > 0) {
	                // 회원 등록이 성공했을 경우에만 새로운 회원 정보를 반환
	                return oauthMember;
	            }
	            return null; 
	        }
	    }
	    return null; 
	}
}

