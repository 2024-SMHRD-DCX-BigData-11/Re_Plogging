package com.smhrd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PloggingController {
	
	@RequestMapping("/qrSuccess")
	public String goqrSuccess() {
		return "qrSuccess";
	}

}
