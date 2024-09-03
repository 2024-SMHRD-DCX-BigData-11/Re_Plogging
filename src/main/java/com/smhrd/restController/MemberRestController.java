package com.smhrd.restController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.smhrd.entity.CommonDomain;
import com.smhrd.entity.Member;
import com.smhrd.entity.Mileage;
import com.smhrd.repository.MemberRepository;
import com.smhrd.repository.MileageRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/member")
public class MemberRestController {

    @Autowired
    private MemberRepository repo;
    
    @Autowired
    private MileageRepository mileageRepo;
    
    @RequestMapping("/check")
    public String check(int userIdx) {
        Optional<Member> member = repo.findById(userIdx);
        boolean check = member.isEmpty();
        return check + "";
    }
    
    @RequestMapping("/testA")
    public CommonDomain testA(int userIdx) {
        CommonDomain response = new CommonDomain();
        Map<String, Object> resultMap = new HashMap<>();
        Optional<Member> member = repo.findById(userIdx);
        if (member.isPresent()) {
            resultMap.put("id", member.get().getUserId());
            resultMap.put("nickname", member.get().getUserNick());
            response.setResult(resultMap);
        }
        return response;
    }
    
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    @ResponseBody
    public CommonDomain userRegist(MultipartHttpServletRequest request) {
        CommonDomain response = new CommonDomain();
        
        String id = request.getParameter("userId").toString();
        String pass = request.getParameter("userPw").toString();
        String phone = request.getParameter("userPhone").toString();
        String nick = request.getParameter("userNick").toString();
        
        int resultCode = 0;
        
        Member member = repo.findByEmail(id);
        if (member == null) {
            member = repo.findByPhone(phone);
            if (member == null) {
                member = repo.findByUserNick(nick);
                if (member == null) {
                    member = new Member();
                    Mileage mileage = new Mileage();
                    
                    member.setUserId(id);
                    String joinEncryptedPw = DigestUtils.sha512Hex(pass);
                    member.setUserPw(joinEncryptedPw);
                    member.setUserPhone(phone);
                    member.setUserNick(nick);
                    
                    mileage.setMlAmount(300);
                    mileage.setUser(member);
                    mileage.setMlType("회원가입");
                    mileage.setMlLog("적립"); // mlLog 필드에 값 설정
                    
                    repo.save(member);
                    mileageRepo.save(mileage);
                    
                    resultCode = member.getUserIdx() != 0 ? 200 : -100;
                } else {
                    resultCode = -300; // 닉네임 중복
                }
            } else {
                resultCode = -400; // 전화번호 중복
            }
        } else {
            resultCode = -500; // 이메일 중복
        }
        
        response.setCode(resultCode);
        return response;
    }

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
