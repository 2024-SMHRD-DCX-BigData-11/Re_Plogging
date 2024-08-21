package com.smhrd.controller;

import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.entity.Member;
import com.smhrd.entity.Plogging;
import com.smhrd.repository.MemberRepository;
import com.smhrd.repository.PloggingRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class PloggingController {

	@Autowired
	private MemberRepository repo; // 레파지토리를 사용하기 위한 변수 선언

	@Autowired
	private PloggingRepository prepo;

//	@RequestMapping("/qrSuccess")
//	public String goqrSuccess() {
//		return "qrSuccess";
//	}

	public String loginMember(String userId, String userPw, String targetUrl, HttpSession session) {
		Member member = repo.findByUserIdAndUserPw(userId, userPw);
		if (member != null) {
			session.setAttribute("userId", userId);
			return targetUrl;
		} else {
			return "/boot/loginFail";
		}
	}

	/* 플로코스 */
	@RequestMapping("/ploggingList")
	public String goploggingList() {
		return "ploggingList";
	}

	/* 플로깅 A코스 선택 시 */
	@RequestMapping("/ploggingStartA")
	public String ploggingStartA(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("user");
		if (member != null) {
			// 현재 진행 중인 플로깅이 있는지 확인
			Optional<Plogging> currentPlogging = prepo.findTopByUserOrderByStartedAtDesc(member);
			if (currentPlogging.isPresent()) {
				Plogging plogging = currentPlogging.get();
				// QR1, QR2, QR3 중 하나라도 0이면 진행 중인 것으로 간주
				if (plogging.getQr1() == 0 || plogging.getQr2() == 0 || plogging.getQr3() == 0) {
					return "redirect:/myplogging?ploggingError=true"; // 진행 중인 플로깅이 있으면 쿼리 파라미터 추가하여 리다이렉트
				}
			}

			// 새로운 플로깅 생성
			Plogging p = new Plogging();
			p.setUser(member);
			p.setCourseName("A코스(순천대학교)");
			p.setQr1Name("열린광장");
			p.setQr2Name("공과대학 1호관");
			p.setQr3Name("대학본부");
			prepo.save(p);
			return "redirect:/myplogging?plogging=true"; // 새 플로깅 생성 후 이동
		} else {
			System.out.println("유저 없음.");
			logout(session);
			return "redirect:/main"; // 유저가 없을 경우 메인 페이지로 이동
		}
	}

	/* 플로깅 B코스 선택 시 */
	@RequestMapping("/ploggingStartB")
	public String ploggingStartB(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("user");
		if (member != null) {
			// 현재 진행 중인 플로깅이 있는지 확인
			Optional<Plogging> currentPlogging = prepo.findTopByUserOrderByStartedAtDesc(member);
			if (currentPlogging.isPresent()) {
				Plogging plogging = currentPlogging.get();
				// QR1, QR2, QR3 중 하나라도 0이면 진행 중인 것으로 간주
				if (plogging.getQr1() == 0 || plogging.getQr2() == 0 || plogging.getQr3() == 0) {
					return "redirect:/myplogging?ploggingError=true"; // 진행 중인 플로깅이 있으면 쿼리 파라미터 추가하여 리다이렉트
				}
			}

			// 새로운 플로깅 생성
			Plogging p = new Plogging();
			p.setUser(member);
			p.setCourseName("B코스(순천 조례호수공원)");
			p.setQr1Name("야외무대");
			p.setQr2Name("산책로1");
			p.setQr3Name("산책로2");
			prepo.save(p);
			return "redirect:/myplogging?plogging=true"; // 새 플로깅 생성 후 이동
		} else {
			System.out.println("유저 없음.");
			logout(session);
			return "redirect:/main"; // 유저가 없을 경우 메인 페이지로 이동
		}
	}

	/* 플로깅 C코스 선택 시 */
	@RequestMapping("/ploggingStartC")
	public String ploggingStartC(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("user");
		if (member != null) {
			// 현재 진행 중인 플로깅이 있는지 확인
			Optional<Plogging> currentPlogging = prepo.findTopByUserOrderByStartedAtDesc(member);
			if (currentPlogging.isPresent()) {
				Plogging plogging = currentPlogging.get();
				// QR1, QR2, QR3 중 하나라도 0이면 진행 중인 것으로 간주
				if (plogging.getQr1() == 0 || plogging.getQr2() == 0 || plogging.getQr3() == 0) {
					return "redirect:/myplogging?ploggingError=true"; // 진행 중인 플로깅이 있으면 쿼리 파라미터 추가하여 리다이렉트
				}
			}

			// 새로운 플로깅 생성
			Plogging p = new Plogging();
			p.setUser(member);
			p.setCourseName("C코스(순천 오천그린광장)");
			p.setQr1Name("오천그린광장");
			p.setQr2Name("산책로1");
			p.setQr3Name("산책로2");
			prepo.save(p);
			return "redirect:/myplogging?plogging=true"; // 새 플로깅 생성 후 이동
		} else {
			System.out.println("유저 없음.");
			logout(session);
			return "redirect:/main"; // 유저가 없을 경우 메인 페이지로 이동
		}
	}

	/* 마이 페이지 */
	@RequestMapping("/mypage")
	public String myPage(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("user");
		if (member != null) {
			int completedPloggingCount = prepo.countCompletedPlogging(member.getUserIdx());
			model.addAttribute("completedPloggingCount", completedPloggingCount);
			return "mypage";
		} else {
			System.out.println("유저 없음.");
			logout(session);
			return "redirect:/main";
		}
	}

	/* 나의 플로깅 */
	@RequestMapping("/myplogging")
	public String gomyplogging(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("user");
		if (member != null) {
			// 현재 진행 중인 플로깅 가져오기
			Optional<Plogging> currentPlogging = prepo.findTopByUserOrderByStartedAtDesc(member);
			if (currentPlogging.isPresent()) {
				model.addAttribute("currentPlogging", currentPlogging.get());
			}

			// 완료된 플로깅 목록 가져오기
			List<Plogging> completedPlogging = prepo.findByUserAndQr1AndQr2AndQr3(member, 1, 1, 1);
			model.addAttribute("completedPlogging", completedPlogging);

			return "myplogging";
		} else {
			System.out.println("유저 없음.");
			logout(session);
			return "main";
		}
	}

	/* 플로깅 취소 */
	@PostMapping("/ploggingCancel")
    public String cancelPlogging(@RequestParam("ploggingIdx") int ploggingIdx, HttpSession session) {
        Member currentUser = (Member) session.getAttribute("user");

        if (currentUser == null) {
            return "redirect:/login"; // 로그인되지 않은 경우 로그인 페이지로 리디렉션
        }

        Plogging plogging = prepo.findById(ploggingIdx)
                .orElseThrow(() -> new IllegalArgumentException("Invalid plogging Id:" + ploggingIdx));

        // 현재 사용자가 해당 플로깅의 시작자인지 확인
        if (plogging.getUser().getUserIdx() != currentUser.getUserIdx()) {
            return "redirect:/main"; // 작성자가 아니면 플로깅 목록으로 리디렉션
        }

        prepo.delete(plogging); // 플로깅 취소(삭제)

        return "redirect:/myplogging"; // 삭제 후 플로깅 목록으로 리디렉션
    }

	/* 로그아웃 */
	public String logout(HttpSession session) {
		session.invalidate();
		return "/boot/main";
	}
}
