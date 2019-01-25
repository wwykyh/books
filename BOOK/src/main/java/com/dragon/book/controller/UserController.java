package com.dragon.book.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dragon.book.mapper.TSysUserMapper;
import com.dragon.book.model.TSysUser;
import com.dragon.book.model.TSysUserExample;
import com.dragon.book.model.TSysUserExample.Criteria;

@Controller
public class UserController {
	@Autowired
	private TSysUserMapper userMapper;
	
		@PostMapping("/dologin")
		public String doLogin(@RequestParam String username,
				@RequestParam String pwd,HttpServletRequest request,HttpSession session) throws Exception{
			TSysUserExample example = new TSysUserExample() ;
			Criteria criteria = example.createCriteria() ;
			criteria.andXmEqualTo(username) ;
			criteria.andPwdEqualTo(pwd) ;
			List<TSysUser> list = userMapper.selectByExample(example) ;
			if(list.size()>0){
				//System.out.println(list);
				TSysUser tSysUser = list.get(0) ;
				session.setAttribute("userId", tSysUser.getUserId());
				session.setAttribute("userName",tSysUser.getXm() );
				if(tSysUser.getXm().equals("admin")){
					return "adminIndex";
				}
				return "index";		
				}	
			else
				return "login";
		}
		
		@GetMapping("/logout")
		public String logout(HttpSession session){
			session.removeAttribute("userName");
			return "login";
		}
		
		
}
