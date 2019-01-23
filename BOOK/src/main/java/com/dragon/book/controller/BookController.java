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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
	@GetMapping("/index")
	public String bookIndex() throws Exception{
		return "index";
	}
	
	@GetMapping("/adminindex")
	public String adminbookIndex() throws Exception{
		return "adminIndex";
	}
}
