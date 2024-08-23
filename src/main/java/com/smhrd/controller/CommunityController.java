package com.smhrd.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.smhrd.entity.Comment;
import com.smhrd.entity.Community;
import com.smhrd.entity.Member;
import com.smhrd.repository.CommentRepository;
import com.smhrd.repository.CommunityRepository;
import com.smhrd.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommunityController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private MemberRepository memberRepository;

    @RequestMapping("/community")
    public ModelAndView goCommunity(@RequestParam(value = "category", required = false) String category,
                                    @RequestParam(value = "keyword", required = false) String keyword,
                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size) {

        ModelAndView mav = new ModelAndView("community");

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Community> communityPage;

        if (category != null) {
            if (category.equals("plogging")) {
                category = "플로깅";
            } else if (category.equals("separation")) {
                category = "분리배출";
            } else if (category.equals("freeboard")) {
                category = "자유게시판";
            }
        }

        if ((category == null || category.isEmpty()) && (keyword == null || keyword.isEmpty())) {
            communityPage = communityRepository.findAllByOrderByIndateDesc(pageable);
        } else if (category != null && !category.isEmpty() && (keyword == null || keyword.isEmpty())) {
            communityPage = communityRepository.findByCategoryOrderByIndateDesc(category, pageable);
        } else if ((category == null || category.isEmpty()) && keyword != null && !keyword.isEmpty()) {
            communityPage = communityRepository.findByTitleContainingOrderByIndateDesc(keyword, pageable);
        } else {
            communityPage = communityRepository.findByCategoryAndTitleContainingOrderByIndateDesc(category, keyword, pageable);
        }

        mav.addObject("list", communityPage.getContent());
        mav.addObject("selectedCategory", category);
        mav.addObject("keyword", keyword);
        mav.addObject("currentPage", page);
        mav.addObject("totalPages", communityPage.getTotalPages());

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

        if (category.equals("plogging")) {
            category = "플로깅";
        } else if (category.equals("separation")) {
            category = "분리배출";
        } else if (category.equals("freeboard")) {
            category = "자유게시판";
        }

        Member writer = memberRepository.findById(writerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid writer Id:" + writerId));

        byte[] imageData = null;
        if (!file.isEmpty()) {
            imageData = file.getBytes();
        }

        Community newPost = new Community();
        newPost.setTitle(title);
        newPost.setCategory(category);
        newPost.setContent(content);
        newPost.setImg(imageData);
        newPost.setWriter(writer);
        newPost.setIndate(new Date());
        newPost.setCount(0);
        newPost.setLikes(0);

        communityRepository.save(newPost);

        return mav;
    }

    @PostMapping("/edit")
    public ModelAndView edit(@RequestParam("title") String title, 
                             @RequestParam("category") String category,
                             @RequestParam("content") String content, 
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("writerId") int writerId, 
                             @RequestParam("editId") int idx, 
                             @RequestParam("uploaded") String uploaded) throws IOException {
        ModelAndView mav = new ModelAndView("redirect:/communityRead?idx=" + idx);

        if (category.equals("plogging")) {
            category = "플로깅";
        } else if (category.equals("separation")) {
            category = "분리배출";
        } else if (category.equals("freeboard")) {
            category = "자유게시판";
        }

        Member writer = memberRepository.findById(writerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid writer Id:" + writerId));

        Community community = communityRepository.findById(idx).orElse(null);
        community.setCategory(category);
        community.setTitle(title);
        community.setContent(content);

        if (!file.isEmpty()) {
            byte[] imageData = file.getBytes();
            community.setImg(imageData);
        } else {
            if (uploaded == null || uploaded.isEmpty()) {
                community.setImg(null);
            }
        }

        communityRepository.save(community);

        return mav;
    }

    @GetMapping("/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable("id") int id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid community Id:" + id));

        byte[] imageContent = community.getImg();
        return ResponseEntity.ok().contentType(org.springframework.http.MediaType.IMAGE_JPEG).body(imageContent);
    }

    @PostMapping("/likePost")
    public String likePost(@RequestParam("idx") int idx, HttpSession session, Model model) {
        Member loggedInUser = (Member) session.getAttribute("user");

        if (loggedInUser == null) {
            model.addAttribute("errorMessage", "로그인 후 좋아요를 누를 수 있습니다.");
            return "redirect:/communityRead?idx=" + idx;
        }

        Community community = communityRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("Invalid community Id:" + idx));

        community.setLikes(community.getLikes() + 1);
        communityRepository.save(community);

        return "redirect:/communityRead?idx=" + idx;
    }

    @PostMapping("/comments/addComment")
    public String createComment(@RequestParam("communityId") Integer communityId,
                                @RequestParam("commentContent") String commentContent, 
                                HttpSession session, Model model) {
        Member user = (Member) session.getAttribute("user");
        if (user == null) {
            return "redirect:/main";
        }

        // 댓글 내용이 비어 있는지 확인
        if (commentContent == null || commentContent.trim().isEmpty()) {
            model.addAttribute("errorMessage", "댓글 내용을 입력해주세요.");
            return "redirect:/communityRead?idx=" + communityId + "&error=emptyComment";
        }

        Community community = communityRepository.findById(communityId).orElse(null);
        if (community == null) {
            return "redirect:/main";
        }

        Comment comment = new Comment();
        comment.setCommunity(community);
        comment.setUser(user);
        comment.setMessage(commentContent);

        commentRepository.save(comment);
        return "redirect:/communityRead?idx=" + communityId;
    }

    @RequestMapping("/editPost")
    public ModelAndView editPost(@RequestParam("idx") int idx) {
        Community community = communityRepository.findById(idx).orElse(null);

        ModelAndView mav = new ModelAndView("communityWriter");
        mav.addObject("community", community);

        return mav;
    }

    @RequestMapping("/deletePost")
    public String deletePost(@RequestParam("idx") int idx) {
        Community community = communityRepository.findById(idx).orElse(null);
        communityRepository.delete(community);
        return "redirect:/community";
    }
}
