package com.java.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;


@Controller
public class BoardController {
	
	@Autowired
	BService bser;
	
	//게시글 검색
	@GetMapping("board/search") 
	public String search(@RequestParam(defaultValue = "1")int page , 
			@RequestParam(required = false)String category ,
			@RequestParam(required = false)String searchWord , Model model) {
		
		System.out.println("bController search category : "+category);
		System.out.println("bController search searchWord : "+searchWord);
		//db에서 가져오기
		Map<String,Object> map = bser.selectAllSearch(page,category,searchWord);
		//model에 저장
		model.addAttribute("map",map);
		System.out.println("list 개수"+(int)map.get("countAll"));
		return "board/bList";
	}// 게시글 전체 가져오기 // bList
	
	//게시글 전체 가져오기
	@GetMapping("board/bList") 
	public String bList(@RequestParam(defaultValue = "1")int page,
			@RequestParam(required = false)String category ,
			@RequestParam(required = false)String searchWord, Model model) {
		//db에서 가져오기
		Map<String,Object> map = bser.selectAll(page,category,searchWord);
		//model에 저장
		model.addAttribute("map",map);
		System.out.println("list 개수"+(int)map.get("countAll"));
		return "board/bList";
	}// 게시글 전체 가져오기 // bList
	
	
	//게시글 1개 가져오기
	@GetMapping("board/bView")
	public String bView(@RequestParam(defaultValue = "1")int bno,Model model) {
		
		//게시글 1개 가져오기
		Map<String,Object> map = bser.SelectOne(bno);
		
		//model 에 저장
		model.addAttribute("map",map);
		
		return "board/bView";
	}//게시글 1개 가져오기
	
	//글쓰기 화면 가져오기
	@GetMapping("board/bInsert")
	public String bInsert() {
		return "board/bInsert";
	}
	
	//글쓰기 저장
	@PostMapping("board/bInsert")
	public String bInsert(BoardDto bdto,@RequestPart MultipartFile files, Model model) throws Exception {
		//MultipartFiles files = input type="file" name ="files" name 을 가져옴.
		//파일첨부의 파일이름 중복시 오류해결
		if(!files.isEmpty()) {
			String orgName = files.getOriginalFilename();
			System.out.println("BController file이름 : "+ orgName);
			long time = System.currentTimeMillis();
			String newName = time+"_"+orgName; //중복방지를 위해 새로운 이름변경
			String upload = "c:/upload/"; //파일 업로드 위치
			File f = new File(upload+newName);
			files.transferTo(f); //파일을 저장위치에 저장시킴.
			//파일이름을 BoardDto 에 저장시킴
			bdto.setBfile(newName); //파일이름을 BoardDto에 저장시킴
		}else { //파일이 없으면
			System.out.println("파일첨부가 없음");
			bdto.setBfile("");
		}
		
		//db로 전송하기
		bser.bInsert(bdto);
		
		return "board/doBInsert";
	}
	
	//다중업로드 화면보기
	@GetMapping("board/bInsert2")
	public String bInsert2() {
		return "board/bInsert2";
	}
	
	//다중업로드 화면보기
	@PostMapping("board/bInsert2")
	public String bInsert2(BoardDto bdto,List<MultipartFile>files, Model model) throws Exception {
		//MultipartFiles files = input type="file" name ="files" name 을 가져옴.
		//업로드 파일이 복수일때는 List<MultipartFile> files로 받음.
		String orgName="";
		String newName="";
		String mergeName="";
		int i=0;
		for(MultipartFile file:files) {
			//파일첨부하기 
		    orgName = file.getOriginalFilename();
			System.out.println("BController file이름 : "+ orgName);
			long time = System.currentTimeMillis();
			newName = time+"_"+orgName; //중복방지를 위해 새로운 이름변경
			String upload = "c:/upload/"; //파일 업로드 위치
			File f = new File(upload+newName);
			file.transferTo(f); //파일을 저장위치에 저장시킴.
			//파일이름을 BoardDto 에 저장시킴
			
			//파일이름을 저장하기
			if(i==0) mergeName += time+"_"+orgName; //중복방지를 위해 새로운 이름변경
			else mergeName +=","+ time+"_"+orgName; 
			
			i++;
			
		}
		bdto.setBfile(mergeName); //파일이름을 BoardDto에 저장시킴
		System.out.println("BController 최종파일 첨부이름 : "+ mergeName);
		//db연결 - 내용저장
		bser.bInsert(bdto);
		return "board/bInsert2";
	}
	//-------------------------------------------------
	//------------------다중업로드------------------------
	//-------------------------------------------------
	//게시글 전체 가져오기
	@GetMapping("board/bList2") 
	public String bList2(@RequestParam(defaultValue = "1")int page,
			@RequestParam(required = false)String category ,
			@RequestParam(required = false)String searchWord, Model model) {
		//db에서 가져오기
		Map<String,Object> map = bser.selectAll(page,category,searchWord);
		//model에 저장
		model.addAttribute("map",map);
		System.out.println("list 개수"+(int)map.get("countAll"));
		return "board/bList";
	}// 게시글 전체 가져오기 // bList
	
