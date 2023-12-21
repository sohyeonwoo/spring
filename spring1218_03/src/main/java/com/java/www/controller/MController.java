package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.MemberDto;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MController {
	
	@Autowired
	MService mser;
	@Autowired
	HttpSession session;

	@RequestMapping("member/login")
	public String login(MemberDto mdto , Model model) {
		
		return "member/login";
	}
	public String doLogin(MemberDto mdto , Model model , HttpServletRequest request) {
		int result = 0;
		//db연결
		MemberDto memberDto = mser.loginSelect(mdto);
		if(memberDto !=null) {
			result =1;
			System.out.println("MController id 있음 : "+memberDto.getId());
		}else {
			System.out.println("MController id 없음 : "+null);
		}
		
		//정보가 담겨있는지 result 값을 통해 1 과 0 으로 출력
		//result 1:성공 2:실패
		model.addAttribute("result",result);
		return "member/doLogin";
	}
}
