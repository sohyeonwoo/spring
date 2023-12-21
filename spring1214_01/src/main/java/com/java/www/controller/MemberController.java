package com.java.www.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;

@Controller
public class MemberController {
	
	
	@RequestMapping("member/mInsert")
	public String mInsert() {
		return"member/memberInsert";
	}
	@RequestMapping("member/doMInsert")
	public String doMInsert(MemberDto mdto, Model model) {
		model.addAttribute("mdto",mdto);
		System.out.println("controller : "+ mdto.getHobby());
		return"member/memberView";
	}
	@RequestMapping("member/mUpdate")
	public String mUpdate(MemberDto mdto, Model model) {
		model.addAttribute("mdto",mdto);
		
		return"/member/memberUpdate";
	}
	
	//기본
	@RequestMapping("member/login")
	public String login() {
		return"member/login";
	}
	@PostMapping("doLogin")
	public String doLogin(MemberDto mdto , BoardDto boardDto,Model model) {
		
		
		String id = mdto.getId();
		
		String pw = mdto.getPw();
		int bno = boardDto.getBno();
		//기본생성자
	//	MemberDto mdto = new MemberDto();
	//	mdto.setId(mdto.getId());
		System.out.println("mdto.getId : "+ mdto.getId());
		Timestamp mdate = new Timestamp(System.currentTimeMillis());
		
		//전체생성자
		MemberDto mdto2 = new MemberDto(id,pw,"소현우","01111111","female","golf",mdate);
		
		//부분생성자
		MemberDto mdto3 = MemberDto.builder().id(id).pw(pw).name("소현우").build();
		System.out.println("controller : "+mdto3.getName());
		model.addAttribute("id",id) ;
		model.addAttribute("pw",pw) ;
		model.addAttribute("bno",bno) ;
		model.addAttribute("mdto",mdto) ;
		return "member/doLogin";
	}

}
