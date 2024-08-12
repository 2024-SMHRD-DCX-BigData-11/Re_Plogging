package com.smhrd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class CommunityController {
	
	@RequestMapping("/community")
	public String goCommnunity(){
	   return "community";
	}
	
	@RequestMapping("/communityWriter")
	public String goWriter(){
	   return "communityWriter";
	}


}
