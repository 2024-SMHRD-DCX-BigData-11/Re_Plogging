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
	    
	    int resultCode = 0;
	    
	    // 세션에서 현재 로그인된 사용자 정보를 가져옴
	    Member user = (Member) session.getAttribute("user");
	    
	    String currentMpw = request.getParameter("currentMpw");
	    String nconfirmMpw = request.getParameter("nconfirmMpw");
	    String MuserNick = request.getParameter("MuserNick");
	    
	    if(user != null) { // 로그인 세션이 있으면
	        
	        // 비밀번호 확인
	        if (currentMpw != null && DigestUtils.sha512Hex(currentMpw).equals(user.getUserPw())) {
	            
	            boolean isUpdated = false;
	            
	            // 새 비밀번호가 입력된 경우 비밀번호 변경
	            if(nconfirmMpw != null && !nconfirmMpw.isEmpty()) {
	                user.setUserPw(DigestUtils.sha512Hex(nconfirmMpw));
	                isUpdated = true;
	            }
	            
	            // 닉네임이 입력된 경우 닉네임 변경
	            if(MuserNick != null && !MuserNick.isEmpty()) {
	                // 닉네임 중복 확인
	                Member nickCheck = repo.findByUserNick(MuserNick);
	                if(nickCheck == null) {
	                    user.setUserNick(MuserNick);
	                    isUpdated = true;
	                } else {
	                    resultCode = -400; // 닉네임 중복
	                }
	            }
	            
	            if (isUpdated) {
	                // 데이터베이스에 업데이트
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