package com.dragon.book.config;

import com.dragon.book.listener.LoginListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
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

	/**
	 * 监听器配置，单点登录
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletListenerRegistrationBean listenerRegist() {
		ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
		srb.setListener(new LoginListener());
		return srb;
	}
}
