package com.smhrd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
	
	
	@Query("""
		    select u
		    from Member u
		    where u.userId = :userId
		    and u.userPw = :userPw
		    """)
	
		public Member findByUserIdAndUserPw(@Param("userId") String userId, 
	            @Param("userPw") String userPw);
	
	
	@Query("""
		    select u
		    from Member u
		    where u.userNick = :userNick
		    """)
	
		public Member findByUserNick(@Param("userNick") String userNick);
	
	
	@Query("""
		    select u
		    from Member u
		    where u.userId = :userId
		    """)
	
		public Member findByEmail(@Param("userId") String userId);
	
	
}
