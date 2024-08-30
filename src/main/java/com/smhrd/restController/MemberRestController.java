package com.smhrd.restController;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.entity.CommonDomain;
import com.smhrd.entity.Member;
import com.smhrd.repository.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/member")
public class MemberRestController {

    @Autowired
    private MemberRepository repo;

    // 닉네임 중복 체크 및 변경
    @RequestMapping(value = "/updateNickname", method = RequestMethod.POST)
    @ResponseBody
    public CommonDomain updateNickname(HttpServletRequest request, HttpSession session) {
        CommonDomain response = new CommonDomain();
        int resultCode = 0;

        try {
            Member user = (Member) session.getAttribute("user");
            String MuserNick = request.getParameter("MuserNick");

            if (user != null) {
                if (MuserNick != null && !MuserNick.isEmpty()) {
                    Member nickCheck = repo.findByUserNick(MuserNick);
                    if (nickCheck == null) {
                        user.setUserNick(MuserNick);
                        repo.save(user);
                        resultCode = 0; // 성공적으로 닉네임이 변경됨
                    } else {
                        resultCode = -400; // 닉네임 중복
                    }
                } else {
                    resultCode = -300; // 닉네임이 비어있음
                }
            } else {
                resultCode = -600; // 로그인 세션 없음
            }
        } catch (Exception e) {
            e.printStackTrace(); // 서버 로그에 예외를 출력
            resultCode = -700; // 예외 발생 시의 에러 코드
        }

        response.setCode(resultCode);
        return response;
    }

    // 비밀번호 변경
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonDomain updatePassword(HttpServletRequest request, HttpSession session) {
        CommonDomain response = new CommonDomain();
        int resultCode = 0;

        try {
            Member user = (Member) session.getAttribute("user");

            // 비밀번호 파라미터 가져오기
            String currentMpw = request.getParameter("currentMpw");
            String nconfirmMpw = request.getParameter("nconfirmMpw");

            // 사용자 세션 확인
            if (user != null) {
                // 현재 비밀번호 일치 여부 확인
                if (currentMpw != null && DigestUtils.sha512Hex(currentMpw).equals(user.getUserPw())) {
                    // 새 비밀번호가 존재하는지 확인
                    if (nconfirmMpw != null && !nconfirmMpw.isEmpty()) {
                        user.setUserPw(DigestUtils.sha512Hex(nconfirmMpw));
                        repo.save(user);
                        resultCode = 0; // 성공적으로 비밀번호가 변경됨
                    } else {
                        resultCode = -300; // 새 비밀번호가 비어있음
                    }
                } else {
                    resultCode = -500; // 현재 비밀번호 불일치
                }
            } else {
                resultCode = -600; // 로그인 세션 없음
            }
        } catch (Exception e) {
            e.printStackTrace(); // 서버 로그에 예외를 출력
            resultCode = -700; // 예외 발생 시의 에러 코드
        }

        response.setCode(resultCode);
        return response;
    }
}
