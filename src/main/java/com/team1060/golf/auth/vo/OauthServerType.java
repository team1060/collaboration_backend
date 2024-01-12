package com.team1060.golf.auth.vo;

import static java.util.Locale.ENGLISH;

public enum OauthServerType {
	KAKAO,
	NAVER, 
	;
	
	public static OauthServerType fromName(String type) {
		return OauthServerType.valueOf(type.toUpperCase(ENGLISH));
	}
}
