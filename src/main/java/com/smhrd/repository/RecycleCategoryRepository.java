package com.smhrd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smhrd.entity.RecycleCategory;

public interface RecycleCategoryRepository extends JpaRepository<RecycleCategory, Integer> {
	

}
