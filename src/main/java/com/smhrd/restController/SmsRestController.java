package com.smhrd.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.entity.CommonDomain;
import com.smhrd.entity.Sms;
import com.smhrd.repository.SmsRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/sms2/")
public class SmsRestController {
	
	@Autowired
	private SmsRepository srepo;
	
	@RequestMapping(value = "/smsCheck", method = RequestMethod.POST)
	public CommonDomain smsChek(
			HttpServletRequest request,
			@RequestParam( value="code" ) int code
			) {
		CommonDomain response = new CommonDomain();
		
		Sms codeCheck = srepo.findByCode( code );
		
		if(codeCheck != null) {
			response.setCode(200);
			
		}
		else {
			response.setCode(-100);
		}
		
		
		return response;
	}
	
}
