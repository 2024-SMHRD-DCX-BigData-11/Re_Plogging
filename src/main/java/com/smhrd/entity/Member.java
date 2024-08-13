package com.smhrd.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;
import java.sql.Timestamp;


@Entity	// JPA한테 이 DTO 객체가 테이블과 관련있는 객체임을 알려주는 장치 => ORM(Object Relational Mapping)를 사용하기 위함
@Data
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	// JPA
	// ORM(Object Relational Mapping)
	// java 객체 <---> 관계형 데이터
	// java 객체 생성하면, 그 객체에 맞춰 테이블을 자동 생성
	// 개발 초기에 테이블, DTO를 변경하기 쉬움
	// SQL문을 작성X >> but, 서버 시작 시간이 살~짝 길어짐
	
	// @Id	// PrimaryKey를 걸기위한 어노테이션, 반드시 하나는 존재해야 함. (그렇지 않으면 error)
	// insertable = false 컬럼을 수정한 이후 들어오는 데이터를 막는 것
	// updatable = false 컬럼을 수정한 이후 기존에 저장되어 있던 데이터를 수정할 수 없게끔 막는것
	// GeneratedValue(strategy = GenerationType.IDENTITY) 기본키 생성을 DB에게 위임
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_idx", columnDefinition = "int", insertable = false, updatable = false)
	private int userIdx;
	
	@Column(name = "user_id", length = 50, nullable = false)
	private String userId;
	
	@Column(name = "user_pw", length = 128, nullable = false)
	private String userPw;
	
	@Column(name = "user_phone", length = 20, nullable = false)
	private String userPhone;
	
	@Column(name = "user_nick", length = 30, nullable = false)
	private String userNick;
	
	@Column(name = "user_profile_img", length = 1000, nullable = false)
	private String userProfileImg;
	
	@Column(name = "joined_at", columnDefinition = "datetime default now()", insertable = false, updatable = false)
	private Timestamp joinedAt;
	
	@Column(name = "mileage_amount", columnDefinition = "integer default 500")
	private int mileageAmount;
	
	@PrePersist
    public void prePersist() {
        if (this.userProfileImg == null || this.userProfileImg.isEmpty()) {
            this.userProfileImg = "./src/main/resources/static/img/기본_프로필.png"; // 기본 이미지 경로
        }
        
        if (this.mileageAmount == 0) {
            this.mileageAmount = 500; // 기본 마일리지 설정
        }
    }
	
}