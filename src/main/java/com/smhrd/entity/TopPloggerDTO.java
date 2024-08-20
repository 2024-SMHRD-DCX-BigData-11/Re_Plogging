package com.smhrd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 이 어노테이션을 추가하여 생성자를 자동 생성합니다.
public class TopPloggerDTO {
    
    private String userNick;
    private Long ploggingCount;
}
