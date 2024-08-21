package com.smhrd.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Community;
import com.smhrd.entity.Member;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {

    Page<Community> findAllByOrderByIndateDesc(Pageable pageable);

    Page<Community> findByCategoryOrderByIndateDesc(String category, Pageable pageable);

    Page<Community> findByTitleContainingOrderByIndateDesc(String keyword, Pageable pageable);

    Page<Community> findByCategoryAndTitleContainingOrderByIndateDesc(String category, String keyword, Pageable pageable);
    
    
    @Query("""
		    select c
		    from Community c
		    where c.writer = :writer
		    ORDER BY indate DESC
		    """)
    
    public List<Community> findByMyCommunity(@Param("writer") Member writer);
}
