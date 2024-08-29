package com.smhrd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Member;
import com.smhrd.entity.Mileage;

@Repository
public interface MileageRepository extends JpaRepository<Mileage, Integer> {

    List<Mileage> findByUser(Member user);
    
    @Query(value = "SELECT fn_mileagecnt(:userIdx)", nativeQuery = true)
    int getMileageCount(@Param("userIdx") int userIdx);

    List<Mileage> findByUserOrderByCreatedAtDesc(Member user);
}