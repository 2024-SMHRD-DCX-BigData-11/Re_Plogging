package com.smhrd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration	// 이 파일이 설정파일로써 적용되어야 함을 알려주기
public class ResourceConfig implements WebMvcConfigurer {
	
	@Value("${save.path}")
	private String savePath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// http://localhost:8096/boot/save/~~~~.jpg
		registry.addResourceHandler("/image/**") // url mapping 지정
				.addResourceLocations( "file:///" + savePath);	// 어떤 폴더에 접근하는지
	}
}
