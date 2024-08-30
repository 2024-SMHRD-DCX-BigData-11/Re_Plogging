package com.smhrd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.RecycleCategory;

@Repository
public interface RecycleCategoryRepository extends JpaRepository<RecycleCategory, Integer> {

	 @Query("""
	            select u
	            from RecycleCategory u
	            """)
	 public List<RecycleCategory> findCategory();
}
