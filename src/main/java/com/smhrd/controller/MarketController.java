package com.smhrd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd.entity.Market;
import com.smhrd.repository.MarketRepository;
import org.springframework.ui.Model;

@Controller
public class MarketController {
	
	@Autowired
	private MarketRepository repo;
	
	@RequestMapping("/market")
	public String goMarket(){
	   return "market";
	}
	
	@RequestMapping("/showMarket")
    public String showMarket(Model model) {
        List<Market> list =  repo.findAll();
        
        model.addAttribute("list", list);
        
        return "market";
    }

}
