package com.smhrd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Plogging;

@Repository
public interface PloggingRepository extends JpaRepository<Plogging, Integer> {

}
