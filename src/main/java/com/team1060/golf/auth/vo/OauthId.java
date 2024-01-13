package com.team1060.golf.auth.vo;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OauthId {
	@SerializedName("email")
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