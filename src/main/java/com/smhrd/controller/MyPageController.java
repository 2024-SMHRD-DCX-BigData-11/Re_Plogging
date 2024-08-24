package com.smhrd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.entity.Community;
import com.smhrd.entity.Market;
import com.smhrd.entity.Member;
import com.smhrd.repository.CommunityRepository;
import com.smhrd.repository.MarketRepository;
import com.smhrd.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyPageController {

	@Autowired
	private MemberRepository repo;

	@Autowired
	private MarketRepository mrepo;

	@Autowired
	private CommunityRepository crepo;

	@RequestMapping("/myWrite")
	public String MyCommunityList(HttpSession session, Model model) {

		// 로그인 확인
		Member member = (Member) session.getAttribute("user");

		if (member != null) {

			// 1. 데이터 수집
			// 2. 기능 실행
			List<Community> list1 = crepo.findByMyCommunity(member); // 작성일자 기준으로 내림차순
			List<Market> list2 = mrepo.findByMyMarket(member); // 게시글 작성일 기준으로 내림차순

			model.addAttribute("MyClist", list1);
			model.addAttribute("MyMlist", list2);

			return "myWrite";

		} else {
			// 3. View 선택 로그인 상태가 이니면 메인 페이지로 이동
			return "redirect:/main";
		}

	}

	// 내 게시글 삭제
	@RequestMapping("/Mycdelete")
	public String Mycdelete(@RequestParam("idx") int idx) {
		crepo.deleteById(idx);
		return "redirect:/myWrite";
	}
	
	// 내 마켓글 삭제
	@RequestMapping("/Mymdelete")
	public String Mymdelete(@RequestParam("idx") int idx) {
		mrepo.deleteById(idx);
		return "redirect:/myWrite";
	}

}