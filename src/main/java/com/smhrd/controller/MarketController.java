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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.smhrd.entity.Community;
import com.smhrd.entity.Market;
import com.smhrd.entity.Member;
import com.smhrd.entity.Mileage;
import com.smhrd.entity.Purchase;
import com.smhrd.repository.MarketRepository;
import com.smhrd.repository.MemberRepository;
import com.smhrd.repository.MileageRepository;
import com.smhrd.repository.PurchaseRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MarketController {

    @Autowired
    private MarketRepository marketRepo;

    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    private PurchaseRepository purchaseRepo;
    
    @Autowired
    private MileageRepository mileageRepo;

    @RequestMapping("/market")
    public ModelAndView goMarket(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "8") int size,
            @RequestParam(value = "sort", defaultValue = "latest") String sort,
            @RequestParam(value = "category", required = false) String category) {

        ModelAndView mav = new ModelAndView("market");
        Page<Market> marketPage;

        if (category != null && !category.isEmpty()) {
            marketPage = getPagedMarketsByCategory(category, page, size, sort);
        } else {
            marketPage = getPagedMarkets(page, size, sort);
        }

        marketPage.getContent().forEach(market -> {
            market.setCategory(categoryToName(market.getCategory()));
        });

        int totalPages = marketPage.getTotalPages();

        mav.addObject("currentPage", page);
        mav.addObject("totalPages", totalPages);
        mav.addObject("list", marketPage.getContent());
        mav.addObject("currentSort", sort);
        mav.addObject("selectedCategory", category);

        return mav;
    }

    private Page<Market> getPagedMarkets(int page, int size, String sort) {
        if (page < 1) page = 1;
        if (size < 1) size = 8;

        Sort sorting = getSorting(sort);

        return marketRepo.findAll(PageRequest.of(page - 1, size, sorting));
    }

    private Page<Market> getPagedMarketsByCategory(String category, int page, int size, String sort) {
        if (page < 1) page = 1;
        if (size < 1) size = 8;

        Sort sorting = getSorting(sort);

        return marketRepo.findByCategory(category, PageRequest.of(page - 1, size, sorting));
    }

    private Sort getSorting(String sort) {
        Sort sorting = Sort.by(Sort.Direction.DESC, "createdAt");

        if (sort.equals("lowPrice")) {
            sorting = Sort.by(Sort.Direction.ASC, "mileage");
        } else if (sort.equals("highPrice")) {
            sorting = Sort.by(Sort.Direction.DESC, "mileage");
        }

        return sorting;
    }

    @RequestMapping("/marketWrite")
    public String goWrite() {
        return "marketWrite";
    }

    @PostMapping("/marketPost")
    public ModelAndView marketPost(
            @RequestParam("category") String category,
            @RequestParam("title") String title,
            @RequestParam("mileage") Integer mileage,
            @RequestParam("content") String content,
            @RequestParam("img1") MultipartFile img1,
            @RequestParam("img2") MultipartFile img2,
            @RequestParam("img3") MultipartFile img3,
            @RequestParam("user") int userId,
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
        newPost.setStatus("판매중");

        marketRepo.save(newPost);

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

    @GetMapping("/marketRead")
    public String goRead(@RequestParam("idx") int idx, Model model, HttpSession session) {
        Market market = marketRepo.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("Invalid market Id:" + idx));

        market.setCategory(categoryToName(market.getCategory()));

        model.addAttribute("market", market);
        model.addAttribute("user", session.getAttribute("user"));
        return "marketRead";
    }

    @GetMapping("/marketBack")
    public String goBack() {
        return "redirect:/market";
    }

    @PostMapping("/marketPurchase")
    public String purchaseItem(@RequestParam("mkIdx") int mkIdx, HttpSession session, Model model) {
        Member currentUser = (Member) session.getAttribute("user");

        Market market = marketRepo.findById(mkIdx)
                .orElseThrow(() -> new IllegalArgumentException("Invalid market Id:" + mkIdx));

        if (currentUser.getMileageAmount() < market.getMileage()) {
            model.addAttribute("error", "Insufficient mileage for this purchase.");
            return "redirect:/marketRead?idx=" + mkIdx;
        }

        int mileageAmount = market.getMileage();

        currentUser.setMileageAmount(currentUser.getMileageAmount() - mileageAmount);
        memberRepo.save(currentUser);

        Member postUser = market.getUser();
        postUser.setMileageAmount(postUser.getMileageAmount() + mileageAmount);
        memberRepo.save(postUser);

        market.setClosedAt(new Date());
        market.setStatus("판매완료");
        marketRepo.save(market);

        // 마일리지 내역 기록
        Mileage mileagePu = new Mileage();
        mileagePu.setMlType("마켓판매");
        mileagePu.setMlAmount(mileageAmount);
        mileagePu.setUser(postUser);
        mileageRepo.save(mileagePu);

        Mileage mileageCu = new Mileage();
        mileageCu.setMlType("마켓구매");
        mileageCu.setMlAmount(mileageAmount);
        mileageCu.setUser(currentUser);
        mileageRepo.save(mileageCu);

        // 구매 내역 생성 및 저장
        Purchase purchase = new Purchase();
        purchase.setMkIdx(market);
        purchase.setUserIdx(currentUser);
        purchaseRepo.save(purchase);

        return "redirect:/market";
    }


    @PostMapping("/market/delete")
    public String deleteMarket(@RequestParam("mkIdx") int mkIdx, HttpSession session) {
        Member currentUser = (Member) session.getAttribute("user");

        if (currentUser == null) {
            return "redirect:/login"; // 로그인되지 않은 경우 로그인 페이지로 리디렉션
        }

        Market market = marketRepo.findById(mkIdx)
                .orElseThrow(() -> new IllegalArgumentException("Invalid market Id:" + mkIdx));

        // 현재 사용자가 해당 게시물의 작성자인지 확인
        if (market.getUser().getUserIdx() != currentUser.getUserIdx()) {
            return "redirect:/market"; // 작성자가 아니면 마켓 목록으로 리디렉션
        }

        marketRepo.delete(market); // 게시물 삭제

        return "redirect:/market"; // 삭제 후 마켓 목록으로 리디렉션
    }

    private String categoryToName(String category) {
        switch (category) {
            case "560000":
                return "분류없음";
            case "560001":
                return "캔";
            case "560002":
                return "유리";
            case "560003":
                return "페트";
            case "560004":
                return "플라스틱";
            case "560005":
                return "비닐";
            case "560006":
                return "스티로폼";
            default:
                return "알 수 없음";
        }
    }
    
    // 내 마켓글 조회
    @RequestMapping("/MyMarketList")
   	public String MyMarketList(HttpSession session, Model model) {
       	
       	// 로그인 확인
       	 Member member = (Member) session.getAttribute("user");
       	 
       	 if(member != null) {
       		 
   			// 1. 데이터 수집
   			// 2. 기능 실행
   			List<Market> list = marketRepo.findByMyMarket(member);// 작성일자 기준으로 내림차순
   					
   			model.addAttribute("MyMlist", list);
   			
   			return "myCommunity";
   			
   			
       	 }else {
       		// 3. View 선택 로그인 상태가 이니면 메인 페이지로 이동
       			return "redirect:/main";
       	 }
       	 
   	}
       
       
       // 내 마켓글 삭제
    	@RequestMapping("/Mymdelete")
    	public String Mymdelete( int idx ) {
    		// 1. 데이터 수집
    		// 2. 기능 실행
    		marketRepo.deleteById(idx);
    		// 3. View 선택
    		return "redirect:/MyCommunityList";
    	}
   }

