package com.smhrd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smhrd.entity.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {

    // 카테고리별 최신순 정렬
    Page<Community> findByCategoryOrderByIndateDesc(String category, Pageable pageable);

    // 키워드로 최신순 정렬
    Page<Community> findByTitleContainingOrderByIndateDesc(String keyword, Pageable pageable);

    // 카테고리와 키워드로 최신순 정렬
    Page<Community> findByCategoryAndTitleContainingOrderByIndateDesc(String category, String keyword, Pageable pageable);
    
    // 전체 게시글을 최신순으로 정렬
    Page<Community> findAllByOrderByIndateDesc(Pageable pageable);
}
