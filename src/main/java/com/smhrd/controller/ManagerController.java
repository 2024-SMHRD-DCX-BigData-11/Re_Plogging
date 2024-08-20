package com.smhrd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd.entity.Community;
import com.smhrd.entity.Market;
import com.smhrd.entity.Member;
import com.smhrd.repository.CommunityRepository;
import com.smhrd.repository.MarketRepository;
import com.smhrd.repository.MemberRepository;

@Controller
public class ManagerController {
	
	@Autowired
	MemberRepository repo;
	
	@Autowired
	CommunityRepository crepo;
	
	@Autowired
	MarketRepository mrepo;

	
	@RequestMapping("/ManagerList")
	public String ReploggingList(Model model) {
		
		// 1. 데이터 수집
		// 2. 기능 실행
		List<Member> list1 = repo.findAll(Sort.by(Sort.Direction.DESC, "joinedAt")); // 가입일자 기준으로 내림차순
		
		List<Community> list2 = crepo.findAll(Sort.by(Sort.Direction.DESC, "indate")); // 작성일 기준으로 내림차순
		
		List<Market> list3 = mrepo.findAll(Sort.by(Sort.Direction.DESC, "createdAt")); // 게시날짜 기준으로 내림차순
				
		model.addAttribute("ulist", list1);
		
		model.addAttribute("clist", list2);
		
		model.addAttribute("mlist", list3);
		
		// 3. View 선택
		return "manager";
		
}
	// 회원 삭제
	@RequestMapping("/udelete")
	public String udelete( int idx ) {
		// 1. 데이터 수집
		// 2. 기능 실행
		repo.deleteById(idx);
		// 3. View 선택
		return "redirect:/ManagerList";
	}
	
	// 게시글 삭제
	@RequestMapping("/cdelete")
	public String cdelete( int idx ) {
		// 1. 데이터 수집
		// 2. 기능 실행
		crepo.deleteById(idx);
		// 3. View 선택
		return "redirect:/ManagerList";
	}
	
	// 마켓 게시글 삭제
	@RequestMapping("/mdelete")
	public String mdelete( int idx ) {
		// 1. 데이터 수집
		// 2. 기능 실행
		mrepo.deleteById(idx);
		// 3. View 선택
		return "redirect:/ManagerList";
	}
	
	
	@RequestMapping("/cview")
	public String cview( int idx, Model model ) {
		// 1. 데이터 수집
		// 2. 기능실행
		Optional<Community> community = crepo.findById(idx);
		
		if( community.isPresent() ) {
			model.addAttribute("community", community.get());
			// 3. View 선택
			return "/communityRead";
		}else {
			return "redirect:/ManagerList";
		}
		
	}
	
	@RequestMapping("/mview")
	public String mview( int idx, Model model ) {
		// 1. 데이터 수집
		// 2. 기능실행
		Optional<Market> market = mrepo.findById(idx);
		
		if( market.isPresent() ) {
			model.addAttribute("market", market.get());
			// 3. View 선택
			return "/marketReaad";
		}else {
			return "redirect:/ManagerList";
		}
		
	}

}
