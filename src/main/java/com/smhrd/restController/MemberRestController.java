package com.smhrd.restController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.smhrd.entity.CommonDomain;
import com.smhrd.entity.Member;
import com.smhrd.repository.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping( "/rest/member/" )
public class MemberRestController {
	
	@Autowired
	private MemberRepository repo;
	
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
			member = repo.findByUserNick(nick);
			if(member == null) {
				member = new Member();
				member.setUserId(id);
				member.setUserPw(pass);
				member.setUserPhone(phone);
				member.setUserNick(nick);
				//insert 
				repo.save(member);
				resultCode = member.getUserIdx() != 0 ? 200 : -100;
			} else {
				resultCode = -400;
			}
		} else {
			resultCode = -500;
		}
		response.setCode( resultCode );
		return response;
	}
	
}