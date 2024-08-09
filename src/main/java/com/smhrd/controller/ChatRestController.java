package com.smhrd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.entity.Comment;
import com.smhrd.repository.ChatRepository;

@RestController
public class ChatRestController {
   
   @Autowired
   public ChatRepository repo;
   
   @RequestMapping("/load")
   public List<Comment> load() {
      // 1. 데이터 수집
      // 2. 기능실행
      List<Comment> list = repo.findAll();
      // 3. 데이터 응답
      // jackson databind 사용 시, @OnToMany가 붙은 변수 때문에 문제가 되기 때문에
      // JSON으로 변환이 제대로 이루어지지 않음
      // 즉, [ 문제가 되고있는 변수를 주석처리 or 데이터를 DTO에 옮겨 담고 리턴 ]하는 방법 선택
      
      return list;
      
      
   }

}
