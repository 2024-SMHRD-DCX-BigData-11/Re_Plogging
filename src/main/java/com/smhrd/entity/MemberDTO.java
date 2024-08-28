package com.smhrd.entity;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  // 모든 필드를 포함하는 생성자 생성
@NoArgsConstructor   // 파라미터가 없는 기본 생성자 생성
public class MemberDTO {
    private int userIdx;
    private String userId; 
    private String userPhone;
    private String userNick;
    private int totalMileage;
    private int completedPloggingCount;
    private Timestamp joinedAt;  // joinedAt 필드 추가
}
