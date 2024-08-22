package com.smhrd.entity;

import java.util.Date;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_market")
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mk_idx", insertable = false, updatable = false)
    private int mkIdx;

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    private Member user;

    @Column(name = "mk_category", nullable = false, length = 50)
    private String category;

    @Column(name = "mk_title", nullable = false, length = 300)
    private String title;

    @Column(name = "mk_mileage", nullable = true)
    private Integer mileage;

    @Column(name = "mk_content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Lob
    @Column(name = "mk_img1", nullable = false, columnDefinition = "MEDIUMBLOB")
    private byte[] img1;

    @Lob
    @Column(name = "mk_img2", nullable = true, columnDefinition = "MEDIUMBLOB")
    private byte[] img2;

    @Lob
    @Column(name = "mk_img3", nullable = true, columnDefinition = "MEDIUMBLOB")
    private byte[] img3;

    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name = "closed_at", nullable = true)
    private Date closedAt;

    @Column(name = "mk_status", nullable = true, length = 20)
    private String status;
}
