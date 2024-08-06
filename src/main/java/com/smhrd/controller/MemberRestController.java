package com.smhrd.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.entity.Member;
import com.smhrd.repository.MemberRepository;

@RestController
public class MemberRestController {
	
	@Autowired
	private MemberRepository repo;
	
	@RequestMapping("/check")
	public String check( String email ) {
		// 1. 데이터 수집
		// 2. 기능 실행(email을 기준으로 찾는 메서드)
		Optional<Member> member = repo.findById(email);
		// Optional : 특별한 기능은 없음 ! 포장지 생각. why 감싸 ? => null 값을 처리하기 용이하게 하기 위한 객체임
		
		boolean check = member.isEmpty();	// 비어있니?
		// existsById를 이용해 체크할수도 있음
		
		
		// 3. 데이터 응답
		return check + "";
	}
	
}
