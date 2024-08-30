package com.smhrd.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class CommonDomain {
	private int code;
	private Object result;
}
