package com.smhrd.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    @ManyToOne
    @JoinColumn(name = "file_idx") // UploadImg 엔티티의 file_idx를 참조하는 외래키
    private UploadImg fileIdx;

    @Column(name = "anal_label", length = 100)
    private String analLabel;

    @Column(name = "anal_result", columnDefinition = "TEXT")
    private String analResult;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;
}