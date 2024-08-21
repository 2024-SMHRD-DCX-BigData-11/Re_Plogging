package com.smhrd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Member;
import com.smhrd.entity.Plogging;

@Repository
public interface PloggingRepository extends JpaRepository<Plogging, Integer> {
	// 가장 최근에 시작된 플로깅을 가져오기 위한 메서드
	Optional<Plogging> findTopByUserOrderByStartedAtDesc(Member user);

	// 모든 QR 코드가 완료된 플로깅을 가져오기 위한 메서드
	List<Plogging> findByUserAndQr1AndQr2AndQr3(Member user, int qr1, int qr2, int qr3);

	// 특정 사용자가 모든 QR 체크포인트를 완료한 플로깅의 개수를 반환하는 쿼리
	@Query("SELECT COUNT(p) FROM Plogging p WHERE p.user.userIdx = :userIdx AND p.qr1 = 1 AND p.qr2 = 1 AND p.qr3 = 1")
	int countCompletedPlogging(@Param("userIdx") int userIdx);
	
	@Modifying(clearAutomatically = true)
	@Query("""
		    UPDATE Plogging p
			SET p.qr1 = 1, qr1Time = NOW()
			WHERE p.user.userIdx = :userIdx AND p.courseName = :courseName
		    """
			)
	public int UpdateQr1(@Param("userIdx") int userIdx, @Param("courseName") String courseName);
	
	
	@Modifying(clearAutomatically = true)
	@Query("""
		    UPDATE Plogging p
			SET p.qr2 = 1, qr2Time = NOW()
			WHERE p.user.userIdx = :userIdx AND p.courseName = :courseName
		    """
			)
	public int UpdateQr2(@Param("userIdx") int userIdx, @Param("courseName") String courseName);
	
	
	@Modifying(clearAutomatically = true)
	@Query("""
		    UPDATE Plogging p
			SET p.qr3 = 1, qr3Time = NOW()
			WHERE p.user.userIdx = :userIdx AND p.courseName = :courseName
		    """
			)
	public int UpdateQr3(@Param("userIdx") int userIdx, @Param("courseName") String courseName);
	
	@Query("""
		    select p
			from Plogging p
			WHERE p.user.userIdx = :userIdx AND p.courseName = :courseName AND qr1=1 AND qr2=0 AND qr3=0
		    """
			)
	public Plogging checkQr1(@Param("userIdx") int userIdx, @Param("courseName") String courseName);
	
	@Query("""
		    select p
			from Plogging p
			WHERE p.user.userIdx = :userIdx AND p.courseName = :courseName AND qr1=1 AND qr2=1 AND qr3=0
		    """
			)
	public Plogging checkQr2(@Param("userIdx") int userIdx, @Param("courseName") String courseName);

}