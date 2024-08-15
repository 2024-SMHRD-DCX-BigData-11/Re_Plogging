package com.smhrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.codec.digest.DigestUtils;

import com.smhrd.entity.Member;
import com.smhrd.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;

@Controller

public class MemberController {

	@Autowired
	private MemberRepository repo; // 레파지토리를 사용하기 위한 변수 선언

	@RequestMapping("/test")
	public String test() {
		return "test";
	}

	@RequestMapping("/main")
	public String goMain() {
		return "main";
	}

	@RequestMapping("/mypage")
	public String goMypage() {
		return "mypage";
	}

	@RequestMapping("/userInfoModify")
	public String goUserInfoModify() {
		return "userInfoModify";
	}
	
	@RequestMapping("/join")
	public String join(Member member) {
	    // 사용자가 입력한 비밀번호를 가져옴
	    String joinPw = member.getUserPw();
	    
	    // 비밀번호를 SHA-512 방식으로 암호화
	    String joinEncryptedPw = DigestUtils.sha512Hex(joinPw);
	    
	    // 암호화된 비밀번호를 Member 객체에 설정
	    member.setUserPw(joinEncryptedPw);
	    
	    repo.save(member);

	    // 저장 후 메인 페이지로 리다이렉트
	    return "redirect:/main";
	}

	/*
	 * @RequestMapping("/join") public String join(Member member) { // 0. 사전 준비 :
	 * Dependency 추가 > DB 연결 설정까지 // Table, DTO, Mapper와 같은 파일들이 있어야 함
	 * 
	 * System.out.println("회원가입 직전 비밀번호: " + member.getUserPw()); // 1. 데이터 수집 // 2.
	 * 기능 실행 > 사용자가 입력한 정보를 DB에 저장 ! repo.save(member);
	 * 
	 * // 3. View 선택 return "redirect:/main"; }
	 */

	@RequestMapping("/login")
	public String login(String userId, String userPw, jakarta.servlet.http.HttpSession session) {
		 // 1. 비밀번호를 암호화
         String loginEncryptedPw = DigestUtils.sha512Hex(userPw);
         
         // 2. 레포지토리에서 암호화된 비밀번호와 아이디로 사용자 조회
         Member member = repo.findByUserIdAndUserPw(userId, loginEncryptedPw);
         
         // 3. 조회 결과에 따른 처리
        if (member == null) {
            // 로그인 실패 처리
        	return "redirect:/main?loginError=true";
        } else {
            // 로그인 성공 처리 (세션에 사용자 정보 저장)
            session.setAttribute("user", member);
            return "redirect:/main";
        }
	}
	
	/*
	 * // 비밀번호 암호화 메소드 (SHA-512 방식) private String encryptPassword(String password)
	 * { return org.apache.commons.codec.digest.DigestUtils.sha512Hex(password); }
	 */


	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// 세션에서 모든 속성을 제거하여 로그아웃 처리
		session.invalidate();
		
		return "redirect:/main";
		
	}
	
	@RequestMapping("/nickCheck")
	public int  nickCheck(@RequestParam String userNick) {
		
		Member member = repo.findByUserNick(userNick);
		
		if(member == null) {
			//중복 안됨
			return 0;
		}
		
		else {
			return 1;
		}
		
		
	}

	// Get, Post Mapping 어노테이션을 이용해 URL 요청이 들어왔을 때, 요청 방식에 따라 다른 기능이 실행되게 할 수 있음.
	// 즉, URL 매핑이 겹쳐도 사용할 수 있게 됨.
	@GetMapping("/update")
	public void update() {
	}
	// 리턴타입이 void로 선언되면
	// >> URLMapping == view 이름
	// >> forward 방식만 가능함

	@PostMapping("/update")
	// 한 번에 수집하기 위해 Member member
	public String update(Member member, HttpSession session) {
		// 1. 데이터 수집
		// 2. 기능 실행(저장하기 기능)
		repo.save(member);
		session.setAttribute("user", member); // 사용자 정보 업데이터 된 걸로 덮어쓰기 됨 ㅇㅇ !
		// 3. View 선택
		return "redirect:/main";
	}

}
