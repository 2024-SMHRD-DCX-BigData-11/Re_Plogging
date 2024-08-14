package com.smhrd.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_sms") // 테이블 이름을 매핑
public class Sms {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sms_idx", insertable = false, updatable = false) 
	private int smsIdx; // 인증번호 인덱스 (mk_idx)
	
	
	@Column(name = "sms_phone", updatable = false) 
	private String smsPhone; // 인증번호 받을 전화번호
	
	@Column(name = "sms_code") 
	private int smsCode; // 생성된 인증번호

}
