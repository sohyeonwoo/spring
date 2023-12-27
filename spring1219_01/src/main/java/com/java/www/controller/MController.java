int package com.java.www.controller;

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
	
	
	//로그인 확인
	@PostMapping("login")
	public String login(String id,String pw, Model model) {
		int result = mService.login(id,pw);
		model.addAttribute("result",result);
		return "member/doLogin";
	}

	@GetMapping("logout")
	public String logout() {
		session.invalidate();
		return "member/logout";
	}
	
	@GetMapping("mInsert") //회원가입 페이지 이동
	public String mInsert() {
		return "member/mInsert";
	}
	//responseBody : data 값을 넘겨주는 코드
	@ResponseBody
	@PostMapping("mInsert") //회원가입 페이지 저장
	public String mInsert(MemberDto mdto) {
		System.out.println("MController mdto id:"+mdto.getId());
		System.out.println("MController mdto gender:"+mdto.getGender());
		
		String result = mService.mInsert(mdto);
		return result;
	}
	
	@PostMapping("idCheck") //ajax 로그인 아이디 체크
	@ResponseBody //리턴을 jsp 페이지가 아니라 data로 넘겨주는 것.
	public String idCheck(String id) {
		
		//db전송 
		String result = mService.idCheck(id);
		return result;
	}
}
