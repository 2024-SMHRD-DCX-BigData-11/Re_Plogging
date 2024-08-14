package com.smhrd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smhrd.entity.Market;
import com.smhrd.repository.MarketRepository;
import org.springframework.ui.Model;

@Controller
public class MarketController {
	
	@Autowired
	private MarketRepository repo;
	
	@RequestMapping("/market")
	public ModelAndView goMarket(){
		List<Market> list = repo.findAll();
        
        ModelAndView mav = new ModelAndView("market");
        
        mav.addObject("list", list);
        
        return mav;
	}
	
	@RequestMapping("/marketWrite")
    public String goWrite() {
        return "marketWrite";
    }

    @RequestMapping("/marketRead")
    public String goRead() {
        return "marketRead";
    }
	

}
