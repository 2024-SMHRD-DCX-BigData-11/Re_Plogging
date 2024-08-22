package com.smhrd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Community;
import com.smhrd.entity.Market;
import com.smhrd.entity.Member;

@Repository
public interface MarketRepository extends JpaRepository<Market, Integer>{
   Page<Market> findByOrderByMkIdxDesc(Pageable pageable);
   Page<Market> findByCategory(String category, Pageable pageable);
   
   
   
   @Query("""
		    select m
		    from Market m
		    where m.user = :user
		    ORDER BY createdAt DESC
		    """)
   
   public List<Market> findByMyMarket(@Param("user") Member user);
   
}