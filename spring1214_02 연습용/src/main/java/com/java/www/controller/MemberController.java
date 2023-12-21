package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.memberDto.MemberDto;

@Controller
public class MemberController {

	@RequestMapping("member/memberInsert")
	public String memberInsert(MemberDto mdto, Model model) {
		model.addAttribute("mdto",mdto);
		return "member/memberInsert";
	}

	@RequestMapping("member/memberView")
	public String memberView(MemberDto mdto, Model model) {
		model.addAttribute("mdto",mdto);
		return "member/memberView";
	}
	@RequestMapping("member/login")
	public String login() {
		return "member/login";
	}
	@RequestMapping("member/doMInsert")
	public String doMInsert(MemberDto mdto, Model model) {
		model.addAttribute("mdto",mdto);
		return "member/memberView";
	}
	@RequestMapping("member/mUpdate")
	public String mUpdate(MemberDto mdto, Model model) {
		model.addAttribute("mdto",mdto);
		return "member/memberUpdate";
	}
}
