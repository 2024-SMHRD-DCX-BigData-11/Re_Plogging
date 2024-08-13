package com.smhrd.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.repository.MarketRepository;

@RestController
public class MarketRestController {
	
	@Autowired
	private MarketRepository repo;

}
