package com.smhrd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	
	// JpaRepository 인터페이스를 상속 받음.
	// <Table과 연결된 Entity, ID(PK)가 붙은 필드(컬럼)의 자료형>
	
	// 1. JpaRepository 인터페이스에 기본 CRUD 메소드가 미리 정의되어 있음
	// hibernate라는 엔진이, 인터페이스를 기반으로 완성된 DAO를 자동으로 만들어냄.
	// .save(Entity S)
	// pk기준으로 select 실행 ➡️ 만약 없다면, insert문 실행
	//					  ➡️ 만약 있다면, update문 실행
	
	// 로그인 >> select * from MEMBER where email = ? and pw = ?
	// 2. 특정 SQL문이 실행되도록, 메소드를 선언
	// 메소드 이름에 주의
	// - 카멜기법 : 단어가 바뀔 때, 대문자로 작성)
	// - By... 을 이용해 조건(where과 같은 기능)을 걸고, 조건부에 넣기 위한 데이터를 매개변수로 넣어줘야 함.(JPA가 알아서 찾아줌)
	// https://kim-oriental.tistory.com/34 (필요할 때 참고)
	public Member findByEmailAndPw( String email, String pw );
	
	
	// 3. 직접 SQL문을 작성해 적용시키는 방법
	// 테이블 명에 alias를 줄 것
	// 대신 테이블의 alias를 적어서 모든 컬럼을 조회
	// 컬럼을 명시할 때에도, alias를 붙여서 명시!
	// 테이블 명은 첫 글자만 대문자로 함, :변수명 을 통해 어떤 변수값이 어디에 들어갈지 명시
	// 가급적 만들어 낼 수 없는 쿼리문일 경우에만 사용하도록 !
	
	@Query("""
			select m
			from Member m
			where m.email = :email
			and m.pw = :pw
			""")
	
	// @Query 이용해 독스트링으로 직접 Sql문 선언 ! (모두 소문자여야 함. 단, 테이블 이름만 첫 글자는 대문자)
	// alias.컬럼명 = :email(바인드 변수(아래의 public ~ String email 필요 !)
	public Member login( String email, String pw );
	
	// 응답 처리 속도가 느려질 수 있음. 자동완성으로 쿼리를 만들어 내다 보니, 쿼리가 효율적이지 않을 수 있음
	
}
