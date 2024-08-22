package com.smhrd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smhrd.entity.Member;
import com.smhrd.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public byte[] getUserProfileImg(int userIdx) {
        return memberRepository.findUserProfileImgByUserIdx(userIdx);
    }

    @Transactional
    public void updateUserProfileImg(int userIdx, byte[] newImage) {
        Member member = memberRepository.findById(userIdx).orElseThrow(() -> new RuntimeException("User not found"));
        member.setUserProfileImg(newImage);
        memberRepository.save(member);
    }
}
