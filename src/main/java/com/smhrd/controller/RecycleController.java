package com.smhrd.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.entity.Recycle;
import com.smhrd.repository.RecycleRepository;

@Controller
public class RecycleController {

	@Autowired
	private RecycleRepository repo;

	@GetMapping("/recycle")
	public String goRecycle(Model model, @RequestParam(defaultValue = "1") int page) {
		int pageSize = 1; // 한 번에 한 개씩만 보여주기
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		Page<Recycle> recyclePage = repo.findAll(pageable);

		// 현재 페이지의 항목을 가져옴
		Recycle recycle = recyclePage.getContent().isEmpty() ? null : recyclePage.getContent().get(0);

		if (recycle != null && recycle.getRecycleVideo() != null) {
			String base64Image = Base64.getEncoder().encodeToString(recycle.getRecycleVideo());
			model.addAttribute("recycleImage", base64Image);
		}

		model.addAttribute("recycle", recycle);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", recyclePage.getTotalPages());

		return "recycle";
	}

	@GetMapping("/recycle/data")
	@ResponseBody
	public List<Map<String, Object>> getRecycleData(
			@RequestParam(name = "main-category", defaultValue = "") String mainCategory) {

		System.out.println("mainCategory : " + mainCategory);

		List<Recycle> recycle;

		if (mainCategory.length() > 0) {
			recycle = repo.findRecycleByTarget(mainCategory);
		} else {
			recycle = repo.findAll();
		}
		List<Map<String, Object>> response = new ArrayList<>();

		if (recycle != null) {
			for (Recycle r : recycle) {
				Map<String, Object> obj = new HashMap<>();
				if (r.getRecycleVideo() != null) {
					String base64Image = Base64.getEncoder().encodeToString(r.getRecycleVideo());
					obj.put("recycleImage", base64Image);
				} else {
					obj.put("recycleImage", null);
				}
				obj.put("recycleTarget", r.getRecycleTarget());
				obj.put("recycleStatus", r.getRecycleStatus());
				obj.put("recycleCategory", r.getRecycleCategory());
				obj.put("recycleMethod", r.getRecycleMethod());

				response.add(obj);
			}
		}

		return response;
	}

}