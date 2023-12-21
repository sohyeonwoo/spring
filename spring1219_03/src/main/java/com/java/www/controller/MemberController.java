package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.MemberDto;
import com.java.www.sevice.MService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	MService mser;
	@Autowired
	HttpSession session;
	
	@RequestMapping("member/login")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping("member/doLogin")
	public String doLogin(MemberDto mdto, Model model) {
		String id = mdto.getId();
		String pw = mdto.getPw();
		
		MemberDto memdto = mser.login(id,pw);
		if(memdto !=null) {
			session.setAttribute("session_id",memdto.getId());
			session.setAttribute("session_name",memdto.getName());
			
		}
		
		return "member/doLogin";
	}

}
