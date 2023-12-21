package com.java.www.controller;

import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.www.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MController {
	
	
	//Model 사용
	@GetMapping("member/memberInsert")
	public String memberInsert(Model model) {
		return  "member/memberInsert";
	}
	@GetMapping("member/login")
	public String login(Model model) {
		return  "member/login";
	}
	
	@PostMapping("member/doLogin")
	public String doLogin(String id,String pw,@RequestParam(defaultValue = "1") int bno, Model model) {
		System.out.println("controller id : "+ id);
		System.out.println("controller pw : "+ pw);
		System.out.println("controller bno : "+ bno);
		return  "/index";
	}
	
	@PostMapping("member/doMemberInsert")
	public String doMemberInsert(HttpServletRequest request,Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String[] hobbys = request.getParameterValues("hobby");
		System.out.printf("%s,%s,%s,%s,%s,%s",id,pw,name,phone,gender,Arrays.toString(hobbys));
		return  "member/memberView";
	}
	//Model 사용
	@GetMapping("member/memberUpdate")
	public String memberUpdate(Model model) {
		//request.setAttrbute 와 같은 개념
		//현재 날짜와 시간을 Timestamp 파일에 저장
		Timestamp bdate = new Timestamp(System.currentTimeMillis());
		MemberDto mdto = new MemberDto("aaa","1111","홍길동","010-11","male","golf",bdate);
		
		model.addAttribute("mdto",mdto);
		return  "member/memberUpdate";
	}
	
	
//========>>>>>>>>	//ModelAndView 사용
	
//	@GetMapping("member/memberInsert")
//	public ModelAndView memberInsert() {
//		ModelAndView mv = new ModelAndView();
//		// id를 전달
//		String id = "admin";
//		// request.setAttribute("id",id);
//		// 현재 날짜와 시간을 Timestamp파일에 저장
//		Timestamp mdate = new Timestamp(System.currentTimeMillis());
//		MemberDto mdto 
//		= new MemberDto("bbb","1111","유관순","010-111-1111","male","golf",mdate);
//		
//		//mdto.setMdate(null);
//		mv.addObject("mdto", mdto);
//		mv.setViewName("member/memberInsert");
//		System.out.println("MemberDto id : " + mdto.getId());
//		return mv;
//	}
}
