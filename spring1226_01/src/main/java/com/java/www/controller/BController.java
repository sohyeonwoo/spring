package com.java.www.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("customer")
public class BController {
	@Autowired
	BService bService;
	@Autowired
	HttpSession session;
	
	@GetMapping("notice")
	public String notice(Model model) {
		//page 가지고 와야 함 .
		
		//service 연결
		List<BoardDto> list = bService.selectAll();
		model.addAttribute("list",list);
		return "customer/notice";
	}
	@GetMapping("notice_view")
	public String notice_view(@RequestParam (defaultValue = "1") int bno,Model model) {
		//service 
		Map<String,Object> map = bService.selectOne(bno);
		//model 에 전송
		model.addAttribute("map",map);
		return "customer/notice_view";
	}
	
	//ajax 댓글 입력
	@ResponseBody
	@PostMapping("BCommentInsert")
	public BCommentDto BCommentInsert(BCommentDto cdto) {
		System.out.println("BController BCommentInsert"+cdto.getCpw());
		System.out.println("BController BCommentInsert"+cdto.getCcontent());
		System.out.println("BController BCommentInsert"+cdto.getBno());
		//id 추가
		cdto.setId((String) session.getAttribute("session_id"));
		BCommentDto bCommentDto = bService.BCommentInsert(cdto);
		//db에 저장된 댓글 1개 가져오기 = cno,cdate 가 포함되어 있음 
		return bCommentDto;
	}
	

}
