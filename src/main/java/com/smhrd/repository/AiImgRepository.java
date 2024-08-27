package com.smhrd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smhrd.entity.Analysis;


public interface AiImgRepository extends JpaRepository<Analysis, Long> {
	
	@Query("""
            select a.analResultImg
            from Analysis a
            where a.fileIdx = :fileIdx and a.analIdx = :analIdx 
            """)
	public Optional<byte[]> findByResultImage(@Param("fileIdx") int fileIdx, @Param("analIdx") int analIdx);

}
