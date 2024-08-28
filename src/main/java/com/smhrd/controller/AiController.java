package com.smhrd.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;
import java.util.Base64;
import java.util.UUID;

import javax.sql.rowset.serial.SerialBlob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.smhrd.entity.Analysis;
import com.smhrd.entity.AnalysisDetail;
import com.smhrd.entity.Member;
import com.smhrd.entity.UploadImg;
import com.smhrd.repository.AnalysisRepository;
import com.smhrd.repository.AiTextRepository;
import com.smhrd.repository.ImageRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpSession;
import net.coobird.thumbnailator.Thumbnails;

@Controller
public class AiController {
	
	// Value 어노테이션으로 변수에 값을 채울 수 있음
	// ${프로퍼티 이름} 문법을 통해 application.properties에 정의한 데이터를 가져올 수 있음
	@Value("${save.path}")
	private String savePath;

    private static final Logger logger = LoggerFactory.getLogger(AiController.class); // 로거 추가

    @Autowired
    ImageRepository irepo;

    @Autowired
    AnalysisRepository arepo1;

    @Autowired
    AiTextRepository arepo2;

    @RequestMapping("/ai1")
    public String openAi1() {
        return "ai1";
    }

    @RequestMapping("/aiResult")
    public String openAiResult() {
        return "aiResult";
    }
    
    @PostMapping("/AiImageUpload")
    public String AiImageUpload(
    		MultipartHttpServletRequest request,
    		HttpSession session,
    		Model model) {

        Member member = (Member) session.getAttribute("user");
        UploadImg uploadimg = new UploadImg();
        if (member != null) {
            try {
                logger.info("파일 업로드 및 처리 시작");
                
                MultipartFile file = request.getFile("file");
                
                // 중복되지 않는 고유한 파일 이름 만들기
                String uuid = UUID.randomUUID().toString();
                String filenameo = file.getOriginalFilename();
                String filename = uuid + filenameo;

                // 파일 이름과 사용자 정보를 DTO에 저장
                uploadimg.setFileName(filename);
                uploadimg.setUserIdx(member);
                uploadimg.setFileSize(file.getSize());
                Path path = Paths.get("savePath" + filename);
                file.transferTo(path);
                
                String fileExt = "NoExt";
                if( file != null ) {
                	fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                }

                uploadimg.setFileExt(fileExt);

                logger.info("파일 정보 저장: {}", fileExt );

            } catch (Exception e) {
                logger.error("파일 처리 중 오류 발생", e);
                e.printStackTrace();
            }

            // 2. 기능 실행
            irepo.save(uploadimg);
            logger.info("파일 정보 DB에 저장 완료");

            // 회원번호 변수에 담기(쿼리스트링용)
            int useridx = member.getUserIdx();
            int fileidx = uploadimg.getFileIdx();
            logger.info("회원번호: {}, 파일번호: {}", useridx, fileidx);
            
            
            // 3. 플라스크로 이동(쿼리스트링)
            return "redirect:http://127.0.0.1:5001?userIdx=" + useridx + "&fileIdx=" + fileidx;
        } else {
        	
        	// 로그인 상태x
            return "redirect:/main";
        }
    }
    
//    @GetMapping("/viewAnalysisImage")
//    public String viewAnalysisImage(@RequestParam(value = "file_idx", required = true) int fileIdx, Model model) {
//    	
//    	System.out.println(fileIdx);
//    	byte[] resultImage = arepo1.findResultImgByFileIdx(fileIdx);
//
//    	if( resultImage != null ) {
//    		
//    		model.addAttribute("imageData", resultImage);
//    		
//        return "aiResult"; // aiResult.jsp 파일 반환
//    }
//    	System.out.println("이미지 데이터 없음");
//    	return "main";
//}
}
