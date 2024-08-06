package com.smhrd.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.entity.Board;
import com.smhrd.repository.BoardRepository;

@Controller
public class BoardController {
   
   @Autowired
   private BoardRepository repo;
   
   // Value 어노테이션으로 변수에 값을 채울 수 있음
   // ${프로퍼티 이름} 문법을 통해 application.properties에 정의한 데이터를 가져올 수 있음
   @Value("${save.path}")
   private String savePath;
   
   @RequestMapping("/list")
   public String list(Model model) {
      
      // 1. 데이터 수집
      // 2. 기능 실행
      List<Board> list =  repo.findAll();
      
      model.addAttribute("list", list);
      // 3. View 선택
      return "boardMain";
      
   }
   
   @GetMapping("/write")
   public String write() {
      return "writerBoard";
   }
   
   @PostMapping("/write")
   public String write( Board board, MultipartFile file ) {
      // 1. 데이터 수집
      // Board writer 역시 잘 수집이 진행된다.
      // 수집과정에서 Member의 가장 상단에 선언된 변수에 자동으로 넣어주기 때문에
      // PK(ID)에 해당하는 컬럼을 가장 상단에 선언
	   
	  // 파일 수집하기
	  // 1) 파일을 저장할 수 있는 폴더 생성(가급적 경로에 한글 들어가면X)
	  // 2) 저장할 폴더의 경로를 저장
	  // 3) 사용자가 보낸 파일 수집 > MultipartFile 자료형을 가진 변수 선언 ( 여러 개면 배열[] 로 )
	  try {
		  // 4) 중복되지 않는 고유한 파일 이름 만들기
		  // 랜덤한 문자열 생성
		  String uuid = UUID.randomUUID().toString();
		  // 파일 이름에 랜덤 문자열 붙이기
		  String filename = uuid + file.getOriginalFilename();
		  
		  // 5) 경로부터 파일명까지 모두 포함하는 객체 생성
		  Path path = Paths.get( savePath + filename );
		  
		  // 6) MultipartFile ➡️ 해당 경로, 파일명을 이용해 실제 파일로 변환
		  file.transferTo(path);
		  // 7) 저장한 파일의 이름을 DTO에 담기
		  board.setImg(filename);	// savePath 집어넣X
		  
		  
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	  
      
      // 2. 기능 실행
      repo.save(board);
      // 3. View 선택
      return "redirect:/list";
   }
   
   @RequestMapping("/view")
   public String view( Long idx, Model model ) {
	   // 1. 데이터 수집
	   // 2. 기능실행
	   Optional<Board> board = repo.findById(idx);
	   
	   if ( board.isPresent() ) {
		   model.addAttribute("board", board.get());
		   return "viewBoard";
	   } else {
		   return "redirect:/list";
	   }
	   // 3. View 선택
   }
   
   @RequestMapping("/delete")
   public String delete( Long idx ) {
	   // 1. 데이터 수집
	   // 2. 기능실행
	   repo.deleteById(idx);
	   // 3. View 선택
	   return "redirect:/list";
   }

}
