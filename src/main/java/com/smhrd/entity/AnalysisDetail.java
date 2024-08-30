package com.smhrd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_analysis_details")
public class AnalysisDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "det_idx", columnDefinition = "INT", insertable = false, updatable = false)
    private int detIdx;

    @ManyToOne
    @JoinColumn(name = "anal_idx", nullable = false)
    private Analysis analysis;

    @ManyToOne
    @JoinColumn(name = "category_idx", nullable = false)
    private RecycleCategory category;

    @Column(name = "category_count", columnDefinition = "INT", nullable = false)
    private int categoryCount;
      
}
