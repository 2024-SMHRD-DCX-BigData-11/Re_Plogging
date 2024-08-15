package com.smhrd.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.smhrd.entity.Community;
import com.smhrd.entity.Market;
import com.smhrd.entity.Member;
import com.smhrd.repository.MarketRepository;
import com.smhrd.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class MarketController {

   @Autowired
   private MarketRepository repo;

   @Autowired
   private MemberRepository memberRepo;

   @RequestMapping("/market")
   public ModelAndView goMarket() {
      List<Market> list = repo.findAll();

      ModelAndView mav = new ModelAndView("market");

      mav.addObject("list", list);

      return mav;
   }

   @RequestMapping("/marketList")
   public String getMarketList(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
         @RequestParam(value = "size", defaultValue = "8") int size,
         @RequestParam(value = "sort", defaultValue = "latest") String sort) {

// 현재 sort 값 출력
      System.out.println("Current sort: " + sort);

// 정렬 기준에 따른 정렬 방식 선택
      Sort sorting = Sort.by(Sort.Direction.DESC, "createdAt");

      if (sort.equals("lowPrice")) {
         sorting = Sort.by(Sort.Direction.ASC, "mileage");
      } else if (sort.equals("highPrice")) {
         sorting = Sort.by(Sort.Direction.DESC, "mileage");
      }

      Page<Market> marketPage = repo.findAll(PageRequest.of(page - 1, size, sorting));
      int totalPages = marketPage.getTotalPages();

      model.addAttribute("currentPage", page);
      model.addAttribute("totalPages", totalPages);
      model.addAttribute("list", marketPage.getContent());

      model.addAttribute("currentSort", sort);
      return "marketList";
   }

   @RequestMapping("/marketWrite")
   public String goWrite() {
      return "marketWrite";
   }

   @PostMapping("/marketPost")
   public ModelAndView marketPost(@RequestParam("category") String category, @RequestParam("title") String title,
         @RequestParam("mileage") Integer mileage, @RequestParam("content") String content,
         @RequestParam("img1") MultipartFile img1, @RequestParam("img2") MultipartFile img2,
         @RequestParam("img3") MultipartFile img3, @RequestParam("user") int userId,
         @RequestParam("status") String status) {

      Member writer = memberRepo.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));

      String fileName1 = saveFile(img1);
      String fileName2 = saveFile(img2);
      String fileName3 = saveFile(img3);

      Market newPost = new Market();
      newPost.setCategory(category);
      newPost.setTitle(title);
      newPost.setMileage(mileage);
      newPost.setContent(content);
      newPost.setImg1(fileName1);
      newPost.setImg2(fileName2);
      newPost.setImg3(fileName3);
      newPost.setUser(writer);
      newPost.setCreatedAt(new Date());
      newPost.setClosedAt(new Date());
      newPost.setStatus("판매중");

      // 저장
      repo.save(newPost);

      ModelAndView mav = new ModelAndView("redirect:/market");
      return mav;
   }

   private String saveFile(MultipartFile file) {
      if (file != null && !file.isEmpty()) {
         String fileName = file.getOriginalFilename();
         String uploadDir = "C:/uploads/";
         File dir = new File(uploadDir);

         if (!dir.exists()) {
            dir.mkdirs();
         }

         try {
            String filePath = uploadDir + fileName;
            file.transferTo(new File(filePath));
            return fileName;
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      return null;
   }

   @RequestMapping("/marketRead")
   public String goRead() {
      return "marketRead";
   }

   @GetMapping("/marketBack")
   public String goBack() {
      return "redirect:/market";
   }

}
