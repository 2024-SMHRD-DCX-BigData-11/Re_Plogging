package com.smhrd.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {

    List<Community> findByCategory(String category);

    List<Community> findByTitleContaining(String keyword);

    // 카테고리와 키워드로 검색
    List<Community> findByCategoryAndTitleContaining(String category, String keyword);
}
