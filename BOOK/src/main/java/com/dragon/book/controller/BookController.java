/**
 * 版权所有：厦门市巨龙软件工程有限公司
 * Copyright 2009 Xiamen Dragon Software Eng. Co. Ltd.
 * All right reserved. 
 *====================================================
 * 文件名称: BookController.java
 * 修订记录：
 * No    日期				    作者(操作:具体内容)
 * 1.    1 22, 2019			王炜焱(创建:创建文件)
 *====================================================
 * 类描述：图书控制器
 * 
 */
package com.dragon.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dragon.book.model.TBorrow;
import com.dragon.book.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("index")
	public String bookIndex() {
		return "index";
	}

	@GetMapping("/adminindex")
	public String adminBookIndex() {
		return "adminIndex";
	}

	@GetMapping("login")
	public String login() throws Exception {
		return "login";
	}

	@GetMapping("Home")
	public String home(Model model) throws Exception {
		List<TBorrow> bookList =bookService.getBookTop();
		List<TBorrow> userList = bookService.getUserTop();
		model.addAttribute("bookList",bookList);
		model.addAttribute("userList", userList);
		return "home";
	}

	@GetMapping("search")
	public String search() throws Exception {
		return "search";
	}
}
