package com.smhrd.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Entity	// JPA한테 이 DTO 객체가 테이블과 관련있는 객체임을 알려주는 장치 => ORM(Object Relational Mapping)를 사용하기 위함
@Data
public class Member {
	
	// JPA
	// ORM(Object Relational Mapping)
	// java 객체 <---> 관계형 데이터
	// java 객체 생성하면, 그 객체에 맞춰 테이블을 자동 생성
	// 개발 초기에 테이블, DTO를 변경하기 쉬움
	// SQL문을 작성X >> but, 서버 시작 시간이 살~짝 길어짐
	
	
	@Id	// PrimaryKey를 걸기위한 어노테이션, 반드시 하나는 존재해야 함. (그렇지 않으면 error)
	private String email;
	
	private String pw;
	
	private String tel;
	
	private String address;
	
	@Column( columnDefinition = "varchar(50) default 'MEMber'", insertable = false, updatable = false )
	private String role;
	
	// 일대다 관계를 구현할 때, 새로운 변수를 선언해서 구현
	// mappedBy 속성으로 어떤 컬럼과 FK 관계를 갖는지를 명시해야 함
	@OneToMany( mappedBy = "writer" )
	private List<Board> board;
	
	// 한 사람의 회원 정보와, 그 사람이 작성한 게시글들의 정보
	/*
	 * select m.email, m.pw, m.tel, m.address, b.idx, b.title...
	 * from Member m, Board b
	 * where m.email = b.writer
	 * and m.email = :email
	 */
	
	// 회원 조회 시, 해당 회원이 작성한 게시글을 같이 조회 > board 변수에 담김
	// jackson databind 사용 시, JSON 변환이 제대로 이루어지지 않음
	
//	@OneToMany( mappedBy = "email" )
//	private List<Chat> chatList;
	
	// 오버플로우 방지를 위해 toString 직접 오버라이딩
	public String toString() {
		return "MEMBER 객체";
	}
}
