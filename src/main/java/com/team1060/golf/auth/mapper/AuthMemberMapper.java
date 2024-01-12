package com.team1060.golf.auth.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.team1060.golf.auth.api.request.RegisterAndModifyMember;
import com.team1060.golf.auth.api.response.ViewMember;
import com.team1060.golf.auth.vo.OauthMember;

@Mapper
public interface AuthMemberMapper {
	
	
	// 가입한 아이디 있는지 찾기 
	OauthMember select(String oauthServerId);
	
	// 없으면 회원가입 
	int authJoin(OauthMember member);
	
	// 회원 1명 조회 
	ViewMember get(String email);
	
	// 회원가입 
	int insertNaver(RegisterAndModifyMember member);
}
