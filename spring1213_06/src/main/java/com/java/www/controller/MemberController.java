package com.java.www.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.www.boardDto.BoardDto;


@Controller
public class MemberController {

	@GetMapping("member/memberWriter")
	public String memberWiter(Model model) {
		//id 전달
		String id = "admin";
		//request.setAttrbute 와 같은 개념
		//현재 날짜와 시간을 Timestamp 파일에 저장
		Timestamp bdate = new Timestamp(System.currentTimeMillis());
		BoardDto bdto = new BoardDto("aaa","1111","홍길동","010-11","male","golf",bdate);
		
		model.addAttribute("bdto",bdto);
		return  "member/memberWriter";
	}
	
}
