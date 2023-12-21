package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //jsp 페이지를 오픈
public class FController {
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

}
