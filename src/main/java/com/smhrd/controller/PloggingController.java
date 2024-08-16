package com.smhrd.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd.entity.Member;
import com.smhrd.entity.Plogging;
import com.smhrd.repository.MemberRepository;
import com.smhrd.repository.PloggingRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class PloggingController {
	
	@Autowired
	private MemberRepository repo; // 레파지토리를 사용하기 위한 변수 선언
	
	@Autowired
	private PloggingRepository prepo;
	
//	@RequestMapping("/qrSuccess")
//	public String goqrSuccess() {
//		return "qrSuccess";
//	}
	
	@RequestMapping("/myplogging")
	public String gomyplogging() {
		return "myplogging";
	}
	
	@RequestMapping("/ploggingList")
	public String goploggingList() {
		return "ploggingList";
	}
	
	public String loginMember( String userId, String userPw, String targetUrl, HttpSession session ) {
    	Member member = repo.findByUserIdAndUserPw( userId, userPw );
    	if( member != null ) {
    		session.setAttribute("userId", userId );
    		return targetUrl;	
    	} else {
    		return "/boot/loginFail";
    	}
    }

	@RequestMapping("/qrSuccess")
    public String loginCheck( HttpSession session ) {
    	Member member = (Member) session.getAttribute("user");
    	if( member != null ) {
    		return "myplogging";
    	} else {
    		System.out.println("유저 없음.");
    		logout( session );
    		return "main";
    	}
    }
	
	@RequestMapping("/ploggingStartA")
	public String ploggingStartA( HttpSession session) {
		Member member = (Member) session.getAttribute("user");
		if( member != null ) {
        	Plogging p = new Plogging();
        	p.setUser(member);
        	p.setCourseName("A코스");
        	prepo.save(p);
    		return "myplogging";
    	} else {
    		System.out.println("유저 없음.");
    		logout( session );
    		return "main";
    	}
	}
	
	@RequestMapping("/ploggingStartB")
	public String ploggingStartB( HttpSession session) {
		Member member = (Member) session.getAttribute("user");
		if( member != null ) {
        	Plogging p = new Plogging();
        	p.setUser(member);
        	p.setCourseName("B코스");
        	prepo.save(p);
    		return "myplogging";
    	} else {
    		System.out.println("유저 없음.");
    		logout( session );
    		return "main";
    	}
	}
	
	@RequestMapping("/ploggingStartC")
	public String ploggingStartC( HttpSession session) {
		Member member = (Member) session.getAttribute("user");
		if( member != null ) {
        	Plogging p = new Plogging();
        	p.setUser(member);
        	p.setCourseName("C코스");
        	prepo.save(p);
    		return "myplogging";
    	} else {
    		System.out.println("유저 없음.");
    		logout( session );
    		return "main";
    	}
	}
	
    
    public String logout( HttpSession session ) {
    	session.invalidate();
    	return "/boot/main";
    }
}
