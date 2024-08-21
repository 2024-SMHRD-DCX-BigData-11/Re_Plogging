package com.smhrd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smhrd.entity.Recycle;

public interface RecycleRepository extends JpaRepository<Recycle, Integer> {

    @Query("SELECT r FROM Recycle r WHERE r.recycleTarget = :recycleTarget")
    Page<Recycle> findRecycleByTarget(@Param("recycleTarget") String recycleTarget, Pageable pageable);
}
