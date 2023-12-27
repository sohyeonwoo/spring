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
	//===로그인 확인 부분
	//ajax 형태
	@PostMapping("ajaxLogin")
	@ResponseBody //데이터로 전송
	public String ajaxLogin(MemberDto mdto) {
		System.out.println("MController id:"+mdto.getId());
		System.out.println("MController pw:"+mdto.getPw());
		
		//service 연결 //result 결과값으로 1 또는 0 으로 받아서 데이터가 있는지 확인하기
		int result = mService.login(mdto);
		System.out.println("MController result:"+result);
		return result +"";
	}
	
	//===로그인 확인 부분
	//===jsp형태
	@PostMapping("login")
	public String login(MemberDto mdto,Model model) {
		System.out.println("MController id:"+mdto.getId());
		System.out.println("MController pw:"+mdto.getPw());
		
		//service 연결 //result 결과값으로 1 또는 0 으로 받아서 데이터가 있는지 확인하기
		int result = mService.login(mdto);
		model.addAttribute("result",result);
		return "member/doLogin";
	}

	
}
