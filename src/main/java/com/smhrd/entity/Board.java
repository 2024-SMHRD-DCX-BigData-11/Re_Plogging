package com.smhrd.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Board {
	
	// IDENTITY : auto_increment
	// insertable : insert문이 자동완성 될 때 해당 컬럼을 포함할지 말지 결정
	// updatable : update문이 자동완성 될 때 해당 컬럼을 set절에 포함할지 말지 결정
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )	// 값을 자동으로 생성
	@Column( insertable = false, updatable = false )	// 컬럼에 대한 상세 설정
	
	private Long idx;	// 글번호
	
	// nullable : null 값 허용할지 말지 결정
	@Column( nullable = false, length = 100 )
	private String title;	// 제목
	
	@Column( length = 1000 )
	private String content;		// 내용
	
	private String img;		// 이미지 파일 이름
	
	@Column( columnDefinition = "datetime default now()", insertable = false, updatable = false )
	private Date indate;	// 작성일
	
	@Column( columnDefinition = "int default 0", insertable = false )
	private Long count;		// 조회수
	
	@ManyToOne		// 다대일 관계임을 명시, 어떤 테이블을 참조하는지 자료형으로 명시 !
	private Member writer;	// 작성자
	
	// FK를 JPA를 구현 시 Lombok과 충돌나 오버플로우가 발생할 수 있음
	// 즉, 이를 해결하기 위해 직접 toString 메소드를 오버라이딩 해야 함
	public String toString() {
		return "BOARD 객체";
	}
}
