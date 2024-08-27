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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smhrd.entity.Recycle;
import com.smhrd.repository.RecycleRepository;

@Controller
public class RecycleController {

    @Autowired
    private RecycleRepository repo;

    @GetMapping("/recycle")
    public String goRecycle(Model model, 
                            @RequestParam(name = "page", defaultValue = "1") int page,
                            @RequestParam(name = "main-category", required = false) String mainCategory,
                            RedirectAttributes redirectAttributes) {
        
        try {
            int pageSize = 1; // 한 번에 한 개씩만 보여주기
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            Page<Recycle> recyclePage;

            if (mainCategory == null || mainCategory.isEmpty()) {
                // 전체보기일 때
                recyclePage = repo.findAll(pageable);
            } else {
                // 특정 카테고리일 때
                recyclePage = repo.findRecycleByTarget(mainCategory, pageable);
            }

            Recycle recycle = recyclePage.getContent().isEmpty() ? null : recyclePage.getContent().get(0);

            if (recycle != null && recycle.getRecycleVideo() != null) {
                String base64Image = Base64.getEncoder().encodeToString(recycle.getRecycleVideo());
                model.addAttribute("recycleImage", base64Image);
            }

            model.addAttribute("recycle", recycle);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", recyclePage.getTotalPages());
            model.addAttribute("selectedCategory", mainCategory);

            return "recycle";
        } catch (Exception e) {
            // 예외 발생 시 리다이렉트 처리
            redirectAttributes.addFlashAttribute("errorMessage", "잘못된 요청입니다.");
            return "redirect:/error";
        }
    }

    @GetMapping("/recycle/data")
    @ResponseBody
    public List<Map<String, Object>> getRecycleData(
            @RequestParam(name = "main-category", defaultValue = "") String mainCategory) {

        List<Recycle> recycle;

        if (mainCategory.isEmpty()) {
            recycle = repo.findAll();  // 전체 데이터를 가져옴
        } else {
            Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE); // 모든 결과를 가져오도록 설정
            Page<Recycle> recyclePage = repo.findRecycleByTarget(mainCategory, pageable);
            recycle = recyclePage.getContent();
        }

        List<Map<String, Object>> response = new ArrayList<>();

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

        return response;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception ex) {
        model.addAttribute("errorMessage", "예기치 않은 오류가 발생했습니다.");
        return "error"; // error 페이지로 포워딩
    }
}
