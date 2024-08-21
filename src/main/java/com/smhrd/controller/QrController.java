package com.smhrd.controller;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd.entity.Member;
import com.smhrd.entity.Plogging;
import com.smhrd.repository.PloggingRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class QrController {
	
	@Autowired
	private PloggingRepository prepo;
	
	// qr인증 성공 페이지
	@RequestMapping("/qrSuccess")
	public String goqrSuccess() {
		return "qrSuccess";
	}
	
	
	// qrTest 페이지
	@RequestMapping("/qrTest")
	public String goqrTest() {
		return "qrTest";
	}
	
	
	@Transactional
	@RequestMapping("/qr1Update")
	public String qr1Update(HttpSession session, HttpServletRequest request) {
		Member member = (Member) session.getAttribute("user");
		if (member != null) {

			String courseName = request.getParameter("courseName").toString();

			System.out.println("courseName: " + courseName);
			
			int userIdx = member.getUserIdx();
			
			System.out.println("userIdx: " + userIdx);
			
			prepo.UpdateQr1(userIdx, courseName);
			

			return "redirect:/myplogging"; // 리다이렉트 사용
		} else {
			System.out.println("유저 없음");
			logout(session);
			return "redirect:/main"; // 리다이렉트 사용
		}
	}

	@Transactional
	@RequestMapping("/qr2Update")
	public String qr2Update(HttpSession session, HttpServletRequest request) {
		Member member = (Member) session.getAttribute("user");
		if (member != null) {
			
			String courseName = request.getParameter("courseName").toString();

			System.out.println("값이 잘 들어갔는지 확인: " + courseName);
			
			int userIdx = member.getUserIdx();
			
			System.out.println("userIdx: " + userIdx);
			
			Plogging plogging = prepo.checkQr1(userIdx, courseName);
			
			if(plogging != null) {
				
				prepo.UpdateQr2(userIdx, courseName);
				
				return "redirect:/myplogging"; // 리다이렉트 사용
			}else {
				System.out.println("qr1번 안찍음");
				return "redirect:/myplogging"; // 리다이렉트 사용
			}
			
		} else {
			System.out.println("유저 없음");
			logout(session);
			return "redirect:/main"; // 리다이렉트 사용
		}
	}
	
	
	@Transactional
	@RequestMapping("/qr3Update")
	public String qr3Update(HttpSession session, HttpServletRequest request) {
		Member member = (Member) session.getAttribute("user");
		if (member != null) {

			String courseName = request.getParameter("courseName").toString();

			System.out.println("값이 잘 들어갔는지 확인: " + courseName);
			
			int userIdx = member.getUserIdx();
			
			System.out.println("userIdx: " + userIdx);
			
			Plogging plogging = prepo.checkQr2(userIdx, courseName);
			
			if(plogging != null) {
				prepo.UpdateQr3(member.getUserIdx(), courseName);
				return "redirect:/myplogging"; // 리다이렉트 사용
			}else {
				
				System.out.println("qr1번 ,2번 안찍음");
				return "redirect:/myplogging"; // 리다이렉트 사용
			}
			
		} else {
			System.out.println("유저 없음");
			logout(session);
			return "redirect:/main"; // 리다이렉트 사용
		}
	}
	
	
	/* 로그아웃 */
	public String logout(HttpSession session) {
		session.invalidate();
		return "/boot/main";
	}

}
