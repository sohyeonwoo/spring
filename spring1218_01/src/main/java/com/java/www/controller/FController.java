
package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FController {
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}

}
