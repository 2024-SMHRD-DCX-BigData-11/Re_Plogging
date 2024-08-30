package com.smhrd.controller;

import net.coobird.thumbnailator.Thumbnails;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView goMarket(@RequestParam(value = "page", defaultValue = "1") int page,
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
		if (page < 1)
			page = 1;
		if (size < 1)
			size = 8;

		Sort sorting = getSorting(sort);

		return marketRepo.findAll(PageRequest.of(page - 1, size, sorting));
	}

	private Page<Market> getPagedMarketsByCategory(String category, int page, int size, String sort) {
		if (page < 1)
			page = 1;
		if (size < 1)
			size = 8;

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
	
	@GetMapping("/marketBack")
    public String goBack() {
        return "redirect:/market";
    }

	@PostMapping("/marketPost")
	public ModelAndView marketPost(@RequestParam("category") String category, @RequestParam("title") String title,
			@RequestParam("mileage") Integer mileage, @RequestParam("content") String content,
			@RequestParam("img1") MultipartFile img1, @RequestParam("img2") MultipartFile img2,
			@RequestParam("img3") MultipartFile img3, @RequestParam("user") int userId,
			@RequestParam("status") String status) {

		Member writer = memberRepo.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));

		Market newPost = new Market();
		newPost.setCategory(category);
		newPost.setTitle(title);
		newPost.setMileage(mileage);
		newPost.setContent(content);

		try {
			newPost.setImg1(resizeImage(img1));
			if (img2 != null && !img2.isEmpty()) {
				newPost.setImg2(resizeImage(img2));
			}
			if (img3 != null && !img3.isEmpty()) {
				newPost.setImg3(resizeImage(img3));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return new ModelAndView("redirect:/marketWrite?uploadError=true");
		}

		newPost.setUser(writer);
		newPost.setStatus("판매중");

		marketRepo.save(newPost);

		return new ModelAndView("redirect:/market");
	}

	private byte[] resizeImage(MultipartFile file) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		Thumbnails.of(file.getInputStream()).size(800, 800).outputFormat("jpg").toOutputStream(baos);

		return baos.toByteArray();
	}

	@GetMapping("/marketRead")
	public String goRead(@RequestParam("idx") int idx, Model model, HttpSession session) {
		Market market = marketRepo.findById(idx)
				.orElseThrow(() -> new IllegalArgumentException("Invalid market Id:" + idx));

		market.setCategory(categoryToName(market.getCategory()));

		Member currentUser = (Member) session.getAttribute("user");
		if (currentUser != null) {
			int totalMileage = marketRepo.getMileageCount(currentUser.getUserIdx());
			model.addAttribute("totalMileage", totalMileage);
		}

		model.addAttribute("market", market);
		model.addAttribute("user", session.getAttribute("user"));
		return "marketRead";
	}

	@PostMapping("/marketPurchase")
	public String purchaseItem(@RequestParam("mkIdx") int mkIdx, HttpSession session, Model model) {
		Member currentUser = (Member) session.getAttribute("user");
		int totalMileage = marketRepo.getMileageCount(currentUser.getUserIdx());

		Market market = marketRepo.findById(mkIdx)
				.orElseThrow(() -> new IllegalArgumentException("Invalid market Id:" + mkIdx));

		if (totalMileage < market.getMileage()) {
			model.addAttribute("error", "Insufficient mileage for this purchase.");
			return "redirect:/marketRead?idx=" + mkIdx;
		}

		int mileageAmount = market.getMileage();
		Member postUser = market.getUser();

		market.setClosedAt(new Date());
		market.setStatus("판매완료");
		marketRepo.save(market);

		// 마일리지 내역 기록
		Mileage mileagePu = new Mileage();
		mileagePu.setMlType("공예품 판매");
		mileagePu.setMlAmount(mileageAmount);
		mileagePu.setUser(postUser);
		mileagePu.setMlLog("적립");
		mileageRepo.save(mileagePu);

		Mileage mileageCu = new Mileage();
		mileageCu.setMlType("공예품 구매");
		mileageCu.setMlAmount(mileageAmount);
		mileageCu.setUser(currentUser);
		mileageCu.setMlLog("사용");
		mileageRepo.save(mileageCu);

		Purchase purchase = new Purchase();
		purchase.setMkIdx(market);
		purchase.setUserIdx(currentUser);
		purchaseRepo.save(purchase);

		return "redirect:/market?purchaseSuccess=true";
	}

	@PostMapping("/market/delete")
	public String deleteMarket(@RequestParam("mkIdx") int mkIdx, HttpSession session) {
		Member currentUser = (Member) session.getAttribute("user");

		if (currentUser == null) {
			return "redirect:/login";
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

	@GetMapping("/marketImage1")
	@ResponseBody
	public byte[] getMarketImage1(@RequestParam("idx") int mkIdx) {
		return marketRepo.findImg1ByMkIdx(mkIdx);
	}

	@GetMapping("/marketImage2")
	@ResponseBody
	public byte[] getMarketImage2(@RequestParam("idx") int mkIdx) {
		return marketRepo.findImg2ByMkIdx(mkIdx);
	}

	@GetMapping("/marketImage3")
	@ResponseBody
	public byte[] getMarketImage3(@RequestParam("idx") int mkIdx) {
		return marketRepo.findImg3ByMkIdx(mkIdx);
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
		case "560007":
			return "종이";
		default:
			return "알 수 없음";
		}
	}
}
