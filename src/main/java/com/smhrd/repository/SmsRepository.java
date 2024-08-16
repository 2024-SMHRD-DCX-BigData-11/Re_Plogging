package com.smhrd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smhrd.entity.Sms;

@Repository
public interface SmsRepository  extends JpaRepository<Sms, Integer> {
	
	@Query("""
		    select s
		    from Sms s
		    where s.smsPhone = :smsPhone
		    """)
	
	public Sms findByPhoneNumber( @Param("smsPhone") String smsPhone);
	
	
	@Query("""
		    select s
		    from Sms s
		    where s.smsCode = :smsCode
		    """)
	public Sms findByCode(@Param("smsCode") int smsCode);

}
