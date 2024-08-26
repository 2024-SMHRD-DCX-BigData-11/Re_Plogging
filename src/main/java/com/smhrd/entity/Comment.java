package com.smhrd.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_comment") // 테이블 이름을 매핑
public class Comment {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "cmt_idx", insertable = false, updatable = false) // 댓글 인덱스를 기본 키로 설정
   private int idx; // 댓글 인덱스 (cmt_idx)
   
   @ManyToOne
   @JoinColumn(name = "comm_idx", nullable = false) // 원글과의 관계 설정 (외래 키)
   private Community community; // 원글 인덱스 (comm_idx)
   
   @Column(name = "cmt_content", length = 900, nullable = false) // 댓글 내용
   private String message; // 댓글 내용 (cmt_content)

   
   @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private Date indate; // 댓글 작성일자 (created_at)
   
   @ManyToOne
   @JoinColumn(name = "user_idx", nullable = false) // 사용자와의 관계 설정 (외래 키)
   private Member user; // 사용자 인덱스 (user_idx)
}
