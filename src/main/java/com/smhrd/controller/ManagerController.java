package com.smhrd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.entity.Community;
import com.smhrd.entity.Market;
import com.smhrd.entity.Member;
import com.smhrd.entity.MemberDTO;
import com.smhrd.repository.CommunityRepository;
import com.smhrd.repository.MarketRepository;
import com.smhrd.repository.MemberRepository;
import com.smhrd.repository.MileageRepository;
import com.smhrd.repository.PloggingRepository;

@Controller
public class ManagerController {

    @Autowired
    MemberRepository repo;

    @Autowired
    CommunityRepository crepo;

    @Autowired
    MarketRepository mrepo;
    
    @Autowired
    MileageRepository mirepo;
	
	@Autowired
	PloggingRepository prepo;

	@RequestMapping("/manager")
	public String ReploggingList(Model model) {

	    List<Member> members = repo.findByUserIdxNot(2530000, Sort.by(Sort.Direction.DESC, "joinedAt"));
	    List<MemberDTO> memberDTOs = new ArrayList<>();

	    // 각 회원의 마일리지와 플로깅 횟수를 DTO에 저장
	    for (Member member : members) {
	        int totalMileage = mirepo.getMileageCount(member.getUserIdx());
	        int completedPloggingCount = prepo.countCompletedPlogging(member.getUserIdx());

	        // MemberDTO 생성자 호출 시 joinedAt 필드 추가
	        MemberDTO memberDTO = new MemberDTO(
	            member.getUserIdx(),
	            member.getUserId(),
	            member.getUserPhone(),
	            member.getUserNick(),
	            totalMileage,
	            completedPloggingCount,
	            member.getJoinedAt()  // joinedAt 필드 추가
	        );
	        memberDTOs.add(memberDTO);
	    }

	    model.addAttribute("ulist", memberDTOs);

	    List<Community> list2 = crepo.findAll(Sort.by(Sort.Direction.DESC, "indate")); 
	    List<Market> list3 = mrepo.findAll(Sort.by(Sort.Direction.DESC, "createdAt")); 

	    model.addAttribute("clist", list2);
	    model.addAttribute("mlist", list3);

	    list3.forEach(market -> {
	        market.setCategory(categoryToName(market.getCategory()));
	    });

	    return "manager";
	}




    // 회원 삭제
    @RequestMapping("/udelete")
    public String udelete(@RequestParam("idx") int idx) {
        repo.deleteById(idx);
        return "redirect:/manager";
    }

    // 게시글 삭제
    @RequestMapping("/cdelete")
    public String cdelete(@RequestParam("idx") int idx) {
        try {
            crepo.deleteById(idx);
        } catch (Exception e) {
            e.printStackTrace(); // 예외가 발생하면 콘솔에 출력
        }
        return "redirect:/manager";
    }

    // 마켓 게시글 삭제
    @RequestMapping("/mdelete")
    public String mdelete(@RequestParam("idx") int idx) {
        mrepo.deleteById(idx);
        return "redirect:/manager";
    }

    @RequestMapping("/cview")
    public String cview(@RequestParam("idx") int idx, Model model) {
        Optional<Community> community = crepo.findById(idx);

        if (community.isPresent()) {
            model.addAttribute("community", community.get());
            return "/communityRead";
        } else {
            return "redirect:/manager";
        }
    }

    @RequestMapping("/mview")
    public String mview(@RequestParam("idx") int idx, Model model) {
        Optional<Market> market = mrepo.findById(idx);

        if (market.isPresent()) {
            model.addAttribute("market", market.get());
            return "/marketRead";
        } else {
            return "redirect:/manager";
        }
    }

    // 카테고리 변환 메서드
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
