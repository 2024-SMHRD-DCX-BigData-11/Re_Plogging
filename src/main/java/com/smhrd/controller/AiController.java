package com.smhrd.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.entity.Analysis;
import com.smhrd.entity.Member;
import com.smhrd.entity.UploadImg;
import com.smhrd.repository.ImageRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class AiController {
	
	@Autowired
	ImageRepository irepo;
	
	@RequestMapping("/ai1")
	public String openAi1() {
		return "ai1";
	}
	
	@PostMapping("/AiImageUpload")
	public String AiImageUpload(UploadImg uploadimg, MultipartFile file, HttpSession session) {
		
		Member member = (Member)session.getAttribute("user");
		
		if(member != null) {
	
			// 파일 수집하기
			// 사용자가 보낸 파일 수집 > MultipartFile 자료형을 가진 변수 선언
			try {
				
				// 중복되지않는 고유한 파일 이름 만들기
				// 랜덤한 문자열 생성
				String uuid = UUID.randomUUID().toString();
				
				// 파일 이름 원본 변수에 저장
				String filenameo = file.getOriginalFilename();
				
				// 파일 이름 원본에 랜덤 문자열 붙이기
				String filename = uuid + filenameo;
				
				// 저장한 파일의 이름을 DTO에 담기
				uploadimg.setFileName(filename);
				
				// 사용자 idx DTO에 담기
				uploadimg.setUserIdx(member);
				
				// 파일을 byte[]로 변환
				byte[] fileBytes = file.getBytes();

				// DTO에 byte[] 저장
				uploadimg.setImageData(fileBytes); // byte[]로 파일 데이터를 저장
				
				// 저장한 파일의 사이즈 DTO에 담기
				uploadimg.setFileSize(file.getSize());
				
				
				if (filenameo != null && filenameo.contains(".")) {
				    String fileExt = filenameo.substring(filenameo.lastIndexOf(".") + 1);
				    uploadimg.setFileExt(fileExt);
				} else {
				    // 확장자가 없을 때
					 uploadimg.setFileExt("NoExt");
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 2. 기능 실행
			irepo.save(uploadimg);
			
			// 회원번호 변수에 담기(쿼리스트링용)
			int useridx = member.getUserIdx();
			
			// 파일번호 변수에 담기(쿼리스트링용)
			int fileidx = uploadimg.getFileIdx();
			
			// 3. 플라스크로 이동(쿼리스트링)
			return "redirect:http://127.0.0.1:5001?userIdx=" + useridx +"&fileIdx=" + fileidx;
	}else {
			// 로그인 상태가 이니면 메인 페이지로 이동
			return "redirect:/main";
		}
	}
}
