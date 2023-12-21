package com.java.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

@RequestMapping("board")
@Controller
public class BController {
	
	@Autowired
	BService bservice;
	
	
	@RequestMapping("bList")
	public String bList(Model model) {
		ArrayList<BoardDto> list = bservice.bList();
		model.addAttribute("list",list);
		return "board/bList";
	}
	
	@RequestMapping("bView")
	public String bView(int bno,Model model) {
		BoardDto bdto =bservice.selectOne(bno);
		model.addAttribute("bdto",bdto);
		return "board/bView";
	}
	
	@RequestMapping("bInsert")
	public String bInsert() {
		return "board/bInsert";
	}
	
	@RequestMapping("doBInsert")
	public String doBInsert(BoardDto bdto,@RequestPart MultipartFile files,Model model) {
		if(!files.isEmpty()) {
			//중복방지 
			String oriFileName = files.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			String newFileName = uuid+"_"+oriFileName;
			
			String fileUpload = "c:/upload/";
			File f = new File(fileUpload+newFileName);
			
			try {
				files.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			bdto.setBfile(newFileName);
		}
		bservice.bInsert(bdto);
		return "board/doBInsert";
	}

}
