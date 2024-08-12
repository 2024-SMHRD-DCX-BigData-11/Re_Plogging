package com.smhrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("/sms")
	public String goSms() {
		return "sms";
	}
	
	
	@RequestMapping("/join")
	public String join( Member member ) {
		// 0. 사전 준비 : Dependency 추가 > DB 연결 설정까지
		//				Table, DTO, Mapper와 같은 파일들이 있어야 함
		
		// 1. 데이터 수집
		// 2. 기능 실행 > 사용자가 입력한 정보를 DB에 저장 !
		repo.save(member);
		
		// 3. View 선택
		return "redirect:/main";
	}
	
	
	@RequestMapping("/login")
	public String login( String userId, String userPw, jakarta.servlet.http.HttpSession session ) {
		// 1. 데이터 수집
		// 2. 기능 실행
		Member member = repo.findByUserIdAndUserPw(userId, userPw);
		
		session.setAttribute("user", member);	// session에 user 이름으로 member 객체 저장
		
		// 3. View 선택(동기식이니까)
		return "redirect:/main";
	}
	
	
	// Get, Post Mapping 어노테이션을 이용해 URL 요청이 들어왔을 때, 요청 방식에 따라 다른 기능이 실행되게 할 수 있음.
	// 즉, URL 매핑이 겹쳐도 사용할 수 있게 됨.
	@GetMapping("/update")
	public void update() {}
	// 리턴타입이 void로 선언되면
	// >> URLMapping == view 이름
	// >> forward 방식만 가능함
	
	@PostMapping("/update")
	// 한 번에 수집하기 위해 Member member
	public String update( Member member, HttpSession session ) {
		// 1. 데이터 수집
		// 2. 기능 실행(저장하기 기능)
		repo.save(member);
		session.setAttribute("user", member);	// 사용자 정보 업데이터 된 걸로 덮어쓰기 됨 ㅇㅇ !
		// 3. View 선택
		return "redirect:/main";
	}
	
	
}
