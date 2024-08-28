package com.smhrd.entity;

import java.sql.Blob;
import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_upload_img")
public class UploadImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_idx", columnDefinition = "INT", insertable = false, updatable = false)
    private int fileIdx;

    @Column(name = "file_name", length = 255, nullable = false)
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private Member userIdx;

    @Column(name = "mileage", columnDefinition = "INT")
    private int mileage;
    
    @Column(name = "file_size", columnDefinition = "BIGINT")
    private long fileSize;

    @Column(name = "uploaded_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp uploadedAt;  // 업로드 날짜 컬럼 추가
}