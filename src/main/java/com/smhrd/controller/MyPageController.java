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
import com.smhrd.repository.MileageRepository;
import com.smhrd.repository.PloggingRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyPageController {

	@Autowired
	private MemberRepository repo;

	@Autowired
	private MarketRepository mrepo;

	@Autowired
	private CommunityRepository crepo;
	
	@Autowired
	private MileageRepository mirepo;
	
	@Autowired
	private PloggingRepository prepo;

	@RequestMapping("/mypage")
	public String goMileageList(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("user");

        if (member != null) {
            int totalMileage = mirepo.getMileageCount(member.getUserIdx());
            int completedPloggingCount = prepo.countCompletedPlogging(member.getUserIdx());
            
            model.addAttribute("totalMileage", totalMileage);

			model.addAttribute("completedPloggingCount", completedPloggingCount);
            
            return "mypage";
        } else {
            return "redirect:/main";
        }
    }
	

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

			// 카테고리 변환
			list2.forEach(market -> {
				market.setCategory(categoryToName(market.getCategory()));
			});

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
	

	// 카테고리
	private String categoryToName(String category) {
		switch (category) {
		case "560000":
			return "분류없음";
		case "560001":
			return "캔";
		case "560002":
			return "유리";
		case "560003":
			return "페트";
		case "560004":
			return "플라스틱";
		case "560005":
			return "비닐";
		case "560006":
			return "스티로폼";
		case "560007":
			return "종이";
		default:
			return "알 수 없음";
		}
	}
}