package com.java.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

@Controller
public class BController {
	@Autowired
	BService bService;
	
	
	@GetMapping("bList")
	public String bList(Model model) {
		ArrayList<BoardDto> list = bService.bList();
		model.addAttribute("list",list);
		return "bList"; 
	}
	
	@GetMapping("bView")
	public String bView(@RequestParam (defaultValue = "1") int bno,Model model) {
		BoardDto bdto = bService.bView(bno);
		model.addAttribute("bdto",bdto);
		return "bView";
	}
	
	@GetMapping("bInsert")
	public String bInsert() {
		return "bInsert";
	}
	@PostMapping("bInsert")
	public String bInsert(BoardDto bdto,MultipartFile files, Model model) throws Exception {
		if(!files.isEmpty()) {
			String orgName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			String newName = time + "_" +orgName;
			bdto.setBfile(newName);
			String upload="c:/upload/";
			File f = new File(upload+newName);
			files.transferTo(f);
			
		} else {
			bdto.setBfile("");
		}
		
		bService.bInsert(bdto);
		return "doBInseㄹrt";
	}
	

}
