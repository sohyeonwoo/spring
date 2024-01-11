package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.service.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MController {
	@Autowired
	EmailService emailService;
	@Autowired
	HttpSession session;
	
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@ResponseBody
	@PostMapping("email")
	public String email(String name,String email) {
		System.out.println("이름 : "+name);
		System.out.println("이름 : "+email);
		//service 연결 - email 발송
		String result = emailService.emailSend(name,email);
		
		return "success: "+result;
	}
	
	@ResponseBody
	@PostMapping("email")
	public String email2(String name,String email) {
		System.out.println("이름 : "+name);
		System.out.println("이름 : "+email);
		//service 연결 - email 발송
		String result = emailService.emailSend2(name,email);
		
		return "success: "+result;
	}
	//인증번호 확인
	@ResponseBody
	@PostMapping("email")
	public String emailCheck(String check) {
		System.out.println("확인 : "+check);
		//service 연결 - 인증번호 확인 : db연결해서 확인 , session
		String result = "fail";
		String pwcode = (String) session.getAttribute("email_pwcode");
		if(check.equals(pwcode)) {
			result = "success";
		}
		session.invalidate();
		return result;
	}

}
