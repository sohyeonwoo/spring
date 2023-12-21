package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;

@Controller
public class FController {
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	//======== board 관련 =========
	@RequestMapping("bInsert")
	public String bInsert() {
		return "bInsert";
	}
	@RequestMapping("bView")
	public String bView(BoardDto bdto,MemberDto mdto, Model model) {
		System.out.println("controller bdto id : "+ bdto.getId());
		System.out.println("controller mdto id : "+mdto.getId());
		model.addAttribute("bdto",bdto);
		return "bView";
	}
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	@RequestMapping("mLogin")
	public String mLogin(MemberDto mdto , Model model) {
		model.addAttribute("mdto",mdto);
		System.out.println("controller : "+mdto.getId());
		System.out.println("controller : "+mdto.getPw());
		return "mLogin";
	}
	@RequestMapping("bUpdate")
	public String bUpdate(MemberDto mdto , Model model) {
		model.addAttribute("mdto",mdto);
		System.out.println("controller : "+mdto.getId());
		System.out.println("controller : "+mdto.getPw());
		return "bUpdate";
	}
//-----------가져올 데이터가 별로 없을시
//	@RequestMapping("doLogin")
//	public String login(String id,String pw,Model model) {
//		model.addAttribute("id",id);
//		model.addAttribute("pw",pw);
//		return "doLogin";
//	}
}
