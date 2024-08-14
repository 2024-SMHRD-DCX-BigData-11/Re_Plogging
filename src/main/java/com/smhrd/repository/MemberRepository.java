package com.smhrd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
	
	
	@Query("""
		    select u
		    from Member u
		    where u.userId = :userId
		    and u.userPw = :userPw
		    """)
	
		public Member findByUserIdAndUserPw(String userId, String userPw);
	
	
//	public Member getMemberWithIdx(int userIdx);
	
	
}
