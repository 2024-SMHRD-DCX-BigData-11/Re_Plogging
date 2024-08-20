package com.smhrd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.smhrd.entity.TopPloggerDTO;
import com.smhrd.repository.PloggingListRepository;

@Controller
public class PloggingListController {

	@Autowired
	private PloggingListRepository ploggingListRepository;

	@GetMapping("/main")
	public String showMainPage(Model model) {
		List<TopPloggerDTO> topPloggerList = ploggingListRepository.findTopPloggerWithJPQL(PageRequest.of(0, 3));

        if (topPloggerList.size() > 0) {
            model.addAttribute("topUser", topPloggerList);
//            TopPloggerDTO topPlogger = topPloggerList.get(0);
//            model.addAttribute("topUserNick", topPlogger.getUserNick());
//            model.addAttribute("topPloggingCount", topPlogger.getPloggingCount());
        } else {
            model.addAttribute("topUserNick", "Unknown");
            model.addAttribute("topPloggingCount", "0");
        }

		return "main"; // main.jsp로 리턴
	}
}
