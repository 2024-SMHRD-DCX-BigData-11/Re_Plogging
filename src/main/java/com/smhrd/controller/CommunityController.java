package com.smhrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.smhrd.entity.Comment;
import com.smhrd.entity.Community;
import com.smhrd.entity.Member;
import com.smhrd.repository.CommentRepository;
import com.smhrd.repository.CommunityRepository;
import com.smhrd.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class CommunityController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommunityRepository communityRepository;
    
    @Autowired
    private MemberRepository memberRepository;

    @RequestMapping("/community")
    public ModelAndView goCommunity() {
        ModelAndView mav = new ModelAndView("community");
        
        List<Community> list = communityRepository.findAll();
        System.out.println("list.size() : " + list.size());

        mav.addObject("list", list);
        return mav;
    }
    
    @GetMapping("/communityWriter")
    public String goWriter() {
        return "communityWriter";
    }

    @GetMapping("/communityRead")
    public String goRead(@RequestParam("idx") int idx, Model model, HttpSession session) {
        Community community = communityRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("Invalid community Id:" + idx));

        community.setCount(community.getCount() + 1);
        communityRepository.save(community);
        
        model.addAttribute("community", community);
        model.addAttribute("user", session.getAttribute("user"));
        return "communityRead";
    }

    @PostMapping("/submitPost")
    public ModelAndView submitPost(@RequestParam("title") String title,
                                   @RequestParam("category") String category,
                                   @RequestParam("content") String content,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam("writerId") int writerId) throws IOException {
        ModelAndView mav = new ModelAndView("redirect:/community");
        
        // 카테고리 값 변환
        if (category.equals("plogging")) {
            category = "플로깅";
        } else if (category.equals("separation")) {
            category = "분리배출";
        } else if (category.equals("freeboard")) {
            category = "자유게시판";
        }

        // 작성자 정보 가져오기
        Member writer = memberRepository.findById(writerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid writer Id:" + writerId));
        
        // 파일 저장 처리
        String fileName = null;
        if (!file.isEmpty()) {
            fileName = file.getOriginalFilename();
            String uploadDir = "C:/uploads/";
            File dir = new File(uploadDir);

            // 디렉토리가 존재하지 않으면 생성
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            String filePath = uploadDir + fileName;
            file.transferTo(new File(filePath));
        }
        
        // 새로운 Community 엔티티 생성 및 저장
        Community newPost = new Community();
        newPost.setTitle(title);
        newPost.setCategory(category); // 변환된 카테고리 값을 설정
        newPost.setContent(content);
        newPost.setImg(fileName);
        newPost.setWriter(writer);
        newPost.setIndate(new Date());
        newPost.setCount(0);
        newPost.setLikes(0);

        communityRepository.save(newPost);

        return mav;
    }
    
    @PostMapping("/likePost")
    public String likePost(@RequestParam("idx") int idx, HttpSession session, Model model) {
        Member loggedInUser = (Member) session.getAttribute("user");
        
        
        if (loggedInUser == null) {
            // 로그인하지 않은 경우
            model.addAttribute("errorMessage", "로그인 후 좋아요를 누를 수 있습니다.");
            return "redirect:/communityRead?idx=" + idx;
        }
        
        // 로그인한 경우 좋아요 수 증가
        Community community = communityRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("Invalid community Id:" + idx));

        community.setLikes(community.getLikes() + 1);
        communityRepository.save(community);

        return "redirect:/communityRead?idx=" + idx;
    }
    
    // 야매
    @PostMapping("/comments/addComment")
    public String createComment(@RequestParam("communityId") Integer communityId,
                                                 @RequestParam("commentContent") String commentContent,
                                                 HttpSession session) {
        Member user = (Member) session.getAttribute("user");
        if (user == null) {
            return "redirect:/main";
        }

        Community community = communityRepository.findById(communityId).orElse(null);
        if (community == null) {
            return "redirect:/main";
        }

        Comment comment = new Comment();
        comment.setCommunity(community);
        comment.setUser(user);
        comment.setMessage(commentContent);

        Comment savedComment = commentRepository.save(comment);
        return "redirect:/communityRead?idx=" + communityId;
    }
}
