package com.java.www.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.www.dto.EmpDepDto;
import com.java.www.dto.EmpDto;
import com.java.www.dto.MemBoardDto;
import com.java.www.service.EmpService;

@Controller
public class FController {
	
	@Autowired
	EmpService empservice;
	
	
	
	@GetMapping("index")
	public String index() {
		return "index";
	}
	@GetMapping("list")
	public String list(EmpDto edto , Model model) {
		ArrayList<EmpDto> list = empservice.emplist();
		model.addAttribute("list",list);
		return "list";
	}
	@GetMapping("list2")
	public String list2(EmpDto edto , Model model) {
		ArrayList<EmpDepDto> list = empservice.emplist2();
		model.addAttribute("list",list);
		return "list2";
	}
	@GetMapping("list3")
	public String list3(EmpDto edto , Model model) {
		ArrayList<MemBoardDto> list = empservice.memBoardDto();
		model.addAttribute("list",list);
		return "list3";
	}

}
