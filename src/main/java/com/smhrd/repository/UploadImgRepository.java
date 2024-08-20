package com.smhrd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smhrd.entity.UploadImg;

public interface UploadImgRepository extends JpaRepository<UploadImg, Integer> {
    // 필요한 경우 커스텀 쿼리 메서드를 여기에 추가할 수 있습니다.
}