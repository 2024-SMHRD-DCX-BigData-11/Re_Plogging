package com.smhrd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "tb_recycle_categories")
public class RecycleCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_idx", columnDefinition = "INT", insertable = false, updatable = false)
    private int categoryIdx;

    @Column(name = "category_name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String categoryName;

    @Column(name = "category_info", columnDefinition = "TEXT", nullable = true)
    private String categoryInfo;

    @Column(name = "category_point", columnDefinition = "INT", nullable = false)
    private int categoryPoint;

    @OneToMany(mappedBy = "category")
    private List<AnalysisDetail> analysisDetails;
}
