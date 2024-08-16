package com.smhrd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smhrd.entity.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {

    Page<Community> findByCategory(String category, Pageable pageable);

    Page<Community> findByTitleContaining(String keyword, Pageable pageable);

    Page<Community> findByCategoryAndTitleContaining(String category, String keyword, Pageable pageable);
}
