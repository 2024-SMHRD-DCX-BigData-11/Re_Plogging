package com.smhrd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SmsController {
	
	@RequestMapping("/smsForm")
	public String goSmsForm() {
		return "smsForm";
	}
	
	@RequestMapping("/shipping1")
	public String goShipping1() {
		return "shipping1";
	}
	
	@RequestMapping("/shipping2")
	public String goShipping2() {
		return "shipping2";
	}

}
