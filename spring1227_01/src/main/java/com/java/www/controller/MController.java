package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.MemberDto;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MController {
	
	@Autowired
	MService mService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@GetMapping("logout")
	public String logout() {
		session.invalidate();
		return "member/logout";
	}

	//--------- 로그인 확인부분 ----------
	//--------- ajax 형태 ----------
	@PostMapping("ajaxLogin")
	@ResponseBody
	public String ajaxLogin(MemberDto mdto) {
		System.out.println("MController login id : "+mdto.getId());
		System.out.println("MController login pw : "+mdto.getPw());
		
		//service 연결 - result = 1(로그인), 0(아이디 또는 패스워드가 일치X)
		int result = mService.login(mdto);

		return result+"";
	}
	
	//--------- jsp 형태 ----------
	@PostMapping("login")
	public String login(MemberDto mdto, Model model) {
		System.out.println("MController login id : "+mdto.getId());
		System.out.println("MController login pw : "+mdto.getPw());
		
		//service 연결 - result = 1(로그인), 0(아이디 또는 패스워드가 일치X)
		int result = mService.login(mdto);
		//model 전송
		model.addAttribute("result",result);
		return "member/doLogin";
	}
}
