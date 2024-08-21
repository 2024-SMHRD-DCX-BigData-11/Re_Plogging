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
    private MileageRepository mileageRepo;

    @RequestMapping("/myMileage")
    public String goMileageList(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("user");

        if (member != null) {
            List<Mileage> list = mileageRepo.findByUser(member);
            model.addAttribute("list", list);
            
            
            return "myMileage";
        } else {
            return "main";
        }
    }
}

