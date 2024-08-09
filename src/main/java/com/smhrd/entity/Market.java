package com.smhrd.entity;

import java.util.Date;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_market") // 테이블 이름을 매핑
public class Market {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mk_idx", insertable = false, updatable = false) 
	private int mkIdx; // 마켓 상품 인덱스 (mk_idx)
	
	@ManyToOne
	@JoinColumn(name = "user_idx", nullable = false) // 외래 키 설정
	private Member user; // 판매자 인덱스 (user_idx)
	
	@Column(name = "mk_category", nullable = false, length = 50)
	private String category; // 상품 카테고리 (mk_category)
	
	@Column(name = "mk_title", nullable = false, length = 300)
	private String title; // 상품 제목 (mk_title)
	
	@Column(name = "mk_mileage", nullable = true)
	private Integer mileage; // 상품 마일리지 (mk_mileage)
	
	@Column(name = "mk_content", nullable = false, columnDefinition = "TEXT")
	private String content; // 상품 내용 (mk_content)
	
	@Column(name = "mk_img1", nullable = false, length = 1200)
	private String img1; // 이미지1 (mk_img1)
	
	@Column(name = "mk_img2", nullable = true, length = 1200)
	private String img2; // 이미지2 (mk_img2)
	
	@Column(name = "mk_img3", nullable = true, length = 1200)
	private String img3; // 이미지3 (mk_img3)
	
	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt; // 게시 날짜 (created_at)
	
	@Column(name = "closed_at", nullable = false)
	private Date closedAt; // 완료 날짜 (closed_at)
	
	@Column(name = "mk_status", nullable = true, length = 20)
	private String status; // 판매 상태 (mk_status)
}
