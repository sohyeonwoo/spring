package com.java.www.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("notice")
public class NController {
	@GetMapping("noticeList")
	public String noticeList() {
		return "notice/noticeList";
	}
	@GetMapping("noticeInsert")
	public String noticeInsert(Model model) {
		return "notice/noticeInsert";
	}
	@GetMapping("doNoticeInsert")
	public String doNoticeInsert(HttpServletRequest request,Model model) {
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bfile = request.getParameter("bfile");
		System.out.println("controller : "+ btitle);
		System.out.println("controller : "+ bcontent);
		System.out.println("controller : "+ bfile);
		Timestamp bdate = new Timestamp(System.currentTimeMillis()) ;
		
		BoardDto bdto = BoardDto.builder()
				.btitle(btitle)
				.bcontent(bcontent)
				.bdate(bdate)
				.bfile(bfile).build();
		//전체 데이터를 bdto에 담아서 model 로 데이터값 전송
		model.addAttribute("bdto",bdto);
		
		//각각의 데이터를 model 로 넘김 //다소 번거로움
//		model.addAttribute("btitle",btitle);
//		model.addAttribute("bcontent",bcontent);
//		model.addAttribute("bfile",bfile);
//		model.addAttribute("bdate",bdate);
		return "notice/noticeView";
	}
	@RequestMapping("noticeView")
	public String noticeView(Model model) {
		//request.setAttrbute 와 같은 개념
		
		
		return "notice/noticeView";
	}
}
