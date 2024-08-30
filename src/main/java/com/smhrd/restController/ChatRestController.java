package com.smhrd.restController;

import java.util.ArrayList;
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
import com.smhrd.entity.RecycleCategory;
import com.smhrd.repository.AnalysisDetailRepository;
import com.smhrd.repository.ChatRepository;
import com.smhrd.repository.RecycleCategoryRepository;

@RestController
@RequestMapping( "/rest/char/" )
public class ChatRestController {
   
   @Autowired
   ChatRepository repo;
   
   @Autowired
   AnalysisDetailRepository arepo2;
   
   @Autowired
   RecycleCategoryRepository recyclerepo;
   
   @RequestMapping( value = "createChart", method = RequestMethod.POST )
   @ResponseBody
   public Map<String, Object> createChart(
		   @RequestParam( value ="idx" ) int idx
		   ) {
	   List<RecycleCategory> categoryList = recyclerepo.findCategory(); 
	   List<AnalysisDetail> detailList = arepo2.findByResultText(idx);
	   
	   List<String> xValue = new ArrayList<String>();
	   List<String> yValue = new ArrayList<String>();
	   
	   for (int i = 0; i < categoryList.size(); i++) {
		   String target = "";
		   xValue.add( categoryList.get(i).getCategoryName() );
		   for (int j = 0; j < detailList.size(); j++) {
			   if ( categoryList.get(i).getCategoryName().equals( detailList.get(j).getCategory().getCategoryName() ) ) {
				    target = String.valueOf( detailList.get(j).getCategoryCount() );
			   } 
		   }
		   yValue.add( target.equals("") ? "0" : target );
	   }
	 
	   Map<String, Object> result = new HashMap<String, Object>();
	   result.put("xValue", xValue );
	   result.put("yValue", yValue );
	   
	   
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

   @RequestMapping( value = "getUrlList", method = RequestMethod.POST )
   @ResponseBody
   public Map<String, Object> getUrlList(
		   @RequestParam( value ="idx" ) int idx
		   ) {
	   List<RecycleCategory> categoryList = recyclerepo.findCategory(); 
	   List<AnalysisDetail> detailList = arepo2.findByResultText(idx);
	   
	   List<String> targetTitleList = new ArrayList<String>();
	   List<String> targetUrlList = new ArrayList<String>();
	   
	   String[] targetNameArr =  { "종이", "캔", "유리", "페트", "플라스틱", "비닐", "스티로폼", "건전지" };
	   String[] targetUrl = { "urE4nzXFOLE?si=o35GOHZY-h7CtGN2", "DsgUFESLWKw?si=MuQEwH6e0RIDp7uj",
			   "gEeCPIe1JEE?si=JahZgVDseRrlUM6E",
			        "UJdK0h4NQWg?si=RPE-gOSQ3Gln98Kk",
			        "hyXggCmXI7I?si=ikO6BOJ8jQGlMl-l",
			        "vrNgvgTtowo?si=izg5GkCYXogMsAXT",
			        "Fe3R9PWuI_0?si=ZVQ_Oxcke2B0wOcy",
			        "Q1H0Yydsv-k?si=RG-YwgMTMNZ-FVex" };
	   
	   String youtube_url = "https://youtu.be/";
	   
	   for (int i = 0; i < detailList.size(); i++) {
		   String categoryName = detailList.get(i).getCategory().getCategoryName();
		   
		   for (int j = 0; j < targetNameArr.length; j++) {
			   if( categoryName.equals( targetNameArr[j] ) ) {
				   targetTitleList.add( categoryName );
				   targetUrlList.add( targetUrl[i] );
			   }
		   }
		}
		   
	   Map<String, Object> result = new HashMap<String, Object>();
	   result.put("nameList", targetTitleList );
	   result.put( "urlList", targetUrlList );
	   result.put("youtube", youtube_url );
	   
	   return result;
   }
   
   
   
   
}
