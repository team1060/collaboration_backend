package com.team1060.golf.auth.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.team1060.golf.auth.api.request.RegisterAndModifyMember;
import com.team1060.golf.auth.api.response.ViewMember;
import com.team1060.golf.auth.api.response.ViewMember.LoginUser;
import com.team1060.golf.auth.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 회원 api
 * </pre>
 * 
 * @author KJY 
 * @since 2023.12.25
 */

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApi {
	private final MemberService memberService;
	private final PasswordEncoder encoder;
	
	// 기존 회원 전체 조회 
	@GetMapping("/join")
	@CrossOrigin
	public List<ViewMember> getEmailList(){
		return memberService.selectAll();
	}
	
	// 회원가입
	@PostMapping("/join")
	@CrossOrigin
	public ResponseEntity<String> joinMember(@RequestBody RegisterAndModifyMember member) {
		try {
			member.encodePassword(encoder);
			memberService.register(member);
			return ResponseEntity.ok("회원가입 성공 ");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 실패");
		}
	}

	// 로그인
	@PostMapping("/login")
	@CrossOrigin
	public ResponseEntity<String> login(@RequestBody LoginUser user) {
		ViewMember member = memberService.select(user.getEmail());
		if (member != null && encoder.matches(user.getPassword(), member.getPassword())) {
			return ResponseEntity.ok("로그인 성공");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인실패");
		}
	}

	// 이메일 중복 체크
//	@GetMapping("/join")
//	@CrossOrigin
//	public ResponseEntity<Boolean> checkedEmail(@RequestParam("email") String email) {
//	    boolean isCheck = memberService.checkedEmail(email);
//	    return ResponseEntity.ok(isCheck);
//	}

}
