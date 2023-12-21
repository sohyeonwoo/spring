package com.java.www.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

@Controller
public class BController {
	
	@Autowired
	BService bser;
	
	//게시글 전체 가져오기
	@RequestMapping("board/bList")
	public String bList(Model model){
		ArrayList<BoardDto> list = bser.bList();
		model.addAttribute("list",list);
		
		return "board/bList";
	}
	
	//게시글 1개 가져오기 
	@RequestMapping("board/bView")
	public String bView(@RequestParam(defaultValue = "1")int bno, Model model) {
		//service연결 = 게시글 1개 가져오기 bno 넘겨서 정보 받아오기
		BoardDto boardDto = bser.selectOne(bno);
		model.addAttribute("bdto",boardDto);
		return "board/bView";
	}

}
