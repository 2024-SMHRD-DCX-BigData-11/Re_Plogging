package com.smhrd.entity;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_upload_img")
public class UploadImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_idx", columnDefinition = "int", insertable = false, updatable = false)
    private int fileIdx;
    
    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] imageData;
    
    @Column(name = "file_ext", nullable = false)
    private String fileExt;

    @Column(name = "uploaded_at", columnDefinition = "datetime default now()", insertable = false, updatable = false)
    private Timestamp uploadedAt;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private Member userIdx;

    @Column(name = "mileage", columnDefinition = "int")
    private int mileage;
}
