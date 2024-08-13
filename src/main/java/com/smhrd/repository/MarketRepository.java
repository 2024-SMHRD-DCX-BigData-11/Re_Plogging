package com.smhrd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Market;

@Repository
public interface MarketRepository extends JpaRepository<Market, Integer>{
	public List<Market> findByMkIdx(Integer mkIdx);

	
	
}
