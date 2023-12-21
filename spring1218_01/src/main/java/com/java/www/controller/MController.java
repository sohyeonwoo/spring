package com.java.www.controller;

import java.lang.reflect.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.MemberDto;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MController {

	@Autowired // IOC컨테이너에 있는 객체 가져오기
	MService mser;
	@Autowired
	HttpSession session;

	@RequestMapping("login")
	public String login() {
		return "member/login";
	}

	@RequestMapping("doLogin")
	public String doLogin(MemberDto mdto, HttpServletRequest request, Model model) {
		int result = 0;
		
		System.out.println("MController id : " + mdto.getId());
		System.out.println("MController pw : " + mdto.getPw());

		// db연결 = 리턴값  : dto로 받기 / dto 가 있는지 확인하기
		MemberDto memberDto = mser.loginSelect(mdto); //id,pw
		if(memberDto!=null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			
			result = 1;
			System.out.println("MController id 있음 : "+memberDto.getId());
		}else {
			System.out.println("MController id 없음 : "+null);
		}
			
		
		
		//정보가 담겨있는지 result값을 통해 1,0값으로 출력 
		// result 1:성공 , 0:로그인 실패
		model.addAttribute("result",result);
		return "member/doLogin";
	}
	
	@RequestMapping("logout")
	public String logout() {
		session.invalidate();
		return "member/logout";
	}

}
