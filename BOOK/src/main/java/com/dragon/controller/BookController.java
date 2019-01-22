package com.dragon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
