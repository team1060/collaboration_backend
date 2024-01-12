package com.team1060.golf.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OauthMember {
	private OauthId oauthId;
	private Long id;
	private String nickname;
	private String role;
	private String email;
	private String phone_number;
	private String username;
	
	public Long id() {
        return id;
    }
	
	public OauthId oauthId() {
		return oauthId;
	}

    public String nickname() {
        return nickname;
    }
    
    public String email() {
        return email;
    }
 
    

}
