package com.smhrd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smhrd.entity.Comment;
import com.smhrd.entity.Community;
import com.smhrd.entity.Member;
import com.smhrd.repository.CommentRepository;
import com.smhrd.repository.CommunityRepository;
import com.smhrd.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private MemberRepository memberRepository;

    // 특정 게시물의 모든 댓글 조회
    @GetMapping("/by-community/{commIdx}")
    public ResponseEntity<List<Comment>> getCommentsByCommunity(@PathVariable Integer commIdx) {
        Community community = communityRepository.findById(commIdx).orElse(null);
        if (community == null) {
            return ResponseEntity.notFound().build();
        }
        List<Comment> comments = commentRepository.findByCommunity(community);
        return ResponseEntity.ok(comments);
    }

    // 댓글 작성, ajax 써서 만들든가 말든가 (CommunityController.java에 이거 지우고)
//    @PostMapping("/addComment")
//    public ResponseEntity<Comment> createComment(@RequestParam("communityId") Integer communityId,
//                                                 @RequestParam("commentContent") String commentContent,
//                                                 HttpSession session) {
//        Member user = (Member) session.getAttribute("user");
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        Community community = communityRepository.findById(communityId).orElse(null);
//        if (community == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        Comment comment = new Comment();
//        comment.setCommunity(community);
//        comment.setUser(user);
//        comment.setMessage(commentContent);
//
//        Comment savedComment = commentRepository.save(comment);
//        return ResponseEntity.ok(savedComment);
//    }

    // 댓글 수정
    @PostMapping("/updateComment")
    public ResponseEntity<Void> updateComment(@RequestParam("commentId") Integer commentId,
                                              @RequestParam("commentContent") String commentContent) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        
        comment.setMessage(commentContent);
        commentRepository.save(comment);
        return ResponseEntity.ok().build();
    }

    // 댓글 삭제
    @PostMapping("/deleteComment")
    public ResponseEntity<Void> deleteComment(@RequestParam("commentId") Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        
        commentRepository.delete(comment);
        return ResponseEntity.noContent().build();
    }
}