	//게시글 1개 가져오기
		@GetMapping("board/bView2")
		public String bView2(@RequestParam(defaultValue = "1")int bno,Model model) {
			
			//게시글 1개 가져오기
			Map<String,Object> map = bser.SelectOne(bno);
			
			//model 에 저장
			model.addAttribute("map",map);
			
			return "board/bView2";
		}//게시글 1개 가져오기
		
		//게시글 삭제
		@PostMapping("board/bDelete")
		public String bDelete(@RequestParam(defaultValue = "1") int bno, Model model) {
			System.out.println("BController bDelete bno : "+ bno);
			bser.bDelete(bno);
			return "board/bDelete";
		}
		
		//게시글 수정페이지 보기
		@PostMapping("board/bUpdate")
		public String bUpdate(@RequestParam(defaultValue = "1")int bno, Model model) {
			System.out.println("BController bDelete bno : "+ bno);
			Map<String,Object>map= bser.SelectOne(bno); //게시글 1개 가져오기
			model.addAttribute("map",map);
			return "board/bUpdate";
		}
		
		//게시글 수정 저장
		@PostMapping("board/doBUpdate")
		public String doBUpdate(BoardDto bdto,@RequestPart MultipartFile files) throws Exception {
			System.out.println("BController doBUpdate bno : "+ bdto.getBno());
			String orgName="";
			String newName="";
			if(!files.isEmpty()) {
				//파일업로드가 되면 새로운 파일로 덮어 씌우고
				//파일 업로드가 없으면 기존 파일로 이어감
			    orgName = files.getOriginalFilename();
			    //newName = String.format("%s_%d",orgName,time);
				long time = System.currentTimeMillis();
				newName = time+"_"+orgName;
				String upload = "c:/upload/";     //파일저장위치
				File f = new File(upload+newName);//파일생성
				files.transferTo(f);              //파일전송
				bdto.setBfile(newName);           //bdto파일이름 저장
			}
			//db전송
			bser.doBUpdate(bdto);
			return "board/doBUpdate";
		}
	
		//답글 달기 
		@PostMapping("board/bReply")
		public String bReply(@RequestParam(defaultValue = "1")int bno, Model model) {
			System.out.println("BController bDelete bno : "+ bno);
			Map<String,Object>map= bser.SelectOne(bno); //게시글 1개 가져오기
			model.addAttribute("map",map);
			return "board/bReply";
		}
		
		//답변달기 저장
		@PostMapping("board/doBReply")
		public String doBReply(BoardDto bdto,@RequestPart MultipartFile files, Model model) throws Exception {
			//답변달기 - bgroup , bstep , bindent 값이 있어야함 // bdto에 담겨 있음
			
			
			if(!files.isEmpty()) {
				String orgName = files.getOriginalFilename();
				System.out.println("BController file이름 : "+ orgName);
				long time = System.currentTimeMillis();
				String newName = time+"_"+orgName; //중복방지를 위해 새로운 이름변경
				String upload = "c:/upload/"; //파일 업로드 위치
				File f = new File(upload+newName);
				files.transferTo(f); //파일을 저장위치에 저장시킴.
				//파일이름을 BoardDto 에 저장시킴
				bdto.setBfile(newName); //파일이름을 BoardDto에 저장시킴
			}else { //파일이 없으면
				System.out.println("파일첨부가 없음");
				bdto.setBfile("");
			}
			//db로 전송하기
			bser.doBReply(bdto);
			
			return "board/doBReply";
		}
}//class
