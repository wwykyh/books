package com.dragon.book.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;


import com.dragon.book.model.TSysUser;

public class LoginListener implements HttpSessionAttributeListener {
	//只要有人登录，就把登录信息保存map,
	//key-》用户名
	private Map<String,HttpSession> sessionMap = new HashMap<String,HttpSession>();
	@Override
	public void attributeAdded(HttpSessionBindingEvent sessionEvent) {
		// TODO Auto-generated method stub
		//保存在session里面的是否跟用户相关，约定userName
			if("userName".equals(sessionEvent.getName())){
				//取出值
				//判断前面是否登录
				String  uName = (String) sessionEvent.getValue();
				if(sessionMap.containsKey(uName)){
					//得到原来session，踢掉
					HttpSession orgSession =sessionMap.get(uName);

					orgSession.invalidate();
					
					
				}
				//新的session保存起来
				sessionMap.put(uName, sessionEvent.getSession());
			}
		}
		


	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}

}