package com.team1060.golf.auth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.team1060.golf.auth.api.request.FindEmailAndPassword;
import com.team1060.golf.auth.api.request.RegisterAndModifyMember;
import com.team1060.golf.auth.api.response.ViewMember;
import com.team1060.golf.auth.api.response.ViewMember.LoginUser;
import com.team1060.golf.auth.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 회원 service
 * </pre>
 * @author KJY
 * @since 2023.12.25
 */

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberMapper memberMapper;
	
	// 회원가입 
	public int register(RegisterAndModifyMember member) {
		return memberMapper.insert(member);
	}
	// 로그인 
	public ViewMember login(LoginUser user) {
		return memberMapper.login(user);
	}
	// email 중복체크
	public Boolean checkedEmail(String email) {
		return memberMapper.checkedEmail(email);
	}
	// 회원 전체 조회 
	public List<ViewMember> selectAll(){
		return memberMapper.getList();
	}
	// 회원 1명 조회 
	public ViewMember select(String email) {
		return memberMapper.select(email);
	}
	// 회원 수정 
	public int modifyMember(ViewMember member) {
		return memberMapper.update(member);
	}
	// 회원 삭제 
	public int removeMember(String email) {
		return memberMapper.delete(email);
	}
	// 회원 아이디 찾기 
	public ViewMember findEmail(FindEmailAndPassword member) {
		return memberMapper.findEmail(member);
	}
	// 관리자 회원 수정
	public int adminMember(RegisterAndModifyMember member) {
		return memberMapper.adminupdate(member);
	}
	
	// 어드민 여부 확인
	public boolean checkAdminStatus(String email) {
		return memberMapper.checkAdminStatus(email);
	}
	
	// 탈퇴 테이블 전체 조회 
	public List<ViewMember> selectDelUser(){
		return memberMapper.selectDelUser();
	}
}
