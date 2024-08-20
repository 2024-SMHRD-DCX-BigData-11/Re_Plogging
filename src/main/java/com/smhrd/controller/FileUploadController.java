package com.smhrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.entity.UploadImg;
import com.smhrd.entity.Member;
import com.smhrd.repository.UploadImgRepository;

import jakarta.servlet.http.HttpSession;

import com.smhrd.repository.MemberRepository;

import java.io.IOException;

@Controller
public class FileUploadController {

    @Autowired
    private UploadImgRepository uploadImgRepository;

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam(value = "mileage", required = false, defaultValue = "0") int mileage,
                                   HttpSession session) {   

        try {
            Member member = (Member) session.getAttribute("user");
            if (member == null) {
                throw new IllegalStateException("User not logged in");
            }

            UploadImg uploadImg = new UploadImg();
            uploadImg.setFileName(file.getOriginalFilename());
            uploadImg.setImageData(file.getBytes());
            uploadImg.setMileage(mileage);
            uploadImg.setUserIdx(member); // member 객체를 그대로 설정

            uploadImgRepository.save(uploadImg);

            return "redirect:/success";
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }

}