package com.smhrd.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Community;
import com.smhrd.entity.Member;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {
    
    // 1. 카테고리별로 커뮤니티 글을 찾는 메소드
    List<Community> findByCategory(String category);
    
    // 2. 작성자별로 커뮤니티 글을 찾는 메소드
    List<Community> findByWriter(Member writer);

    // 3. 제목에 특정 키워드가 포함된 글을 찾는 메소드 (LIKE 연산자 사용)
    List<Community> findByTitleContaining(String keyword);
    
    // 4. 특정 조회수 이상의 글을 찾는 메소드
    List<Community> findByCountGreaterThanEqual(int count);
    
    // 5. 작성일 기준으로 최신 글을 찾는 메소드
    List<Community> findByOrderByIndateDesc();

    // 6. 커스텀 쿼리: 특정 기간 내에 작성된 글을 찾는 메소드
    @Query("""
            select c
            from Community c
            where c.indate between :startDate and :endDate
            """)
    List<Community> findAllByDateRange(Date startDate, Date endDate);
}
