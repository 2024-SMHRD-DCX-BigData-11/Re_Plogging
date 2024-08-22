package com.smhrd.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx", columnDefinition = "int", insertable = false, updatable = false)
    private int userIdx;

    @Column(name = "user_id", length = 50, nullable = false)
    private String userId;

    @Column(name = "user_pw", length = 128, nullable = false)
    private String userPw;

    @Column(name = "user_phone", length = 20, nullable = false)
    private String userPhone;

    @Column(name = "user_nick", length = 30, nullable = false)
    private String userNick;

    @Lob
    @Column(name = "user_profile_img", columnDefinition="MEDIUMBLOB", nullable = true)
    private byte[] userProfileImg;

    @Column(name = "joined_at", columnDefinition = "datetime default now()", insertable = false, updatable = false)
    private Timestamp joinedAt;

    @Column(name = "mileage_amount", columnDefinition = "integer default 0")
    private int mileageAmount;
    
    @Column(name = "plogging_count", columnDefinition = "integer default 0")
    private int ploggingCount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Plogging> ploggings;

    @PrePersist
    public void prePersist() {
        if (this.userProfileImg == null || this.userProfileImg.length == 0) {
            try {
                this.userProfileImg = Files.readAllBytes(Paths.get("./src/main/resources/static/img/기본_프로필.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if (this.mileageAmount == 0) {
            this.mileageAmount = 500;
        }
    }
}
