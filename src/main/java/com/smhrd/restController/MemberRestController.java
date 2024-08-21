package com.smhrd.restController;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
@RequestMapping( "/rest/member/" )
public class MemberRestController {
	
	@Autowired
	private MemberRepository repo;
	
	@Autowired
	private MileageRepository mileageRepo;
	
	@RequestMapping("/check")
	public String check( int userIdx ) {
		// 1. 데이터 수집
		// 2. 기능 실행(email을 기준으로 찾는 메서드)
		Optional<Member> member = repo.findById(userIdx);
		// Optional : 특별한 기능은 없음 ! 포장지 생각. why 감싸 ? => null 값을 처리하기 용이하게 하기 위한 객체임
		
		boolean check = member.isEmpty();	// 비어있니?
		// existsById를 이용해 체크할수도 있음
		
		
		// 3. 데이터 응답
		return check + "";
	}
	
	

	@RequestMapping("/testA")
	public CommonDomain testA( int userIdx ) {
		
		CommonDomain response = new CommonDomain();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Optional<Member> member = repo.findById(userIdx);
		if( member != null ) {
			resultMap.put("id", member.get().getUserId() );
			resultMap.put("nickname", member.get().getUserNick() );
			
			response.setResult( resultMap );
		} 
		return response;
	}
	
	@RequestMapping( value ="/join", method = RequestMethod.POST )
	@ResponseBody
	public CommonDomain userRegist(
			MultipartHttpServletRequest request
			) {
		CommonDomain response = new CommonDomain();
		
		String id = request.getParameter("userId").toString();
		String pass = request.getParameter("userPw").toString();
		String phone = request.getParameter("userPhone").toString();
		String nick = request.getParameter( "userNick" ).toString();
		
		int resultCode = 0;
		//유저 아이디 비교
		Member member = repo.findByEmail( id );
		if( member == null ) {
			member = repo.findByPhone(phone);
			if(member == null) {
				member = repo.findByUserNick(nick);
				if(member == null) {
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
					
					//insert 
					repo.save(member);
					mileageRepo.save(mileage);
					
					resultCode = member.getUserIdx() != 0 ? 200 : -100;
				} else {
					resultCode = -300;
				}
				
			} else {
				resultCode = -400;
			}
		} else {
			resultCode = -500;
		}
		response.setCode( resultCode );
		
		
		return response;
	}
	
	@RequestMapping( value ="/memberUpdate", method = RequestMethod.POST )
	@ResponseBody
	public CommonDomain memberUpdate(
			MultipartHttpServletRequest request, HttpSession session
			) {
		
		CommonDomain response = new CommonDomain();
		
		String currentMpw = request.getParameter("currentMpw").toString();
		String nconfirmMpw = request.getParameter("nconfirmMpw").toString();
		String MuserNick = request.getParameter( "MuserNick" ).toString();
		
		int resultCode = 0;
		
		// 세션에서 현재 로그인된 사용자 정보를 가져옴
	    Member user = (Member) session.getAttribute("user");

	    if (user != null) {
	        // 비밀번호 확인
	        if (DigestUtils.sha512Hex(currentMpw).equals(user.getUserPw())) {
	            // 닉네임 중복 확인
	            Member nickCheck = repo.findByUserNick(MuserNick);
	            if (nickCheck == null || nickCheck.getUserId().equals(user.getUserId())) {
	                // 기존 사용자 정보 업데이트
	                user.setUserNick(MuserNick);

	                // 비밀번호 변경
	                user.setUserPw(DigestUtils.sha512Hex(nconfirmMpw));

	                // 데이터베이스에 저장
	                repo.save(user);

	                resultCode = 0; // 성공 코드
	            } else {
	                resultCode = -400; // 닉네임 중복
	            }
	        } else {
	            resultCode = -500; // 비밀번호 불일치
	        }
	    } else {
	        resultCode = -600; // 세션에 사용자 정보 없음
	    }

	    response.setCode(resultCode);
	    return response;
		

	} 
}