package com.team1060.golf.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OauthId {
    private String oauthServerId;
    private OauthServerType oauthServerType;

    public String getOauthServerId() {
        return oauthServerId;
    }

    public OauthServerType getOauthServerType() {
        return oauthServerType;
    }
    
    public String getOauthId() {
        return oauthServerId;
    }
    
}