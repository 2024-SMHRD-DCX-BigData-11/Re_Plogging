package com.smhrd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.smhrd.entity.Member;
import com.smhrd.entity.Mileage;
import com.smhrd.repository.MemberRepository;
import com.smhrd.repository.MileageRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MileageController {

    @Autowired
    private MileageRepository miRepo;

    @RequestMapping("/myMileage")
    public String goMileageList(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("user");

        if (member != null) {
            // 보유 마일리지 가져오기
            int totalMileage = miRepo.getMileageCount(member.getUserIdx());
            
            // 마일리지 리스트 가져오기
            List<Mileage> list = miRepo.findByUser(member);
            
            // 모델에 리스트와 보유 마일리지 추가
            model.addAttribute("list", list);
            model.addAttribute("totalMileage", totalMileage);
            
            return "myMileage";
        } else {
            return "main";
        } 
    }
}

