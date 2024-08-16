package com.smhrd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Comment;
import com.smhrd.entity.Community;
import com.smhrd.entity.Member;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    
    // 특정 Community에 속한 모든 댓글 조회
    List<Comment> findByCommunity(Community community);
    
    // 특정 Member가 작성한 모든 댓글 조회
    List<Comment> findByUser(Member user);

    // 특정 Community와 특정 Member가 작성한 모든 댓글 조회
    List<Comment> findByCommunityAndUser(Community community, Member user);
}
