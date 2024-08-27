package com.smhrd.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.entity.Analysis;
import com.smhrd.entity.AnalysisDetail;
import com.smhrd.entity.Member;
import com.smhrd.entity.UploadImg;
import com.smhrd.repository.AiImgRepository;
import com.smhrd.repository.AiTextRepository;
import com.smhrd.repository.ImageRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AiController {

    private static final Logger logger = LoggerFactory.getLogger(AiController.class); // 로거 추가

    @Autowired
    ImageRepository irepo;

    @Autowired
    AiImgRepository arepo1;

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
    public String AiImageUpload(UploadImg uploadimg, MultipartFile file, HttpSession session) {

        Member member = (Member) session.getAttribute("user");

        if (member != null) {

            try {
                logger.info("파일 업로드 및 처리 시작");

                // 중복되지 않는 고유한 파일 이름 만들기
                String uuid = UUID.randomUUID().toString();
                String filenameo = file.getOriginalFilename();
                String filename = uuid + filenameo;

                // 파일 이름과 사용자 정보를 DTO에 저장
                uploadimg.setFileName(filename);
                uploadimg.setUserIdx(member);
                uploadimg.setImageData(file.getBytes());
                uploadimg.setFileSize(file.getSize());

                if (filenameo != null && filenameo.contains(".")) {
                    String fileExt = filenameo.substring(filenameo.lastIndexOf(".") + 1);
                    uploadimg.setFileExt(fileExt);
                } else {
                    uploadimg.setFileExt("NoExt");
                }

                logger.info("파일 정보 저장: {}", filename);

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
            int notuser = 0000;
            int fileidx = uploadimg.getFileIdx();
            logger.warn("비회원 사용자 접근: userIdx={}, fileIdx={}", notuser, fileidx);
            return "redirect:http://127.0.0.1:5001?userIdx=" + notuser + "&fileIdx=" + fileidx;
        }
    }

    @RequestMapping("/aiResultList")
    public String openAiResultList(@RequestParam("file_idx") int fileIdx, @RequestParam("anal_idx") int analIdx, Model model) {

        logger.info("aiResultList 메서드 호출: fileIdx={}, analIdx={}", fileIdx, analIdx);

        Optional<byte[]> resultImageOptional = arepo1.findByResultImage(fileIdx, analIdx);

        if (resultImageOptional.isPresent()) {
            byte[] resultImage = resultImageOptional.get();
            String base64Image = Base64.encodeBase64String(resultImage);
            model.addAttribute("imageData", base64Image);
            logger.info("이미지 데이터가 성공적으로 조회되었습니다.");
        } else {
            model.addAttribute("imageData", null);
            logger.warn("해당 fileIdx 및 analIdx에 대한 이미지 데이터를 찾을 수 없습니다.");
        }

        List<AnalysisDetail> resultText = arepo2.findByResultText(analIdx);
        if (resultText != null && !resultText.isEmpty()) {
            model.addAttribute("resultText", resultText);
            logger.info("객체 인식 결과가 성공적으로 조회되었습니다.");
        } else {
            model.addAttribute("resultText", null);
            logger.warn("해당 analIdx에 대한 객체 인식 결과를 찾을 수 없습니다.");
        }

        return "aiResult";
    }
}
