package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.BoardDto;

@Controller
public class FController {
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	@RequestMapping("writeView")
	public String writeView(BoardDto bdto,Model model) {
		model.addAttribute("bdto",bdto);
		return "writeView";
	}
	@RequestMapping("content_view")
	public String content_view(BoardDto bdto, Model model) {
		model.addAttribute("bdto",bdto);
		return "content_view";
	}
	@RequestMapping("list")
	public String list() {
		return "list";
	}
	@RequestMapping("mView")
	public String mView() {
		return "mView";
	}

}
