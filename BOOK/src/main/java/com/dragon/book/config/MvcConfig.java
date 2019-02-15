package com.dragon.book.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dragon.book.interceptor.LoginInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	/**
	 * 过滤器配置，去除部分路径
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		String[] excludes = new String[] { "/login", "/dologin", "/css/**",
				"/js/**", "/images/**", "/data/**", "/dvpt/**", "/html5/**",
				"/error", "/register", "/doreg", "/checkName" };
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
				.excludePathPatterns(excludes);
	}
}
