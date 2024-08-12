package com.smhrd.entity;

import java.util.Date;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_plogging_list") // 테이블 이름을 매핑
public class Plogging {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plog_idx", insertable = false, updatable = false) 
	private int idx; // 플로깅 인덱스 (plog_idx)
	
	@ManyToOne
	@JoinColumn(name = "user_idx", nullable = false) // 외래 키 설정
	private Member user; // 회원 인덱스 (user_idx)
	
	@Column(name = "course_name", nullable = false, length = 100)
	private String courseName; // 코스 이름 (course_name)
	
	@Column(name = "qr1", nullable = false, columnDefinition = "INT DEFAULT 0")
	private int qr1; // QR1 (qr1)
	
	@Column(name = "qr2", nullable = false, columnDefinition = "INT DEFAULT 0")
	private int qr2; // QR2 (qr2)
	
	@Column(name = "qr3", nullable = false, columnDefinition = "INT DEFAULT 0")
	private int qr3; // QR3 (qr3)
	
	@Column(name = "qr1_time", nullable = false)
	private Date qr1Time; // QR1 시간 (qr1_time)
	
	@Column(name = "qr2_time", nullable = false)
	private Date qr2Time; // QR2 시간 (qr2_time)
	
	@Column(name = "qr3_time", nullable = false)
	private Date qr3Time; // QR3 시간 (qr3_time)
	
	@Column(name = "started_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date startedAt; // 시작일자 (started_at)
}
