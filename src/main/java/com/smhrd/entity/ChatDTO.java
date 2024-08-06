package com.smhrd.entity;

import java.util.Date;

import lombok.Data;

@Data
public class ChatDTO {
	private Long idx;
	
	private String message;
	
	private Date indate;
	
	private String email;
}
