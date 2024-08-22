package com.smhrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.codec.digest.DigestUtils;

import com.smhrd.entity.Member;
import com.smhrd.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    private MemberRepository repo;

    @RequestMapping("/main")
    public String goMain() {
        return "main";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw,
                        HttpSession session) {
        String loginEncryptedPw = DigestUtils.sha512Hex(userPw);
        Member member = repo.findByUserIdAndUserPw(userId, loginEncryptedPw);

        if (member == null) {
            return "redirect:/main?loginError=true";
        } else {
            session.setAttribute("user", member);
            return "redirect:/main";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/main";
    }

    @RequestMapping("/userInfoModify")
    public String goUserInfoModify() {
        return "userInfoModify";
    }

    @RequestMapping("/userWithdrawal")
    public String goUserWithdrawal() {
        return "userWithdrawal";
    }

    @RequestMapping("/deleteMember")
    public String deleteMember(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw,
                               HttpSession session) {
        String deletePw = DigestUtils.sha512Hex(userPw);
        int deletedCount = repo.deleteByUserIdAndUserPw(userId, deletePw);

        if (deletedCount > 0) {
            session.invalidate();
            return "redirect:/main";
        } else {
            return "redirect:/userWithdrawal?deleteError=true";
        }
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/update")
    public void update() {
    }

    @PostMapping("/update")
    public String update(Member member, HttpSession session) {
        repo.save(member);
        session.setAttribute("user", member);
        return "redirect:/main";
    }

    /* 프로필 이미지 업데이트 */
    @PostMapping("/updateProfileImage")
    public String updateProfileImage(@RequestParam("file") MultipartFile file, HttpSession session) {
        Member member = (Member) session.getAttribute("user");
        try {
            if (!file.isEmpty()) {
                byte[] imageData = file.getBytes();
                member.setUserProfileImg(imageData);
                repo.save(member);
                session.setAttribute("user", member);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/userInfoModify?uploadError=true";
        }
        return "redirect:/userInfoModify";
    }

    /* 프로필 이미지 가져오기 */
    @GetMapping("/profileImage")
    @ResponseBody
    public byte[] getProfileImage(HttpSession session) {
        Member member = (Member) session.getAttribute("user");
        return member.getUserProfileImg();
    }
}
