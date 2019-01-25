package com.dragon.book.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dragon.book.interceptor.LoginInterceptor;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		String[] excludes = new String[]{"/login","/dologin","/css/**","/js/**","/data/**","/dvpt/**","/html5/**","/error"};	
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(excludes) ;
	}
}
