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

    List<Community> findByCategory(String category);

    List<Community> findByWriter(Member writer);

    List<Community> findByTitleContaining(String keyword);

    List<Community> findByCountGreaterThanEqual(int count);

    List<Community> findByOrderByIndateDesc();

    @Query("""
            select c
            from Community c
            where c.indate between :startDate and :endDate
            """)
    List<Community> findAllByDateRange(Date startDate, Date endDate);
}
