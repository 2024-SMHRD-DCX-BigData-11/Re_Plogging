package com.smhrd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket // 웹 소켓을 사용 가능한 상태로 할 것이다.
@RequiredArgsConstructor // 필요한 변수들을 초기화 해주는 생성자
public class WebSocketConfig implements WebSocketConfigurer {
   
   private final WebSocketHandler handler;

   @Override
   public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
      registry.addHandler(handler, "/chat") // 구현한 Socket handler에 URL Mapping 부여
            .setAllowedOrigins("*");  // 누가 요청을 하던지 전부 허용하겠다.
      
   }

}
