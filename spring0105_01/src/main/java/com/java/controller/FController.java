package com.java.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.BoardDto;
import com.java.dto.MemberDto;
import com.java.service.BService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FController {

	@Autowired BService bService;
	@Autowired HttpSession session;
	
	@GetMapping({"/","main"})
	public String main() {
		return "main";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("logout")
	public String logout() {
		session.invalidate();
		return "main";
	}
	
	@PostMapping("login")
	public String login(MemberDto mdto,Model model) {
		MemberDto mdto1 = bService.login(mdto);
		
		if(mdto1 != null) {
			session.setAttribute("session_id", mdto1.getId());
			session.setAttribute("session_name", mdto1.getName());
		}
		
		
		model.addAttribute("mdto1",mdto1);
		return "dologin";
	}
	
	@GetMapping("bwrite")
	public String bwrite() {
		return "bwrite";
	}
	
	@GetMapping("map")
	public String map() {
		
		return "map";
	}
	
	//최신영화가져오기
	@PostMapping("movie")
	@ResponseBody
	public String searchScreen(String movie) throws Exception{
		System.out.println("data : "+movie);
//--- 영화정보 API 가져오기
		String key = "e355ea411876070a1e496840ad4511b0";
	
		StringBuilder urlBuilder = new StringBuilder("http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"); /*URL*/
		       urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "="+key); /*Key*/
		       //주석처리해야 함.
		       //urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*이부분 주석처리 : 공공데이터포털에서 받은 인증키*/
		       urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
		       urlBuilder.append("&" + URLEncoder.encode("targetDt","UTF-8") + "=" + URLEncoder.encode("20240104", "UTF-8")); /*날짜*/
		       URL url = new URL(urlBuilder.toString());
		       HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		       conn.setRequestMethod("GET");
		       conn.setRequestProperty("Content-type", "application/json");
		       System.out.println("Response code: " + conn.getResponseCode());
		       BufferedReader rd;
		       if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		           rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		       } else {
		           rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		       }
		       StringBuilder sb = new StringBuilder();
		       String line;
		       while ((line = rd.readLine()) != null) {
		           sb.append(line);
		       }
		       rd.close();
		       conn.disconnect();
		       System.out.println(sb.toString());
		       return sb.toString();
//---
		
	}
//-----------------------영화포스터	
	
	
	//summernote에서 ajax 이미지 전송	
	@PostMapping("uploadImage") 
	@ResponseBody
	public String uploadImage( @RequestPart MultipartFile file) throws Exception {
		String urlName="";
		if(!file.isEmpty()) {
			String oriFileName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String uploadFileName = time+"_"+oriFileName; //파일이름변경
			String upload = "c:/upload/";
			File f = new File(upload+uploadFileName); //파일등록
			file.transferTo(f); // 파일서버로 전송
			urlName = "/upload/"+uploadFileName;
		} 
		return urlName;
	}
	
	//게시글 보기
	@GetMapping("bview")
	public String bview(@RequestParam(defaultValue = "1")int bno,Model model) {
		BoardDto bdto = bService.selectOne(bno);
		
		model.addAttribute("bdto",bdto);
		return "bview";
	}
	
	@PostMapping("bwrite") //글쓰기 저장
	public String bwrite(BoardDto bdto, @RequestPart MultipartFile file, Model model) throws Exception {
		System.out.println("controller bwrite btitle : "+bdto.getBtitle());
		if(!file.isEmpty()) {
			String oriFileName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String uploadFileName = time+"_"+oriFileName; //파일이름변경
			bdto.setBfile(uploadFileName); //dto bfile 이름저장

			String upload = "c:/upload/";
			File f = new File(upload+uploadFileName); //파일등록
			file.transferTo(f); // 파일서버로 전송
		} else {
			bdto.setBfile("");
		}
		
		System.out.println("controller bwrite bfile : "+ bdto.getBfile());
		
		//service 연결 : 글쓰기 저장
		bService.bwrite(bdto);
		
		
		model.addAttribute("result","success-bwrite");
		return "result";
	}
	
	
	@GetMapping("blist")
	public String blist(Model model) {
		//게시글 전체 가져오기
		List<BoardDto> list = bService.selectAll();
		model.addAttribute("list",list);
		return "blist";
	}
	@GetMapping("bInsert")
	public String bInsert() {
		return "bInsert";
	}
	
	@PostMapping("write")
	public String write(@RequestPart MultipartFile file, BoardDto bdto,Model model) throws Exception {
		if(!file.isEmpty()) {
			String oriName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String newName = time+"_"+oriName;
			bdto.setBfile(newName);
			
			String upload = "c:/upload/";
			File f = new File(upload+newName);
			file.transferTo(f);
			
		}else {
			bdto.setBfile("");
		}
		bService.bInsert(bdto);
		model.addAttribute("result","success-bwrite");
		
		
		return "result";
	}
}
