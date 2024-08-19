package com.smhrd.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.validation.constraints.Size;
import java.sql.Timestamp;


@Entity	// JPA한테 이 DTO 객체가 테이블과 관련있는 객체임을 알려주는 장치 => ORM(Object Relational Mapping)를 사용하기 위함
@Data
@Table(name = "tb_recycle")
public class Recycle {
	
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
	@Column(name = "recycle_idx", columnDefinition = "int", insertable = false, updatable = false)
	private int recycleIdx;
	
	@Column(name = "recycle_target", length = 100)
	private String recycleTarget;
	
	@Column(name = "recycle_status", length = 10)
	private String recycleStatus;
	
	@Column(name = "recycle_category", length = 255)
	private String recycleCategory;
	
	@Column(name = "recycle_method", columnDefinition = "TEXT")
	private String recycleMethod;
	
	@Column(name = "recycle_video", columnDefinition = "LONGBLOB")
	private byte[] recycleVideo;

}

