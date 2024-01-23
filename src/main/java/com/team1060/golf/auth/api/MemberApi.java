package com.team1060.golf.auth.api;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team1060.golf.auth.api.request.EmailRequest;
import com.team1060.golf.auth.api.request.FindEmailAndPassword;
import com.team1060.golf.auth.api.request.PasswordChangeRequest;
import com.team1060.golf.auth.api.request.RegisterAndModifyMember;
import com.team1060.golf.auth.api.request.RemoveMember;
import com.team1060.golf.auth.api.request.FindEmailAndPassword.FindPassword;
import com.team1060.golf.auth.api.response.LoginResponse;
import com.team1060.golf.auth.api.response.ViewMember;
import com.team1060.golf.auth.api.response.ViewMember.LoginUser;
import com.team1060.golf.auth.api.response.ViewMember.LoginUserMypage;
import com.team1060.golf.auth.config.jwt.TokenProvider;
import com.team1060.golf.auth.service.MailService;
import com.team1060.golf.auth.service.MemberService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

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
	private final MailService mailService;
	private final TokenProvider tokenProvider;

	// 기존 회원 전체 조회
	@GetMapping("/join")
	@CrossOrigin
	public List<ViewMember> getEmailList() {
		return memberService.selectAll();
	}
	
	// 탈퇴 회원 전체 조회 
	@GetMapping("/deljoin")
	public List<ViewMember> selectDelUser() {
		return memberService.selectDelUser();
	}
	
	// 회원가입
	@PostMapping("/join")
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
	public ResponseEntity<LoginResponse> login(@RequestBody LoginUser user) {
		ViewMember member = memberService.select(user.getEmail());
		if (member != null && encoder.matches(user.getPassword(), member.getPassword())) {
			Duration expirationTime = Duration.ofDays(7); // 7일
			String token = tokenProvider.generateToken(user, expirationTime);
			LoginResponse response = new LoginResponse("로그인성공!", token, user.getEmail());
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 이메일 인증
	@PostMapping({"/login/email", "/findpw"})
	public String mailConfirm(@RequestBody EmailRequest emailDto)
			throws MessagingException, UnsupportedEncodingException {
		String authCode = mailService.sendEmail(emailDto.getEmail());
		return authCode;
	}

	// 이메일로 회원정보 조회
	@PostMapping("/getEmail/{email}")
	public ResponseEntity<ViewMember> getEmail(@PathVariable(name = "email") String email) {
		ViewMember member = memberService.select(email);
		if (member != null) {
			return ResponseEntity.ok().body(member);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// 회원정보 수정 로그인
	@PostMapping("/mypage/login")
	public ResponseEntity<String> login(@RequestBody LoginUserMypage user) {
		log.info("start");
		ViewMember member = memberService.select(user.getEmail());
		log.info(member);
		if (member != null && encoder.matches(user.getPassword(), member.getPassword())) {
			return ResponseEntity.ok("로그인 성공");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인실패");
		}
	}

	// 비밀번호 변경
	@PostMapping("/mypage/modify")
	@Transactional
	public ResponseEntity<String> update(@RequestBody PasswordChangeRequest user) {
		ViewMember member = memberService.select(user.getEmail());
		if (member != null && encoder.matches(user.getCurrentPassword(), member.getPassword())) {
			member.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			memberService.modifyMember(member);
			return ResponseEntity.ok("수정 완료");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("수정 실패");
		}
	}

	// 닉네임 변경
	@PutMapping("/mypage/modify")
	public ResponseEntity<String> nicknameUpdate(@RequestBody RegisterAndModifyMember user) {
		ViewMember member = memberService.select(user.getEmail());
		if(member != null) {
			member.setNickname(user.getNickname());
			memberService.modifyMember(member);
			return ResponseEntity.ok("닉네임 수정 완료");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("닉네임 수정 실패");
		}
	}

	// 01 08 jyp 추가 관리자 페이지
	@PutMapping("/admin/memberupdate/{email}")
	@CrossOrigin
	public ResponseEntity<String> adminUpdate(@PathVariable (name = "email")String email, @RequestBody RegisterAndModifyMember member) {
	  try {
	        memberService.adminMember(member);
	        return ResponseEntity.ok("회원 정보 수정 완료");
	  } catch (Exception e) {

			e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("권한이 없거나 회원 정보가 없습니다.");
	    }
	}

	// 탈퇴 신청
	@DeleteMapping("/mypage/login/remove/{email}")
	public ResponseEntity<String> removeMember(@PathVariable(name = "email")String email, @RequestBody RemoveMember user) {
	    ViewMember member =  memberService.select(email);
	    if(member != null && encoder.matches(user.getPassword(), member.getPassword())) {
	        memberService.removeMember(member.getEmail());
	        return ResponseEntity.ok("회원 삭제 완료");
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("회원 삭제 실패");
	    }
	}
	
	// 아이디 찾기 
	@PostMapping("/find")
	public ResponseEntity<String> findEmail(@RequestBody FindEmailAndPassword member) throws MessagingException, UnsupportedEncodingException{
		if(member != null) {
			ViewMember user =  memberService.findEmail(member);
			String send = mailService.sendEmailAccount(user.getEmail());
			return ResponseEntity.ok(send); 
		}else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("회원 실패");
	    }
	}
	
	// 비밀번호 찾기 
	@PostMapping("/modify/pw")
	public ResponseEntity<String> findPw(@RequestBody PasswordChangeRequest user) {
		ViewMember member = memberService.select(user.getEmail());
		if(member != null) {
			member.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			memberService.modifyMember(member);
			return ResponseEntity.ok("수정성공"); 
		}else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("수정 실패");
	    }
	}
	
	// 어드민 여부
	@GetMapping("/isAdmin")
	public boolean isAdmin(@Param("email") String email) {
		return memberService.checkAdminStatus(email);
	}
}
