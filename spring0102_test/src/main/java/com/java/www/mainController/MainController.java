package com.java.www.mainController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("main")
	public String main() {
		return "layout/main";
	}
	@RequestMapping("member/login")
	public String login() {
		return "member/login";
	}

}
