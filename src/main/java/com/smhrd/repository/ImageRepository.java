package com.smhrd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smhrd.entity.UploadImg;

public interface ImageRepository extends JpaRepository<UploadImg, Long> {
}
