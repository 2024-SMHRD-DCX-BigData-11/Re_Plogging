package com.smhrd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smhrd.entity.Recycle;

public interface RecycleRepository extends JpaRepository<Recycle, Integer> {
	
	@Query("SELECT r FROM Recycle r WHERE r.recycleTarget = :recycleTarget")
	List<Recycle> findRecycleByTarget(@Param("recycleTarget") String recycleTarget);
}
