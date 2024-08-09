package com.smhrd.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.smhrd.entity.Comment;
import com.smhrd.entity.Comment;
import com.smhrd.entity.Member;
import com.smhrd.repository.ChatRepository;

@Component // 특별한 역할을 하진 않는데 Scan이 되어서 Bean으로 등록이 되어져야하는 경우
public class WebSocketHandler extends TextWebSocketHandler {
	
	@Autowired
	private ChatRepository repo;
	
	private Gson gson = new Gson();
   
   // 사용자의 연결 정보들을 저장할 객체
   private List<WebSocketSession> users = new ArrayList<WebSocketSession>();
   
   // 만약 채팅방이 각각 존재하고, 채팅방별로 사용자가 다르다면
   // >> HashMap 자료구조를 이용해서 저장
   //   key : object 형식으로 저장
   HashMap<String, List<WebSocketSession>> rooms = new HashMap<String, List<WebSocketSession>>();
   
   
   @Override
   public void afterConnectionEstablished(WebSocketSession session) throws Exception {
      // 사용자가 WebSocek에 연결을 했을 때 실행되는 메소드
      System.out.println("사용자 연결됨");
      
      users.add(session);
   }
   
   @Override
   public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
      // 사용자가 WebSocket 과의 연결을 끊었을 때 실행되는 메소드
      
      users.remove(session);
      
   }
   
   @Override
   public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
      // 사용자가 보낸 메세지를 받았을 때 실행되는 메소드
      // WebSocketMassage 객체의 payload 변수에 받은 메세지가 담김
      System.out.println(message.getPayload());
      
      // 모든 사용자에게 메세지 전달
      for(WebSocketSession user : users ) {
    	  if( !user.equals(session) ) {
    		user.sendMessage(message);  // 메세지를 전달
    	  }
      }
      
      // DB에 메세지를 저장
      String json = message.getPayload().toString();
      // JSON ➡️ JavaObject ( Gson )
      // fromJson(json 문자열, 어떤 클래스로 변환할 것인지)
      // JSON의 Key값과 같은 필드명을 가지는 클래스로만 변환 가능
//      Chat chat = gson.fromJson(json,  Chat.class);
      
      // 변수의 자료형이 String, int 등이 아니라 Member 선언이 되어있어 변환 불가함
      // 즉, DTO를 따로 만들어서 변환해 주어야 함
      ChatDTO dto = gson.fromJson(json, ChatDTO.class);
      
      // ChatDTO ➡️ Chat 변환하는 과정 필요
      Comment chat = new Comment();
      chat.setMessage( dto.getMessage() );
      
      Member member = new Member();
      member.setEmail( dto.getEmail() );
      
      chat.setEmail(member);
      
      repo.save(chat);
   }
   
}
