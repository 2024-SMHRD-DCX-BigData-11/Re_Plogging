package com.smhrd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Market;
import com.smhrd.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

}
