package com.smhrd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smhrd.entity.AnalysisDetail;

public interface AnalysisDetailRepository extends JpaRepository<AnalysisDetail, Integer> {
	
	@Query("""
		    select a
		    from AnalysisDetail a
		    where a.analysis.analIdx = :analIdx
		    """)
	public List<AnalysisDetail> findByResultText(@Param("analIdx") int analIdx);
}
