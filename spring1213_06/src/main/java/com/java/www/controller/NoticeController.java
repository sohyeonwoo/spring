package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {
	
	
	@RequestMapping("notice/list")
	public String list() {
		return "/notice/list";
	}
	@RequestMapping("notice/view")
	public String view() {
		return "/notice/view";
	}
	@RequestMapping("notice/writer")
	public String writer() {
		return "/notice/writer";
	}

}
