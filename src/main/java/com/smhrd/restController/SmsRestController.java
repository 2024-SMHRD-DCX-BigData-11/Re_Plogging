package com.smhrd.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.entity.CommonDomain;
import com.smhrd.entity.Sms;
import com.smhrd.repository.SmsRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping("/rest/sms2/")
public class SmsRestController {
	
//	@Autowired
//	private SmsRepository srepo;
//	
//	@RequestMapping(value = "/smsCheck", method = RequestMethod.POST)
//	public CommonDomain smsChek(HttpServletRequest request) {
//		CommonDomain response = new CommonDomain();
//		
//		int u_input = Integer.parseInt(request.getParameter("u_input"));
//		Sms codeCheck = srepo.findByCode(u_input);
//		
//		if(codeCheck != null) {
//			response.setResult("Success!!");
//			
//		}
//		else {
//			response.setResult("Fail...");
//		}
//		
//		
//		return response;
//	}
	
}
