package com.smhrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd.repository.MemberRepository;
import com.smhrd.repository.RecycleRepository;

@Controller
public class RecycleController {
	@Autowired
	private RecycleRepository repo;
	
	@RequestMapping("/recycle")
	public String goRecycle() {
		return "recycle";
	}
}
