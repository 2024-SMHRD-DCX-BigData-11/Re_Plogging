package com.smhrd.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

import javax.sql.rowset.serial.SerialBlob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration;
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

import com.smhrd.FileUtils;
import com.smhrd.entity.Analysis;
import com.smhrd.entity.AnalysisDetail;
import com.smhrd.entity.Member;
import com.smhrd.entity.Mileage;
import com.smhrd.entity.RecycleCategory;
import com.smhrd.entity.UploadImg;
import com.smhrd.repository.AnalysisRepository;
import com.smhrd.repository.AnalysisDetailRepository;
import com.smhrd.repository.ImageRepository;
import com.smhrd.repository.MileageRepository;
import com.smhrd.repository.RecycleCategoryRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpSession;
import net.coobird.thumbnailator.Thumbnails;

@Controller
public class AiController {
	
    private static final Logger logger = LoggerFactory.getLogger(AiController.class); // 로거 추가

    @Autowired
    ImageRepository irepo;

    @Autowired
    AnalysisRepository arepo1;

    @Autowired
    AnalysisDetailRepository arepo2;
    
    @Autowired
    RecycleCategoryRepository categoryrepo;
    
    @Autowired
    MileageRepository mileagerepo;

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
    	String fileName = FileUtils.fileUpload(request, "file", "ai" );
    	
    	if( member != null ) {
    		UploadImg uploadimg = new UploadImg();
        	uploadimg.setFileName(fileName);
        	uploadimg.setUserIdx(member);

        	
        	irepo.save(uploadimg);
            logger.info("파일 정보 DB에 저장 완료");
        
            int useridx = member.getUserIdx();
            int fileidx = uploadimg.getFileIdx();
            logger.info("회원번호: {}, 파일번호: {}", useridx, fileidx);
         
         
//          3. 플라스크로 이동(쿼리스트링)
            return "redirect:http://127.0.0.1:5001?userIdx=" + useridx + "&fileIdx=" + fileidx;
    	} else {
    		return "redirect:/main";
    	}
}
    
    
    @GetMapping("/viewAnalysisImage")
    public String viewAnalysisImage(@RequestParam( value = "resultImageName", required = true) String resultImageName, @RequestParam( value = "anal_idx", required = true) int anal_idx,  Model model, HttpSession session) {
    	Member member = (Member) session.getAttribute("user");
    	// 분석 결과 이미지 프로젝트 내부 폴더에서 가져오기
         if (!resultImageName.equals("")) {
        	 model.addAttribute("resultImageName", resultImageName);
        	 
        	 
        	 List<AnalysisDetail> resultText =  arepo2.findByResultText(anal_idx);
        	 int totalMil = 0;
        	 
        	 for (int i = 0; i < resultText.size(); i++) {
				totalMil += resultText.get(i).getCategory().getCategoryMileage() * resultText.get(i).getCategoryCount();
				
				// 마일리지 적립
				Mileage mileage = new Mileage();
				mileage.setMlAmount(resultText.get(i).getCategory().getCategoryMileage() * resultText.get(i).getCategoryCount());
				mileage.setMlLog("적립");
				mileage.setMlType(resultText.get(i).getCategory().getCategoryName() + " 객체 인식");
				mileage.setUser(member);
				mileagerepo.save(mileage);
				
			}
        	 
        	 model.addAttribute("total", totalMil );
        	 model.addAttribute("list", resultText );
        	 model.addAttribute("anal_idx", anal_idx );
        	
        } else {
        	System.out.println("이미지 없음");
            model.addAttribute("resultImageName", null);
        }
        
        return "aiResult"; // aiResult.jsp로 이동
    }
    
    
    
}

