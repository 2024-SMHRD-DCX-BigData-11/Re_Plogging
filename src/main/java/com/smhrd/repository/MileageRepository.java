package com.smhrd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Member;
import com.smhrd.entity.Mileage;

@Repository
public interface MileageRepository extends JpaRepository<Mileage, Integer> {

    List<Mileage> findByUser(Member user);

}