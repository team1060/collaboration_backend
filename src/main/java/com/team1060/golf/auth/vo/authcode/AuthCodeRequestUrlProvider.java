package com.team1060.golf.auth.vo.authcode;

import com.team1060.golf.auth.vo.OauthServerType;

public interface AuthCodeRequestUrlProvider {
	OauthServerType supportServer();
	
	String provide();
}
