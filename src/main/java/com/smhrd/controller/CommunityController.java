package com.smhrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.smhrd.entity.Community;
import com.smhrd.entity.Member;
import com.smhrd.repository.CommunityRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class CommunityController {

    @Autowired
    private CommunityRepository communityRepository;

    @GetMapping("/community")
    public ModelAndView goCommunity() {
        List<Community> posts = communityRepository.findAll();
        ModelAndView mv = new ModelAndView("community");
        mv.addObject("posts", posts);
        return mv;
    }

    @GetMapping("/communityWriter")
    public String goWriter() {
        return "communityWriter";
    }

    @PostMapping("/submitPost")
    public String submitPost(Community post, MultipartFile file) {
        /*
         * @RequestParam("title") String title,
                             @RequestParam("category") String category,
                             @RequestParam("content") String content,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("writerId") int writerId
         */
//        Community post = new Community();
//        post.setTitle(title);
//        post.setCategory(category);
//        post.setContent(content);
//        post.setWriter(writerId);  // 실제로는 세션이나 인증 정보를 통해 가져와야 함.

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            try {
                // 파일 저장 경로
                String uploadDir = "/path/to/upload/dir"; // 실제 파일 저장 경로를 지정
                File saveFile = new File(uploadDir, fileName);
                file.transferTo(saveFile);
                post.setImg(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                // 예외 처리 (예: 사용자에게 에러 메시지 전달)
            }
        }

        communityRepository.save(post);

        return "redirect:/community";
    }
}
