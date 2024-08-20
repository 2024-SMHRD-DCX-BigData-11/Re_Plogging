package com.smhrd.repository;

import com.smhrd.entity.Plogging;
import com.smhrd.entity.TopPloggerDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PloggingListRepository extends JpaRepository<Plogging, Long> {

    @Query("SELECT new com.smhrd.entity.TopPloggerDTO(m.userNick, COUNT(p)) " +
           "FROM Plogging p JOIN p.user m " +
           "WHERE p.qr1 = 1 AND p.qr2 = 1 AND p.qr3 = 1 " +
           "GROUP BY m.userNick " +
           "ORDER BY COUNT(p) DESC")
    List<TopPloggerDTO> findTopPloggerWithJPQL(Pageable pageable);
}
