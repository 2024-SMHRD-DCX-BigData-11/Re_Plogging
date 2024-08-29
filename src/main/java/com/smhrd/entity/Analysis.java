package com.smhrd.entity;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_analysis")
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anal_idx", columnDefinition = "INT", insertable = false, updatable = false)
    private int analIdx;

    @Column(name = "file_idx", columnDefinition = "INT", nullable = false)
    private int fileIdx;

    @Column(name = "anal_result_img_name", columnDefinition = "LONGTEXT")
    private String analResultImgName;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;
}