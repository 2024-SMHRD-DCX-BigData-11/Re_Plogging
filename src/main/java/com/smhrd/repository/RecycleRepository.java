package com.smhrd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.entity.Plogging;
import com.smhrd.entity.Recycle;

public interface RecycleRepository extends JpaRepository<Recycle, Integer> {

}
