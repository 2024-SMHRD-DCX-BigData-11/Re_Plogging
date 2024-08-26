package com.smhrd.controller;

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

    @RequestMapping("/managerList")
    public String ReploggingList(Model model) {

        // 1. 데이터 수집
        List<Member> list1 = repo.findAll(Sort.by(Sort.Direction.DESC, "joinedAt")); // 가입일자 기준으로 내림차순
        List<Community> list2 = crepo.findAll(Sort.by(Sort.Direction.DESC, "indate")); // 작성일 기준으로 내림차순
        List<Market> list3 = mrepo.findAll(Sort.by(Sort.Direction.DESC, "createdAt")); // 게시날짜 기준으로 내림차순

        model.addAttribute("ulist", list1);
        model.addAttribute("clist", list2);
        model.addAttribute("mlist", list3);

        // 카테고리 값을 변환하여 추가
        list3.forEach(market -> {
            market.setCategory(categoryToName(market.getCategory()));
        });

        // 3. View 선택
        return "manager";
    }

    // 회원 삭제
    @RequestMapping("/udelete")
    public String udelete(@RequestParam("idx") int idx) {
        repo.deleteById(idx);
        return "redirect:/managerList";
    }

    // 게시글 삭제
    @RequestMapping("/cdelete")
    public String cdelete(@RequestParam("idx") int idx) {
        try {
            crepo.deleteById(idx);
        } catch (Exception e) {
            e.printStackTrace(); // 예외가 발생하면 콘솔에 출력
        }
        return "redirect:/managerList";
    }

    // 마켓 게시글 삭제
    @RequestMapping("/mdelete")
    public String mdelete(@RequestParam("idx") int idx) {
        mrepo.deleteById(idx);
        return "redirect:/managerList";
    }

    @RequestMapping("/cview")
    public String cview(@RequestParam("idx") int idx, Model model) {
        Optional<Community> community = crepo.findById(idx);

        if (community.isPresent()) {
            model.addAttribute("community", community.get());
            return "/communityRead";
        } else {
            return "redirect:/managerList";
        }
    }

    @RequestMapping("/mview")
    public String mview(@RequestParam("idx") int idx, Model model) {
        Optional<Market> market = mrepo.findById(idx);

        if (market.isPresent()) {
            model.addAttribute("market", market.get());
            return "/marketRead";
        } else {
            return "redirect:/managerList";
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
