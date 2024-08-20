package com.smhrd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Mileage;

@Repository
public interface MileageRepository extends JpaRepository<Mileage, Integer>{

}
