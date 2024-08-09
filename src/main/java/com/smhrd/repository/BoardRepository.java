package com.smhrd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	// Long으로 선언한 이유 : 제너릭 안에는 참조 자료형이 들어감
	// 기본 자료형에 대응하는 참조 자료형이 존재함
	// int <---> Integer
	// log <---> Long
	
	// writer를 조건으로 게시물 조회
	// FK로 참조가 걸려있는 컬럼을 조건으로 조회 시 컬럼명_참조하는 컬럼명 형식으로 적어야 함
	// >> 컬럼(필드) 이름 작성 시에도 카멜 기법 사용s
	
}
