package com.smhrd.restController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.entity.AnalysisDetail;
import com.smhrd.entity.Comment;
import com.smhrd.entity.CommonDomain;
import com.smhrd.repository.AnalysisDetailRepository;
import com.smhrd.repository.ChatRepository;

@RestController
@RequestMapping( "/rest/char/" )
public class ChatRestController {
   
   @Autowired
   ChatRepository repo;
   
   @Autowired
   AnalysisDetailRepository arepo2;
   
   @RequestMapping( value = "createChart", method = RequestMethod.POST )
   @ResponseBody
   public Map<String, Object> createChart(
		   @RequestParam( value ="idx" ) int idx
		   ) {
	   Map<String, Object> result = new HashMap<String, Object>();
	   List<AnalysisDetail> resultText =  arepo2.findByResultText(idx);
	   for (Iterator iterator = resultText.iterator(); iterator.hasNext();) {
		AnalysisDetail detail = (AnalysisDetail) iterator.next();
		detail.setAnalysis( null );
	}
	   
	   
	   result.put("code", resultText.size() != 0 ? 200 : -100  );
	   result.put("result", resultText );
	   return result;
   }
   
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
