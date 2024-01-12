package com.team1060.golf.auth.presentation;

import org.springframework.core.convert.converter.Converter;

import com.team1060.golf.auth.vo.OauthServerType;

public class OauthServerTypeConverter implements Converter<String, OauthServerType>{
	
	 @Override
	    public OauthServerType convert(String source) {
	        return OauthServerType.fromName(source);
	    }
}
