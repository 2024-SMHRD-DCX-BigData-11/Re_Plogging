package com.smhrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smhrd.entity.Community;
import com.smhrd.repository.CommunityRepository;

import java.util.List;

@Controller
public class CommunityController {
	
    @Autowired
    private CommunityRepository communityRepository;

    @RequestMapping("/community")
    public ModelAndView goCommunity() {
    	ModelAndView mav = new ModelAndView("community");
    	
    	// 데이터 수집 및 기능 실행
        List<Community> list = communityRepository.findAll();
        
        System.out.println("list.size() : " + list.size());

        // 모델에 데이터를 추가하여 JSP로 전달
        mav.addObject("list", list);

        // View 선택
        return mav;
    }
    
    @GetMapping("/communityWriter")
    public String goWriter() {
        return "communityWriter";
    }

    @GetMapping("/communityRead")
    public String goRead() {
        return "communityRead";
    }
}
