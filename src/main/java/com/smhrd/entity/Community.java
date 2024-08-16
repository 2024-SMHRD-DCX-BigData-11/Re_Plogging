package com.smhrd.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_community")
public class Community {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comm_idx", insertable = false, updatable = false)
    private int idx;

    @Column(name = "comm_category", nullable = false, length = 50)
    private String category;

    @Column(name = "comm_title", nullable = false, length = 1200)
    private String title;

    @Column(name = "comm_content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "comm_file", length = 1200, nullable = true)
    private String img;

    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date indate;

    @Column(name = "comm_views", nullable = false, columnDefinition = "INT UNSIGNED DEFAULT 0")
    private int count;

    @Column(name = "comm_likes", nullable = false, columnDefinition = "INT UNSIGNED DEFAULT 0")
    private int likes;

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false) 
    private Member writer;

    @JsonIgnore
    @OneToMany(mappedBy = "community")
    private List<Comment> comments;
}
