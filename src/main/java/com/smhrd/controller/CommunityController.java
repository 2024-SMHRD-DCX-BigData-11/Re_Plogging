package com.smhrd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommunityController {
	
	@RequestMapping("/commnunity")
	public String goCommnunity(){
	   return "commnunity";
	}

}
