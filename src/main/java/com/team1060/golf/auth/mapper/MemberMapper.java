package com.team1060.golf.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.team1060.golf.auth.api.request.FindEmailAndPassword;
import com.team1060.golf.auth.api.request.RegisterAndModifyMember;
import com.team1060.golf.auth.api.response.ViewMember;
import com.team1060.golf.auth.api.response.ViewMember.LoginUser;

import io.opentelemetry.sdk.metrics.View;

/**
 * <pre>
 * 회원 api 
 * </pre>
 * @author KJY
 * @since 2023.12.25
 */

@Mapper
public interface MemberMapper {
	
	// 회원가입 
	int insert(RegisterAndModifyMember member);
	// 로그인 
	ViewMember login(LoginUser user);
	
	// email 중복 체크 
	boolean checkedEmail(String email);
	
	// 아래는 아직 테스트 안함 
	
	// 회원 전체 조회 
	List<ViewMember> getList();
	// 회원 1명 조회 
	ViewMember select(String email);
	// 회원 수정 
	int update(ViewMember member);
	// 회원 삭제 
	int delete(String email);
	
	// 아이디찾기 
	ViewMember findEmail(FindEmailAndPassword member);
	
	// 관리자페이지 수정
	int adminupdate(RegisterAndModifyMember member);
	
	// 어드민 상태 체크
	boolean checkAdminStatus(String email);
	
	List<ViewMember> selectDelUser();
}
