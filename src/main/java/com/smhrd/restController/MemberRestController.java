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

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/member/")
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
                    member.setMileageAmount(500);
                    
                    mileage.setMlAmount(500);
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
    
    @RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
    @ResponseBody
    public CommonDomain memberUpdate(MultipartHttpServletRequest request, HttpSession session) {
        CommonDomain response = new CommonDomain();
        int resultCode = 0;
        
        Member user = (Member) session.getAttribute("user");
        
        String currentMpw = request.getParameter("currentMpw");
        String nconfirmMpw = request.getParameter("nconfirmMpw");
        String MuserNick = request.getParameter("MuserNick");
        
        if (user != null) {
            if (currentMpw != null && DigestUtils.sha512Hex(currentMpw).equals(user.getUserPw())) {
                boolean isUpdated = false;
                
                if (nconfirmMpw != null && !nconfirmMpw.isEmpty()) {
                    user.setUserPw(DigestUtils.sha512Hex(nconfirmMpw));
                    isUpdated = true;
                }
                
                if (MuserNick != null && !MuserNick.isEmpty()) {
                    Member nickCheck = repo.findByUserNick(MuserNick);
                    if (nickCheck == null) {
                        user.setUserNick(MuserNick);
                        isUpdated = true;
                    } else {
                        resultCode = -400; // 닉네임 중복
                    }
                }
                
                if (isUpdated) {
                    repo.save(user);
                } else if (resultCode == 0) {
                    resultCode = -300; // 변경된 사항 없음
                }
            } else {
                resultCode = -500; // 비밀번호 불일치
            }
        } else {
            resultCode = -600; // 로그인 세션 없음
        }
        
        response.setCode(resultCode);
        return response;
    }
}
