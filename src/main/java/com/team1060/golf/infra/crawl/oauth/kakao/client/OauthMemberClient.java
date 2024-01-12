package com.team1060.golf.infra.crawl.oauth.kakao.client;

import com.team1060.golf.auth.vo.OauthMember;
import com.team1060.golf.auth.vo.OauthServerType;

public interface OauthMemberClient {
	
	OauthServerType supportServer();
    OauthMember fetch(String code);
}
