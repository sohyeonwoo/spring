package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.BoardDto;
import com.java.service.BService;

@Controller
public class FController {
	@Autowired
	BService bService;
	
	
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("blist")
	public String blist(Model model) {
		List<BoardDto> list = bService.selectAll();
		model.addAttribute("list",list);
		return "blist";
	}
	
	@PostMapping("bwrite")
	public String bwrite(BoardDto bdto , @RequestPart MultipartFile file ) throws Exception {
		
		if(!file.isEmpty()) {
			String oriName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String newName = time+"_"+oriName;
			
			//bdto - bfile에 파일이름 담아둠
			bdto.setBfile(newName);
			
			//c:/upload/ 업로드 저장
			String upload = "c:/upload/";
			File f = new File(upload+newName);
			file.transferTo(f);
		}else {
			bdto.setBfile("");
		}
		bService.bwrite(bdto);
			return "dobWrite";
	}
	
	@GetMapping("bwrite")
	public String bwrite() {
		return "bwrite";
	}
	
	//내용부분 - 이미지추가시 파일업로드
	@ResponseBody
	@PostMapping("summernoteUpload")
	public String summernoteUpload(@RequestPart MultipartFile file) throws Exception {
		String urlLink = "";
		if(!file.isEmpty()) {
			String oriName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String uploadFile = time+"_"+oriName;
			String fileUpload = "c:/upload/";
			
			//파일서버에 업로드 부분
			File f = new File(fileUpload+uploadFile);
			file.transferTo(f);
			//bdto bfile 에 추가하기
			urlLink = "/upload/"+uploadFile;
			
		}
		return urlLink;
	}

	
	@GetMapping("bview")
	public String bview(@RequestParam(defaultValue = "1")int bno,Model model) {
		BoardDto bdto = bService.selectOne(bno);
		model.addAttribute("bdto",bdto);
		return "bview";
	}

}
