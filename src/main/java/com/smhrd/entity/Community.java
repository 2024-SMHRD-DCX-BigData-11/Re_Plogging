package com.smhrd.entity;

import java.util.Date;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_community") // 테이블 이름을 매핑
public class Community {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comm_idx", insertable = false, updatable = false) 
	private int idx; // 글 인덱스 (comm_idx)
	
	@Column(name = "comm_category", nullable = false, length = 50)
	private String category; // 글 카테고리 (comm_category)
	
	@Column(name = "comm_title", nullable = false, length = 1200)
	private String title; // 글 제목 (comm_title)
	
	@Column(name = "comm_content", nullable = false, columnDefinition = "TEXT")
	private String content; // 글 내용 (comm_content)
	
	@Column(name = "comm_file", length = 1200, nullable = true)
	private String img; // 글 첨부파일 (comm_file)
	
	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date indate; // 작성일 (created_at)
	
	@Column(name = "comm_views", nullable = false, columnDefinition = "INT UNSIGNED DEFAULT 0")
	private int count; // 조회수 (comm_views)
	
	@Column(name = "comm_likes", nullable = false, columnDefinition = "INT UNSIGNED DEFAULT 0")
	private int likes; // 좋아요 수 (comm_likes)
	
	@ManyToOne
	@JoinColumn(name = "user_idx", nullable = false) // 외래 키 설정
	private Member writer; // 작성자 (user_idx)
}
