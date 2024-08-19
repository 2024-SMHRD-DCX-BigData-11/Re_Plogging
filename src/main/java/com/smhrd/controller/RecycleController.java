package com.smhrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;

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
}