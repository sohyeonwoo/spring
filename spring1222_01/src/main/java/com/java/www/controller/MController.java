package com.java.www.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.MemberDto;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpSession;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("member")
public class MController {

	@Autowired
	MService mService;
	@Autowired
	HttpSession session;
	
	
	@RequestMapping("login") //로그인 페이지 이동
	public String login() {
		return "member/login";
	}
	
	@PostMapping("doLogin") //로그인 확인
	public String login(String id,String pw ,Model model) {
		MemberDto mdto = mService.login(id, pw);
		if(mdto !=null) {
			session.setAttribute("session_id",mdto.getId());
			session.setAttribute("session_name",mdto.getName());
		}
		model.addAttribute("mdto",mdto);
		return "member/doLogin";
	}
	@GetMapping("join01")
	public String join01() {
		return "member/join01";
	}
	@GetMapping("join02")
	public String join02() {
		return "member/join02";
	}
	
	@PostMapping("join02con")
	@ResponseBody
	public String join02con(MemberDto mdto,String f_tell,String m_tell, String l_tell , Model model) {
		String phone =  f_tell+"-"+m_tell+"-"+l_tell;
		mdto.setPhone(phone);
		String result = mService.join02confirm(mdto);
		
		return result;
		
	}
	@PostMapping("idCheck")
	@ResponseBody
	public String idCheck(String id) {
		System.out.println("MController idCheck id: "+id);
		String result = "사용가능";
		result=mService.idCheck(id);
		return "result";
	}
	@GetMapping("join03")
	public String join03() {
		return "member/join03";
	}
}
