package com.dragon.book.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dragon.book.model.TSysUser;
import org.jboss.logging.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	/** 日志对象 **/
	private static Logger logger = Logger.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean flag = true;
		String url = request.getRequestURL().toString();
		TSysUser user = (TSysUser) request.getSession()
				.getAttribute("user");


		if (user == null) {
			logger.info("username: " + "null");
			request.getRequestDispatcher("/login").forward(request, response);
			return false;
		}else if ("admin".equals(user)){

			request.getRequestDispatcher("/adminIndex").forward(request, response);

			logger.info("username: " + user.getXm());

		}
		else
			logger.info("username: " + user.getXm());
		logger.info(">>>: " + url);
		return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}
